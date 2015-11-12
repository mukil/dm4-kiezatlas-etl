package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;

import java.util.logging.Logger;


public class Migration7 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {

        Topic bezirkPankow = dms.getTopic("uri", new SimpleValue("ka2.bezirk.pankow"));
        ResultList<RelatedTopic> pankowEntries = bezirkPankow.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                "dm4.core.parent", "ka2.geo_object", 0);
        logger.info("### Identified " + pankowEntries.getSize() + " Related Pankow Geo Objects #####");
        for (RelatedTopic entry : pankowEntries.getItems()) {
            entry.loadChildTopics();
            if (!entry.getTypeUri().equals("ka2.geo_object")) {
                throw new RuntimeException("Related Topic is not of type Geo Object");
            }
            logger.info("## > Pankow Related Geo Object is " + entry.getSimpleValue());
        }
        ResultList<RelatedTopic> regionenEntries = bezirkPankow.getRelatedTopics("dm4.core.association", "dm4.core" +
                ".default", "dm4.core.default", "ka2.bezirksregion", 0);
        logger.info("### Identified " + regionenEntries.getSize() + " Related Pankow Bezirksregionen #####");
        for (RelatedTopic entry : regionenEntries.getItems()) {
            entry.loadChildTopics();
            if (!entry.getTypeUri().equals("ka2.bezirksregion")) {
                throw new RuntimeException("Related Topic is not of type Bezirksregion");
            }
            logger.info("## > Pankow Related Bezirksregion  is " + entry.getSimpleValue());
            dms.deleteTopic(entry.getId());
        }
        dms.deleteTopic(bezirkPankow.getId());
        logger.info("######################### Pankow Data Removal complete " +  "#############################");
    }

}
