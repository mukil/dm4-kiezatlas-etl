package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;

import java.util.Date;
import java.util.logging.Logger;
import java.util.Iterator;


/**
 * A DEFUSED migration since it will take ages to complete, thus it is yet untested.
 */
public class Migration7 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {

        logger.info("##### Starting Kiezatlas ETL Migration for Pankow Removal #####");
        int count = 1;
        int maxCount = 100;
        long started = new Date().getTime();
        Topic bezirkPankow = dms.getTopic("uri", new SimpleValue("ka2.bezirk.pankow"));
        ResultList<RelatedTopic> pankowEntries = bezirkPankow.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                "dm4.core.parent", "ka2.geo_object", 0);
        logger.info("### Identified " + pankowEntries.getSize() + " Related Pankow Geo Objects #####");
        Iterator<RelatedTopic> iterator = pankowEntries.getItems().iterator();
        while (iterator.hasNext()) {
            if (count > maxCount) break;
            RelatedTopic entry = iterator.next();
            if (entry.getTypeUri().equals("ka2.geo_object")) {
                int assocSize = entry.getAssociations().size();
                String entryName = entry.getSimpleValue().toString();
                dms.deleteTopic(entry.getId());
                logger.info("#### > Deleted Nr. "+count+" \"" + entryName + "\", including " + assocSize + " " +
                        "Associations");
            }
            count++;
        }
        logger.info("### Breaking out of pankow iteration - Deleted "+maxCount+ " Geo Objects related to Pankow ###");
        logger.info("### ETL Pankow migration finished at "+new Date().toGMTString()+", started at " + new Date(started)
                .toGMTString());
        /** ResultList<RelatedTopic> regionenEntries = bezirkPankow.getRelatedTopics("dm4.core.association", "dm4
         * .core" +
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
