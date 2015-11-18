package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;

import java.util.logging.Logger;
import java.util.Iterator;


public class Migration5 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {

        logger.info("##### SKIPPING Kiezatlas ETL Migration 5 due to performance issues. #####");

        /** Topic bezirkPankow = dms.getTopic("uri", new SimpleValue("ka2.bezirk.pankow"));
        ResultList<RelatedTopic> pankowEntries = bezirkPankow.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                "dm4.core.parent", "ka2.geo_object", 10);
        ResultList<RelatedTopic> testFetch = bezirkPankow.getRelatedTopics("dm4.core.aggregation", null, null, "ka2.geo_object", 10);
        logger.info("### Identified " + testFetch.getSize() + " geo objects in a test fetch #####");
        logger.info("### Identified " + pankowEntries.getSize() + " Related Pankow Geo Objects #####");
        Iterator<RelatedTopic> iterator = pankowEntries.getItems().iterator();
        while (iterator.hasNext()) {
            RelatedTopic entry = iterator.next();
            if (entry.getTypeUri().equals("ka2.geo_object")) {
                logger.info("####### >>  Deleting \"" + entry.getSimpleValue() + "\", " + entry.getAssociations()
                        .size() + " Associations");
                dms.deleteTopic(entry.getId());
            }
        }
        ResultList<RelatedTopic> regionenEntries = bezirkPankow.getRelatedTopics("dm4.core.association", "dm4.core" +
                ".default", "dm4.core.default", "ka2.bezirksregion", 0);
        logger.info("### Identified " + regionenEntries.getSize() + " Related Pankow Bezirksregionen #####");
        Iterator<RelatedTopic> iterato = pankowEntries.getItems().iterator();
        while (iterato.hasNext()) {
            RelatedTopic bezirksregion = iterato.next();
            if (bezirksregion.getTypeUri().equals("ka2.bezirksregion")) {
                logger.info("####### >>  Deleting \"" + bezirksregion.getSimpleValue() + "");
                dms.deleteTopic(bezirksregion.getId());
            }
        }
        dms.deleteTopic(bezirkPankow.getId());
        logger.info("######################### Pankow Data Removal complete " +  "#############################"); **/
    }

}
