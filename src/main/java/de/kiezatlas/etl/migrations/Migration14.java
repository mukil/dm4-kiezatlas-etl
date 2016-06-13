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
public class Migration14 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    int facetTopicsDeletedCount = 0;

    @Override
    public void run() {
        // Delete all left over file topics
        ResultList<RelatedTopic> files = dms.getTopics("dm4.files.file", 0);
        for (RelatedTopic file : files) {
            deleteIfNoParentGeoObject(file);
        }
        // ### Clean up ALL abandoned facet COMPOSITION topics once related to a Geo Object
        // Bezirk, Bezirskregion, LOR Nummer and Criterias are all AGGREGATED
        ResultList<RelatedTopic> descriptions = dms.getTopics("ka2.beschreibung", 0);
        for (RelatedTopic description : descriptions) {
            deleteIfNoParentGeoObject(description);
        }
        ResultList<RelatedTopic> stichworte = dms.getTopics("ka2.stichworte", 0);
        for (RelatedTopic stichwort : stichworte) {
            deleteIfNoParentGeoObject(stichwort);
        }
        ResultList<RelatedTopic> sonstiges = dms.getTopics("ka2.sonstiges", 0);
        for (RelatedTopic sonstige : sonstiges) {
            deleteIfNoParentGeoObject(sonstige);
        }
        logger.info("### Migration14 COMPLETE: Cleaned up (deleted) "+facetTopicsDeletedCount+" abandoned (COMPOSITION) facet topics!");
    }

    private void deleteIfNoParentGeoObject(Topic entry) {
        Topic geoObject = entry.getRelatedTopic(null, "dm4.core.child", "dm4.core.parent", "ka2.geo_object");
        if (geoObject == null) {
            entry.delete();
            facetTopicsDeletedCount++;
        }
    }

}
