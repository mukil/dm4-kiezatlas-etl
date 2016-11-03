package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * Assign Rollstuhlgerecht default topics (blatantly skipped in Migration16).
 */
public class Migration17 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject WorkspacesService workspaceService;
    @Inject KiezatlasService kiezatlasService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        Topic kiezatlas = workspaceService.getWorkspace(KiezatlasService.KIEZATLAS_WORKSPACE_URI);
        // 3) Assign Rollstuhlgerecht default topics too
        Topic vollRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.voll");
        workspaceService.assignToWorkspace(vollRoll, kiezatlas.getId());
        logger.info("Assigned " + vollRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic teilweiseRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.teilweise");
        workspaceService.assignToWorkspace(teilweiseRoll, kiezatlas.getId());
        logger.info("Assigned " + teilweiseRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic nichtRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.nicht");
        workspaceService.assignToWorkspace(nichtRoll, kiezatlas.getId());
        logger.info("Assigned " + nichtRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic unbekanntRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.unbekannt");
        workspaceService.assignToWorkspace(unbekanntRoll, kiezatlas.getId());
        logger.info("Assigned " + unbekanntRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
    }

}
