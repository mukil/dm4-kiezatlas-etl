package de.kiezatlas.etl.migrations;

import de.deepamehta.core.model.IndexMode;
import de.deepamehta.core.service.Migration;
import java.util.List;

import java.util.logging.Logger;


/*
 * Introduces the IndexMode.FULLTEXT_KEY to Topic Types "Beschreibung", "Stichworte", "Sonstiges" und "Bezirksregion".
 * Required by the dm4-kiezatlas-website-0.2+ and its fulltext search.
 */
public class Migration9 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        logger.info("#### Migration9: Starting Fulltext Index to \"Geo Object\" child Topic Types ######");
        addIndexModeFulltextKey("ka2.beschreibung");
        addIndexModeFulltextKey("ka2.stichworte");
        // addIndexModeFulltextKey("ka2.sonstiges");
        addIndexModeFulltextKey("ka2.bezirksregion");
        addIndexModeFulltextKey("ka2.traeger.name");
        logger.info("#### Migration9: COMPLETE: Applied new Fulltext Indices to \"Geo Object\""
            + " child and facet types ###");
    }
    
    private void addIndexModeFulltextKey(String typeUri) {
        List<IndexMode> descrIdxModes = dm4.getTopicType(typeUri).getIndexModes();
        boolean isIndexedFulltextKey = false;
        for (IndexMode idxMode : descrIdxModes) {
            if (idxMode.equals(idxMode.FULLTEXT_KEY)) isIndexedFulltextKey = true;
        }
        if (!isIndexedFulltextKey) {
            dm4.getTopicType(typeUri).addIndexMode(IndexMode.FULLTEXT_KEY);
            logger.info("> Index Mode FULLTEXT_KEY UNAVAILABLE: Added index mode to " + typeUri);
        } else {
            logger.info("> Index Mode FULLTEXT_KEY on type \"" + typeUri + "\" already AVAILABLE - Doing nothing");
        }

    }

}
