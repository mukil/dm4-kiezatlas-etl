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
                    Topic incorrectCity = address.getChildTopics().getTopic("dm4.contacts.city");
                    address.getChildTopics().setRef("dm4.contacts.city", "ka2.city.berlin");
                    incorrectCity.delete();
                }
            } else {
                city.delete();
            }
        }
        logger.info("### Migration13 COMPLETE: Cleaned up all city topic to be BERLIN!");
    }

}
