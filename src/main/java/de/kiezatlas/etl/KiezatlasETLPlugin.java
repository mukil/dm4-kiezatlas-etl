package de.kiezatlas.etl;

import de.deepamehta.core.osgi.PluginActivator;
import de.deepamehta.core.service.Inject;
import de.deepamehta.plugins.accesscontrol.AccessControlService;
import de.deepamehta.plugins.workspaces.WorkspacesService;


public class KiezatlasETLPlugin extends PluginActivator {

    // --- Service Injection (and this class are just) here to provide these Services to Migration6 --- //

    @Inject
    private WorkspacesService workspaceService;

    @Inject
    private AccessControlService accessControlService;

}
