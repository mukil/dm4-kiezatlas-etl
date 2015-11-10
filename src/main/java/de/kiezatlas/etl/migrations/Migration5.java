package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.plugins.accesscontrol.AccessControlService;
import de.deepamehta.plugins.workspaces.WorkspacesService;


public class Migration5 extends Migration {

    @Inject
    private WorkspacesService workspaceService;

    @Inject
    private AccessControlService accessControlService;

    @Override
    public void run() {
        // 1) Assign all our types from migration1 to the "System" workspace so "admin" can edit these definitions
        Topic system = workspaceService.getWorkspace(accessControlService.SYSTEM_WORKSPACE_URI);
        TopicType themaFacet = dms.getTopicType("ka2.criteria.thema.facet");
        TopicType thema = dms.getTopicType("ka2.criteria.thema");
        TopicType angebotFacet = dms.getTopicType("ka2.criteria.angebot.facet");
        TopicType angebot = dms.getTopicType("ka2.criteria.angebot");
        TopicType zielgruppeFacet = dms.getTopicType("ka2.criteria.zielgruppe.facet");
        TopicType zielgruppe = dms.getTopicType("ka2.criteria.zielgruppe");
        TopicType traegerFacet = dms.getTopicType("ka2.criteria.traeger.facet");
        TopicType traeger = dms.getTopicType("ka2.criteria.traeger");
        TopicType ueberregionalFacet = dms.getTopicType("ka2.criteria.ueberregional.facet");
        TopicType ueberregional = dms.getTopicType("ka2.criteria.ueberregional");
        workspaceService.assignTypeToWorkspace(themaFacet, system.getId());
        workspaceService.assignTypeToWorkspace(thema, system.getId());
        workspaceService.assignTypeToWorkspace(angebotFacet, system.getId());
        workspaceService.assignTypeToWorkspace(angebot, system.getId());
        workspaceService.assignTypeToWorkspace(zielgruppeFacet, system.getId());
        workspaceService.assignTypeToWorkspace(zielgruppe, system.getId());
        workspaceService.assignTypeToWorkspace(traegerFacet, system.getId());
        workspaceService.assignTypeToWorkspace(traeger, system.getId());
        workspaceService.assignTypeToWorkspace(ueberregionalFacet, system.getId());
        workspaceService.assignTypeToWorkspace(ueberregional, system.getId());
        // 2) Assign all our topics from migration2 to the "System" workspace so "admin" can edit these
        // ###
        // 3) Assign all kiezatlas facets from migration3 to the "System" workspace so "admin" can edit these defs
        // ###
        // 4) Assign all kiezatlas topics from migration3 to the "System" workspace so "admin" can edit these
        // ###
    }
}
