package de.kiezatlas.etl.migrations;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * Remove all geo object type facets childs without an association to a Geo Object.
 */
public class Migration15 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    int facetTopicsDeletedCount = 0;

    @Override
    public void run() {
        // ### Clean up ALL abandoned facet COMPOSITION topics once related to a Geo Object
        ResultList<RelatedTopic> openingHours = dms.getTopics("ka2.oeffnungszeiten", 0);
        for (RelatedTopic openingHour : openingHours) {
            deleteIfNoParentGeoObject(openingHour);
        }
        ResultList<RelatedTopic> contactEntries = dms.getTopics("ka2.kontakt", 0);
        for (RelatedTopic contactEntry : contactEntries) {
            deleteIfNoParentGeoObject(contactEntry);
        }
        ResultList<RelatedTopic> websiteTopics = dms.getTopics("dm4.webbrowser.web_resource", 0);
        for (RelatedTopic websiteTopic : websiteTopics) {
            deleteIfNoParentGeoObject(websiteTopic);
        }
        logger.info("### Migration15 Part 1: COMPLETE: Cleaned up (deleted) "+facetTopicsDeletedCount+" abandoned (COMPOSITION) facet topics!");
    }

    private void deleteIfNoParentGeoObject(Topic entry) {
        Topic geoObject = entry.getRelatedTopic(null, "dm4.core.child", "dm4.core.parent", "ka2.geo_object");
        if (geoObject == null) {
            entry.delete();
            facetTopicsDeletedCount++;
        }
    }

}