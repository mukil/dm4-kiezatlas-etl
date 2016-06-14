package de.kiezatlas.etl.migrations;

import de.deepamehta.core.model.IndexMode;
import de.deepamehta.core.service.Migration;

import java.util.logging.Logger;


/*
 * Introduces the IndexMode.FULLTEXT_KEY for topic types "ka2.beschreibung" and "ka2.stichworte".
 * Required by the dm4-kiezatlas-website-0.2+ and its fulltext search.
 */
public class Migration5 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        logger.info("###### Kiezatlas ETL Migration 1: Add Index to \"Beschreibung\" und \"Stichworte\" ######");
        dm4.getTopicType("ka2.beschreibung").addIndexMode(IndexMode.FULLTEXT_KEY);
        dm4.getTopicType("ka2.stichworte").addIndexMode(IndexMode.FULLTEXT_KEY);

    }

}
