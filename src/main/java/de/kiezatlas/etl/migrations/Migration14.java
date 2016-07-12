package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;



/*
 * Assigns topics "Berlin" and "Deutschland" to Workspace "Kiezatlas".
 */
public class Migration14 extends Migration {

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject WorkspacesService workspaceService;
    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        logger.info("### Migration14: Assiging topics (\"Deutschland\" und \"Berlin\") to the Kiezatlas Workspace ###");
        // 1) Assign topcis "Berlin" and "Deutschland" to "Kiezatlas"
        Topic de = dm4.getTopicByUri("ka2.country.deutschland");
        Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        workspaceService.assignToWorkspace(de, kiezatlas.getId());
        Topic berlin = dm4.getTopicByUri("ka2.city.berlin");
        workspaceService.assignToWorkspace(berlin, kiezatlas.getId());
        logger.info("### Migration14: COMPLETED ###");
    }

}
