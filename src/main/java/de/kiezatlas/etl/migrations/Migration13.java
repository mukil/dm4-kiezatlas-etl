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
 */
public class Migration13 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        // ### Clean up ALL city entries and reference them to "Berlin"
        List<Topic> cities = dm4.getTopicsByType("dm4.contacts.city");
        for (Topic city : cities) {
            if (!city.getUri().equals("ka2.city.berlin")) {
                List<RelatedTopic> addresses = city.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                    "dm4.core.parent", "dm4.contacts.address");
                for (RelatedTopic address : addresses) {
                    // Re-assign all existing address the city topic "Berlin"
                    address.getChildTopics().setRef("dm4.contacts.city", "ka2.city.berlin");
                    // If city was not initiated with an URI through a previous migration, we delete it
                    if (city.getUri().isEmpty()) city.delete();
                }
            }
        }
        logger.info("### DATA Migration13 COMPLETE: Cleaned up all existing address to be located in the city of BERLIN!");
    }

}
