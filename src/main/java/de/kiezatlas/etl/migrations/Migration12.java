package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Association;
import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;
import java.util.List;

import java.util.logging.Logger;



/*
 * Adapt "Bild Facet" TypeDefinition accordingly, in a seperate Transaction/Migration.
 */
public class Migration12 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;
    @Inject WorkspacesService workspaces;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        // 1) Reduce Bild Facet Definition
        logger.info("#### Migration12 Started: Assignined Bild Facet Types to \"Kiezatlas\" Workspace ####");
        TopicType bildFacetType = dm4.getTopicType("ka2.bild.facet");
        TopicType bildPfadFacet = dm4.getTopicType("ka2.bild.pfad");
        bildFacetType.removeAssocDef("dm4.files.file");
        logger.info("### Removed Assoc Def to Topic Type File ####");
        // 3) Asign new "ka2.bild.pfad" ad bild facets to Workspace "Kiezatlas"
        Topic kiezatlas = workspaces.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        workspaces.assignToWorkspace(bildFacetType, kiezatlas.getId());
        workspaces.assignToWorkspace(bildPfadFacet, kiezatlas.getId());
        logger.info("### Asigned Assoc Def to Workspace ####");
        // 2) Fetch "DeepaMehta" standard workspace
        Topic deepaMehtaWs = workspaces.getWorkspace(WorkspacesService.DEEPAMEHTA_WORKSPACE_URI);
        // 2) Workspace Assignment for new Fact Value Topic and Facet Value Topic Association
        List<Topic> geoObjects = dm4.getTopicsByType("ka2.geo_object");
        for (Topic geoObject : geoObjects) {
            Topic fileFacetTopic = geoObject.getRelatedTopic("dm4.core.composition", "dm4.core.parent", "dm4.core.child", "ka2.bild.pfad");
            if (fileFacetTopic != null) {
                logger.info("Migration12: Assigning Bild Facet Value \""+fileFacetTopic.getSimpleValue().toString()+"\" to workspace \"DeepaMehta\" ");
                workspaces.assignToWorkspace(fileFacetTopic, deepaMehtaWs.getId());
                Association facetAssoc = dm4.getAssociation(null, fileFacetTopic.getId(), geoObject.getId(), null, null);
                workspaces.assignToWorkspace(facetAssoc, deepaMehtaWs.getId());
            } else {
                logger.info("Migration12: Geo Object has NO Bild Facet Value Topic - NOTHING TO MIGRATE");
            }
        }
        logger.info("#### Migration12 Completed: Assignined Bild Facet Types to \"Kiezatlas\" Workspace ####");
    }

}
