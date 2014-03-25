package de.kiezatlas.etl.migrations;

import de.kiezatlas.etl.Transformation;

import de.deepamehta.core.Topic;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.service.Migration;

import java.util.Map;
import java.util.logging.Logger;



public class Migration4 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        checkMap(Transformation.CRITERIA_MAP);
        checkMap(Transformation.CATEGORY_MAP);
        logger.info("############################## Kiezatlas ETL migrations complete ##############################");
    }

    // ------------------------------------------------------------------------------------------------- Private Methods

    private void checkMap(Map<String, String> map) {
        for (String ka1TopicId : map.keySet()) {
            String ka2TopicUri = map.get(ka1TopicId);
            checkTopic(ka2TopicUri);
        }
    }

    private void checkTopic(String topicUri) {
        Topic topic = dms.getTopic("uri", new SimpleValue(topicUri), false);
        if (topic == null) {
            throw new RuntimeException("\"" + topicUri + "\" is an invalid URI");
        }
    }
}
