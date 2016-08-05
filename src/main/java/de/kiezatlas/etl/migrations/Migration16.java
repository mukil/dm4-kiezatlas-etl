package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * Assigns all Geo Objects assigned to a specific "Bezirk" topic to the new "Kiezatlas Website" topic.
 * Creates new Kiezatlas Website topic if none exists for the given Bezirk.
 */
public class Migration16 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject WorkspacesService workspaceService;
    @Inject KiezatlasService kiezatlasService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        Topic kiezatlas = workspaceService.getWorkspace(KiezatlasService.KIEZATLAS_WORKSPACE_URI);
        // 1) Assign new Bezirke topics to "Kiezatlas" Workspace
        Topic marzahn = dm4.getTopicByUri("ka2.bezirk.marzahn-hellersdorf");
        workspaceService.assignToWorkspace(marzahn, kiezatlas.getId());
        logger.info("Assigned bezirk " + marzahn.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic reinickendorf = dm4.getTopicByUri("ka2.bezirk.reinickendorf");
        workspaceService.assignToWorkspace(reinickendorf, kiezatlas.getId());
        logger.info("Assigned bezirk " + reinickendorf.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        // 2) Assign new Rollstuhlgerecht and Sprachen Types to "Kiezatlas" Workspace
        TopicType sprachenType = dm4.getTopicType("ka2.criteria.sprachen");
        TopicType sprachenFacetType = dm4.getTopicType("ka2.criteria.sprachen.facet");
        TopicType rollstuhlType = dm4.getTopicType("ka2.criteria.rollstuhlgerecht");
        TopicType rollstuhlFacetType = dm4.getTopicType("ka2.criteria.rollstuhlgerecht.facet");
        workspaceService.assignToWorkspace(sprachenType, kiezatlas.getId());
        workspaceService.assignToWorkspace(sprachenFacetType, kiezatlas.getId());
        workspaceService.assignToWorkspace(rollstuhlType, kiezatlas.getId());
        workspaceService.assignToWorkspace(rollstuhlFacetType, kiezatlas.getId());
        // 3) Assign Rollstuhlgerecht default topics too
        Topic vollRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.voll");
        logger.info("Assigned " + vollRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic teilweiseRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.teilweise");
        logger.info("Assigned " + teilweiseRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic nichtRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.nicht");
        logger.info("Assigned " + nichtRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic unbekanntRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.unbekannt");
        logger.info("Assigned " + unbekanntRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
    }

}
