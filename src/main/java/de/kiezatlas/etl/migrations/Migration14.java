package de.kiezatlas.etl.migrations;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.model.AssociationDefinitionModel;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * DEFUSED: Assigns all Geo Objects assigned to a specific "Bezirk" topic to the new "Kiezatlas Website" topic.
 * Creates new Kiezatlas Website topic if none exists for the given Bezirk.
 */
public class Migration14 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        // 1) Reduce Bild Facet Definition
        TopicType bildFacetType = dms.getTopicType("ka2.bild.facet");
        bildFacetType.removeAssocDef("dm4.files.file");
    }

}
