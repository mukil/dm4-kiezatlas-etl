package de.kiezatlas.etl.migrations;

import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * Adapt "Bild Facet" TypeDefinition accordingly, in a seperate Transaction/Migration.
 */
public class Migration12 extends Migration {

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
