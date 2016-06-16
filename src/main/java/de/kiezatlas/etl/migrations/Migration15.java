package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;
import java.util.List;

import java.util.logging.Logger;


/*
 * Remove all geo object type facets childs without an association to a Geo Object.
 */
public class Migration15 extends Migration {

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject WorkspacesService workspaceService;
    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    int facetTopicsDeletedCount = 0;

    @Override
    public void run() {
        // ### Clean up ALL abandoned facet COMPOSITION topics once related to a Geo Object
        List<Topic> openingHours = dm4.getTopicsByType("ka2.oeffnungszeiten");
        for (Topic openingHour : openingHours) {
            deleteIfNoParentGeoObject(openingHour);
        }
        List<Topic> contactEntries = dm4.getTopicsByType("ka2.kontakt");
        for (Topic contactEntry : contactEntries) {
            deleteIfNoParentGeoObject(contactEntry);
        }
        List<Topic> websiteTopics = dm4.getTopicsByType("dm4.webbrowser.web_resource");
        for (Topic websiteTopic : websiteTopics) {
            deleteIfNoParentGeoObject(websiteTopic);
        }
        // 4.3) Assign "Berlin" and "Deutschland" to "Kiezatlas"
        Topic de = dm4.getTopicByUri("ka2.country.deutschland");
        if (de == null) throw new RuntimeException("Migration failed, Deutschland country topic not in database.");
        Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        workspaceService.assignToWorkspace(de, kiezatlas.getId());
        logger.info("Assigned Deutschland " + de.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic berlin = dm4.getTopicByUri("ka2.city.berlin");
        if (berlin == null) throw new RuntimeException("Migration failed, Berlin city topic not in database.");
        workspaceService.assignToWorkspace(berlin, kiezatlas.getId());
        logger.info("Assigned Berlin " + berlin.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        // ### Träger Topics maybe too
        logger.info("### Migration15 Part 1: COMPLETE: Cleaned up (deleted) "+facetTopicsDeletedCount+" abandoned (COMPOSITION) facet topics!");
    }

    private void deleteIfNoParentGeoObject(Topic entry) {
        Topic geoObject = entry.getRelatedTopic(null, "dm4.core.child", "dm4.core.parent", "ka2.geo_object");
        if (geoObject == null) {
            entry.delete();
            facetTopicsDeletedCount++;
        }
    }

}
