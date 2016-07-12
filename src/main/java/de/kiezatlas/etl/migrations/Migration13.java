package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Association;
import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;
import java.util.List;

import java.util.logging.Logger;



/*
 * Assigns topics "Berlin" and "Deutschland" to Workspace "Kiezatlas".
 */
public class Migration13 extends Migration {

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject WorkspacesService workspaces;
    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        logger.info("### Migration13: Assigning \"Deutschland\" + \"Berlin\" to the \"Kiezatlas\" Workspace ###");
        // 1) Assign topcis "Berlin" and "Deutschland" to "Kiezatlas"
        Topic de = dm4.getTopicByUri("ka2.country.deutschland");
        Topic kiezatlas = workspaces.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        workspaces.assignToWorkspace(de, kiezatlas.getId());
        Topic berlin = dm4.getTopicByUri("ka2.city.berlin");
        workspaces.assignToWorkspace(berlin, kiezatlas.getId());
        // 2) Fetch "DeepaMehta" Workspace, Do Assignment for new Fact Value Topic and Facet Value Topic Association
        Topic deepaMehtaWs = workspaces.getWorkspace(WorkspacesService.DEEPAMEHTA_WORKSPACE_URI);
        List<Topic> geoObjects = dm4.getTopicsByType("ka2.geo_object");
        for (Topic geoObject : geoObjects) {
            Topic fileFacetTopic = geoObject.getRelatedTopic("dm4.core.composition", "dm4.core.parent", "dm4.core.child", "ka2.bild.pfad");
            if (fileFacetTopic != null) {
                logger.info("Migration13: Assigning Bild Facet Value \""+fileFacetTopic.getSimpleValue().toString()+"\" to workspace \"DeepaMehta\" ");
                workspaces.assignToWorkspace(fileFacetTopic, deepaMehtaWs.getId());
                Association facetAssoc = dm4.getAssociation(null, fileFacetTopic.getId(), geoObject.getId(), null, null);
                workspaces.assignToWorkspace(facetAssoc, deepaMehtaWs.getId());
            }
        }
        logger.info("### Migration13 COMPLETED: Assigned Bild Facet Value and two mroe to their resp. Workspace ###");
    }

}
