package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;
import de.kiezatlas.etl.KiezatlasETLService;
import java.util.List;
import java.util.logging.Logger;



/*
 * This migration is necessary to have kiezatlas1 images not stored as (first class) "File" topics in
 * DeepaMehta 4 / Kiezatlas 2 as these image files are not part of a local file repository managed by DM 4
 * but in a file repository of DeepaMehta 2 installation (and thus addressable only by remote URL).
 */
public class Migration11 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject KiezatlasETLService etlService;
    @Inject KiezatlasService kiezService;
    @Inject WorkspacesService workspaces;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        logger.info("#### Migration11 STARTED: Bild Facet \"File Topic to \"Bild Pfad\" Migration ####");
        // 1) Extend Bild Facet Definition
        TopicType bildFacetType = dm4.getTopicType("ka2.bild.facet");
        bildFacetType.addAssocDef(mf.newAssociationDefinitionModel("dm4.core.composition_def", "ka2.bild.facet",
           "ka2.bild.pfad", "dm4.core.one", "dm4.core.one"));
        // 2) Move all File Topics to a new Bild Facet Definition
        List<Topic> geoObjects = dm4.getTopicsByType("ka2.geo_object");
        for (Topic geoObject : geoObjects) {
            Topic fileFacetTopic = etlService.getImageFileFacetByGeoObject(geoObject);
            if (fileFacetTopic != null) {
                // 2.1) Construct new "Bild Pfad" value
                logger.info("Fetched Image File Topic Name \"" + fileFacetTopic.getSimpleValue() + "\"");
                String fileName = fileFacetTopic.getChildTopics().getString("dm4.files.file_name");
                String filePath = fileFacetTopic.getChildTopics().getString("dm4.files.path");
                // 2.2) Remove oudated file topic from Geo Object => Bild Facet
                fileFacetTopic.delete();
                // 2.3) Write new "Bild Pfad" facet
                etlService.updateImageFileFacet(geoObject, filePath + fileName);
            }
        }
        logger.info("#### Migration11 COMPLETED: ("+geoObjects.size()+") Geo Objects Bild Facet \"File\" topic"
            + "to \"Bild Pfad\" Migration ####");
    }

}
