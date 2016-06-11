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
 * A migration for deleting the 2nd part of 1400 Geo Objects related to Pankow.
 */
public class Migration8 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {

        logger.info("##### Skipping Kiezatlas ETL Migration Nr. 2 for Pankow Removal #####");
        /** /int count = 1;
        int maxCount = 700;
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
                .toGMTString()); **/

    }

}
