package de.kiezatlas.etl.migrations;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.kiezatlas.KiezatlasService;
import java.util.List;

import java.util.logging.Logger;


/*
 * Remove all geo object type facets childs without an association to a Geo Object.
 * Attention: Rewrites all existing address topic data to be referecing the city of BERLIN!
 * WARNING: This migration rewrites all existing geo coordinate topics.
 */
public class Migration19 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    private final static String CITY_TYPE_URI = "dm4.contacts.city";
    private final static String ADDRESS_TYPE_URI = "dm4.contacts.address";
    private final static String BERLIN_TOPIC_URI = "ka2.city.berlin";

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        // ### Clean up ALL city entries and reference them to "Berlin"
        List<Topic> cities = dm4.getTopicsByType(CITY_TYPE_URI);
        for (Topic city : cities) {
            if (!city.getUri().equals(BERLIN_TOPIC_URI)) {
                List<RelatedTopic> addresses = city.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                    "dm4.core.parent", ADDRESS_TYPE_URI);
                for (RelatedTopic address : addresses) {
                    // Re-assign all existing address the city topic "Berlin"
                    address.getChildTopics().setRef(CITY_TYPE_URI, BERLIN_TOPIC_URI);
                    // If city was not initiated with an URI through a previous migration, we delete it
                    if (city.getUri().isEmpty()) city.delete();
                }
            }
        }
        logger.info("### DATA Migration13 COMPLETE: Cleaned up all existing address to be assigned to the topic "
            + "with URI: \""+BERLIN_TOPIC_URI+"\"");
    }

}
