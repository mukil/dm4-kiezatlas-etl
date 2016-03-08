package de.kiezatlas.etl.migrations;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;

import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;


/**
 * A DEFUSED migration to adjust geo object topics to more confidential workspace.
 * Todo: This turns out to be not the way to go.
 */
public class Migration8 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {

        logger.info("###### Turn the \"DeepaMehta\" Workspace SharingMode to \"Confidential\" ######");

        /** The following would lead to numerous "challenges" and 1 problem)
         * 0) Change the "DeepaMehta" Workspace SharingMode to "Confidential"
         * Topic deepaMehta = workspaceService.getWorkspace(WorkspacesService.DEEPAMEHTA_WORKSPACE_URI);
         * deepaMehta.getChildTopics().setRef("dm4.workspaces.sharing_mode", "dm4.workspaces.confidential"); **/

    }

}
