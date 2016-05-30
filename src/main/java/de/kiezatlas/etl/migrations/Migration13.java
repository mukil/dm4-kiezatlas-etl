package de.kiezatlas.etl.migrations;

import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.model.AssociationDefinitionModel;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.ResultList;
import de.kiezatlas.KiezatlasService;

import java.util.logging.Logger;


/*
 * DEFUSED: Assigns all Geo Objects assigned to a specific "Bezirk" topic to the new "Kiezatlas Website" topic.
 * Creates new Kiezatlas Website topic if none exists for the given Bezirk.
 */
public class Migration13 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasService kiezService;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        // 1) Extend Bild Facet Definition
        TopicType bildFacetType = dms.getTopicType("ka2.bild.facet");
        // ### Asign new Facet to Workspace "ka2.bild.pfad
        // Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        bildFacetType.addAssocDef(new AssociationDefinitionModel("dm4.core.composition_def", "ka2.bild.facet",
           "ka2.bild.pfad", "dm4.core.one", "dm4.core.one"));
        // 2) Move all File Topics to a new Bild Facet Definition
        ResultList<RelatedTopic> geoObjects = dms.getTopics("ka2.geo_object", 0);
        for (RelatedTopic geoObject : geoObjects) {
            Topic fileFacetTopic = kiezService.getImageFileFacetByGeoObject(geoObject);
            if (fileFacetTopic != null) {
                // 2.1) Construct new "Bild Pfad" value
                logger.info("Fetched Image File Topic" + fileFacetTopic.getSimpleValue());
                String fileName = fileFacetTopic.getChildTopics().getString("dm4.files.file_name");
                String filePath = fileFacetTopic.getChildTopics().getString("dm4.files.path");
                // 2.2) Remove oudated file topic
                fileFacetTopic.delete();
                // 2.3) Write new "Bild Pfad" facet
                kiezService.updateImageFileFacet(geoObject, filePath + fileName);
            }
        }
    }

}
