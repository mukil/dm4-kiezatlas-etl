package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * Assigns all Geo Objects assigned to a specific "Bezirk" topic to the new "Kiezatlas Website" topic.
 * Creates new Kiezatlas Website topic if none exists for the given Bezirk.
 */
public class Migration18 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        /** // 1) Assign new Bezirke topics to "Kiezatlas" Workspace
        Topic marzahn = dm4.getTopicByUri("ka2.bezirk.marzahn-hellersdorf");
        workspaceService.assignToWorkspace(marzahn, kiezatlas.getId());
        logger.info("Assigned bezirk " + marzahn.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic reinickendorf = dm4.getTopicByUri("ka2.bezirk.reinickendorf");
        workspaceService.assignToWorkspace(reinickendorf, kiezatlas.getId());
        logger.info("Assigned bezirk " + reinickendorf.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        // 2) Assign new Rollstuhlgerecht and Sprachen Types to "Kiezatlas" Workspace
        TopicType sprachenType = dm4.getTopicType("ka2.criteria.sprachen");
        TopicType sprachenFacetType = dm4.getTopicType("ka2.criteria.sprachen.facet");
        TopicType rollstuhlType = dm4.getTopicType("ka2.criteria.rollstuhlgerecht");
        TopicType rollstuhlFacetType = dm4.getTopicType("ka2.criteria.rollstuhlgerecht.facet");
        workspaceService.assignToWorkspace(sprachenType, kiezatlas.getId());
        workspaceService.assignToWorkspace(sprachenFacetType, kiezatlas.getId());
        workspaceService.assignToWorkspace(rollstuhlType, kiezatlas.getId());
        workspaceService.assignToWorkspace(rollstuhlFacetType, kiezatlas.getId());
        // 3) Assign Rollstuhlgerecht default topics too
        Topic vollRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.voll");
        logger.info("Assigned " + vollRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic teilweiseRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.teilweise");
        logger.info("Assigned " + teilweiseRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic nichtRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.nicht");
        logger.info("Assigned " + nichtRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        Topic unbekanntRoll = dm4.getTopicByUri("ka2.rollstuhlgerecht.unbekannt");
        logger.info("Assigned " + unbekanntRoll.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        logger.info("##### Starting Bezirks-Topic to Site-Topci Migration #####");
        HashMap<Long, Topic> bezirksWebsiteTopics = new HashMap<Long, Topic>();
        ResultList<RelatedTopic> bezirke = dms.getTopics("ka2.bezirk", 0);
        // 4) Create one site topic for each bezirks topic (### Lets do this via our migrator, from KA 1)
        for (Topic bezirk : bezirke) {
            logger.info("Bezirks Topic Debug: " + bezirk.getUri() + "\"");
            String newSiteUri = "de.kiezatlas.site_" + bezirk.getUri();
            Topic newKiezatlasSite = kiezService.createWebsite(bezirk.getSimpleValue() + " Site", newSiteUri);
            logger.info("Creating new Kiezatlas Website \"" + newKiezatlasSite.getSimpleValue() + "\"");
            bezirksWebsiteTopics.put(bezirk.getId(), newKiezatlasSite);
        }
        // 5) Assign all geo objects from bezirk to their new, corresponding site topic.
        for (Topic bezirk : bezirke) {
            ResultList<RelatedTopic> geoObjects = kiezService.getGeoObjectsByBezirkFacet(bezirk);
            Topic bezirksWebsite = bezirksWebsiteTopics.get(bezirk.getId());
            for (Topic geoObject : geoObjects) {
                kiezService.addGeoObjectToWebsite(geoObject.getId(), bezirksWebsite.getId());
                logger.info("Migrating Geo Object " + geoObject.getSimpleValue() + " from Bezirk "
                    + bezirk.getSimpleValue() + " to Website \"" + bezirksWebsite.getSimpleValue() + "\"");
            }
        } **/
        // 6) ### Assign Bezirksregion topics to Site topics (via Bezirk)
    }

}
