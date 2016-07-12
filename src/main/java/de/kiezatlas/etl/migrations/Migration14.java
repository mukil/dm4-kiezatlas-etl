package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.kiezatlas.KiezatlasService;
import java.util.List;
import java.util.logging.Logger;



/*
 * Part 1: Remove all geo object type facets childs without an association to a Geo Object.
 * Files, Beschreibung, Sonstiges, Stichworte
 * Part 2: Remove all geo object type facets childs without an association to a Geo Object.
 * Öffnungszeiten, Kontakt and Web Resource
 */
public class Migration14 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    int facetTopicsDeletedCount = 0;

    @Override
    public void run() {
        logger.info("### Migration14: STARTing Facet Composition Cleanup ###");
        // Delete all left over file topics
        List<Topic> files = dm4.getTopicsByType("dm4.files.file");
        for (Topic file : files) {
            deleteIfNoParentGeoObject(file);
        }
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
        // ### Clean up ALL abandoned facet COMPOSITION topics once related to a Geo Object
        // Bezirk, Bezirskregion, LOR Nummer and Criterias are all AGGREGATED
        List<Topic> descriptions = dm4.getTopicsByType("ka2.beschreibung");
        for (Topic description : descriptions) {
            deleteIfNoParentGeoObject(description);
        }
        List<Topic> stichworte = dm4.getTopicsByType("ka2.stichworte");
        for (Topic stichwort : stichworte) {
            deleteIfNoParentGeoObject(stichwort);
        }
        List<Topic> sonstiges = dm4.getTopicsByType("ka2.sonstiges");
        for (Topic sonstige : sonstiges) {
            deleteIfNoParentGeoObject(sonstige);
        }
        logger.info("### Migration14 COMPLETED: Deleted "+facetTopicsDeletedCount+" abandoned composition topics ###");
    }

    private void deleteIfNoParentGeoObject(Topic entry) {
        Topic geoObject = entry.getRelatedTopic(null, "dm4.core.child", "dm4.core.parent", "ka2.geo_object");
        if (geoObject == null) {
            entry.delete();
            facetTopicsDeletedCount++;
        }
    }

}
