package de.kiezatlas.etl.migrations;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;
import de.kiezatlas.KiezatlasService;
import java.util.HashMap;

import java.util.logging.Logger;


/*
 * Assigns all Geo Objects assigned to a specific "Bezirk" topic to the new "Kiezatlas Website" topic.
 * Creates new Kiezatlas Website topic if none exists for the given Bezirk.
 */
public class Migration15 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        /** logger.info("##### Starting Bezirks-Topic to Site-Topci Migration #####");
        /** HashMap<Long, Topic> bezirksWebsiteTopics = new HashMap<Long, Topic>();
        ResultList<RelatedTopic> bezirke = dms.getTopics("ka2.bezirk", 0);
        // 1) Create one site topic for each bezirks topic (### Lets do this via our migrator, from KA 1)
        for (Topic bezirk : bezirke) {
            logger.info("Bezirks Topic Debug: " + bezirk.getUri() + "\"");
            String newSiteUri = "de.kiezatlas.site_" + bezirk.getUri();
            Topic newKiezatlasSite = kiezService.createWebsite(bezirk.getSimpleValue() + " Site", newSiteUri);
            logger.info("Creating new Kiezatlas Website \"" + newKiezatlasSite.getSimpleValue() + "\"");
            bezirksWebsiteTopics.put(bezirk.getId(), newKiezatlasSite);
        }
        // 2) Assign all geo objects from bezirk to their new, corresponding site topic.
        for (Topic bezirk : bezirke) {
            ResultList<RelatedTopic> geoObjects = kiezService.getGeoObjectsByBezirkFacet(bezirk);
            Topic bezirksWebsite = bezirksWebsiteTopics.get(bezirk.getId());
            for (Topic geoObject : geoObjects) {
                kiezService.addGeoObjectToWebsite(geoObject.getId(), bezirksWebsite.getId());
                logger.info("Migrating Geo Object " + geoObject.getSimpleValue() + " from Bezirk "
                    + bezirk.getSimpleValue() + " to Website \"" + bezirksWebsite.getSimpleValue() + "\"");
            }
        } **/
        // 3) ### Assign Bezirksregion topics to Site topics (via Bezirk)
    }

}
