package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;

import java.util.logging.Logger;



/*
 * Adapt "Bild Facet" TypeDefinition accordingly, in a seperate Transaction/Migration.
 */
public class Migration12 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject WorkspacesService workspaceService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        logger.info("#### Migration12 Started: Assignined Bild Facet Types to \"Kiezatlas\" Workspace ####");
        // 1) Reduce Bild Facet Definition
        TopicType bildFacetType = dm4.getTopicType("ka2.bild.facet");
        TopicType bildPfadFacet = dm4.getTopicType("ka2.bild.pfad");
        logger.info("Remove Assoc Def to Topic Type File ####");
        bildFacetType.removeAssocDef("dm4.files.file");
        // 2) Asign new "ka2.bild.pfad" ad bild facets to Workspace "Kiezatlas"
        Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        workspaceService.assignToWorkspace(bildFacetType, kiezatlas.getId());
        workspaceService.assignToWorkspace(bildPfadFacet, kiezatlas.getId());
        logger.info("#### Migration12 Completed: Assignined Bild Facet Types to \"Kiezatlas\" Workspace ####");
    }

}
