package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.plugins.workspaces.WorkspacesService;

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
        // 1) Reduce Bild Facet Definition
        TopicType bildFacetType = dms.getTopicType("ka2.bild.facet");
        bildFacetType.removeAssocDef("dm4.files.file");
        Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        workspaceService.assignToWorkspace(bildFacetType, kiezatlas.getId());
        // 2) Assign new Topics Bezirke
        Topic marzahn = dms.getTopic("uri", new SimpleValue("ka2.bezirk.marzahn-hellersdorf"));
        workspaceService.assignToWorkspace(marzahn, kiezatlas.getId());
        logger.info("Assigned bezirk " + marzahn.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic reinickendorf = dms.getTopic("uri", new SimpleValue("ka2.bezirk.reinickendorf"));
        workspaceService.assignToWorkspace(reinickendorf, kiezatlas.getId());
        logger.info("Assigned bezirk " + reinickendorf.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        TopicType sprachenType = dms.getTopicType("ka2.criteria.sprachen");
        TopicType sprachenFacetType = dms.getTopicType("ka2.criteria.sprachen.facet");
        TopicType rollstuhlType = dms.getTopicType("ka2.criteria.rollstuhlgerecht");
        TopicType rollstuhlFacetType = dms.getTopicType("ka2.criteria.rollstuhlgerecht.facet");
        workspaceService.assignToWorkspace(sprachenType, kiezatlas.getId());
        workspaceService.assignToWorkspace(sprachenFacetType, kiezatlas.getId());
        workspaceService.assignToWorkspace(rollstuhlType, kiezatlas.getId());
        workspaceService.assignToWorkspace(rollstuhlFacetType, kiezatlas.getId());
        // 3) ###n Rollstuhlgerecht und Sprachen
        Topic vollRoll = dms.getTopic("uri", new SimpleValue("ka2.rollstuhlgerecht.voll"));
        logger.info("Assigned " + vollRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic teilweiseRoll = dms.getTopic("uri", new SimpleValue("ka2.rollstuhlgerecht.teilweise"));
        logger.info("Assigned " + teilweiseRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic nichtRoll = dms.getTopic("uri", new SimpleValue("ka2.rollstuhlgerecht.nicht"));
        logger.info("Assigned " + nichtRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic unbekanntRoll = dms.getTopic("uri", new SimpleValue("ka2.rollstuhlgerecht.unbekannt"));
        logger.info("Assigned " + unbekanntRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
    }

}
