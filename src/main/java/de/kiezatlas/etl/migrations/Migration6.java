package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.workspaces.WorkspacesService;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


public class Migration6 extends Migration {


    private Logger log = Logger.getLogger(getClass().getName());

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    @Inject
    private WorkspacesService workspaceService;

    // We introduce "Kiezatlas" Workspace to correct missing type assignments of dm4-kiezatlas (Migration3, 2.1.7)

    @Override
    public void run() {

        log.info("###### Introduce all Kiezatlas Application Types to the \"Kiezatlas\" Workspace ######");

        // 1) Assign all our types from migration1 to the "Kiezatlas" workspace so "admin" can edit these definitions
        Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        TopicType themaCriteria = dm4.getTopicType("ka2.criteria.thema.facet");
        TopicType thema = dm4.getTopicType("ka2.criteria.thema");
        TopicType angebotCriteria = dm4.getTopicType("ka2.criteria.angebot.facet");
        TopicType angebot = dm4.getTopicType("ka2.criteria.angebot");
        TopicType zielgruppeCriteria = dm4.getTopicType("ka2.criteria.zielgruppe.facet");
        TopicType zielgruppe = dm4.getTopicType("ka2.criteria.zielgruppe");
        TopicType traegerCriteria = dm4.getTopicType("ka2.criteria.traeger.facet");
        TopicType traeger = dm4.getTopicType("ka2.criteria.traeger");
        TopicType ueberregionalCriteria = dm4.getTopicType("ka2.criteria.ueberregional.facet");
        TopicType ueberregional = dm4.getTopicType("ka2.criteria.ueberregional");
        workspaceService.assignTypeToWorkspace(themaCriteria, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(thema, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(angebotCriteria, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(angebot, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(zielgruppeCriteria, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(zielgruppe, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(traegerCriteria, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(traeger, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(ueberregionalCriteria, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(ueberregional, kiezatlas.getId());

        // 2) Assign all our topics from migration2 to the "System" workspace so "admin" can edit these
        List<Topic> categories = dm4.getTopicsByValue("uri", new SimpleValue("ka2.category.*"));
        Iterator<Topic> i = categories.iterator();
        while (i.hasNext()) {
            Topic topic = i.next();
            workspaceService.assignToWorkspace(topic, kiezatlas.getId());
            log.info("Assigned category " + topic.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        }

        // 3) Assign all kiezatlas facets from migration3 to the "kiezatlas" workspace so "admin" can edit these defs
        // ### Träger
        log.info("### Assigning all Kiezatlas Facets to public workspace \"Kiezatlas\"");
        TopicType kaTraeger = dm4.getTopicType("ka2.traeger");
        TopicType kaTraegerName = dm4.getTopicType("ka2.traeger.name");
        TopicType kaTraegerArt = dm4.getTopicType("ka2.traeger.art");
        TopicType kaTraegerFacet = dm4.getTopicType("ka2.traeger.facet");
        workspaceService.assignTypeToWorkspace(kaTraeger, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kaTraegerName, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kaTraegerArt, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kaTraegerFacet, kiezatlas.getId());
        //
        TopicType oeffnungszeiten = dm4.getTopicType("ka2.oeffnungszeiten");
        TopicType oeffnungszeitenFacet = dm4.getTopicType("ka2.oeffnungszeiten.facet");
        workspaceService.assignTypeToWorkspace(oeffnungszeiten, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(oeffnungszeitenFacet, kiezatlas.getId());
        //
        TopicType kontakt = dm4.getTopicType("ka2.kontakt");
        TopicType kontaktMail = dm4.getTopicType("ka2.kontakt.email");
        TopicType kontaktAnsprechpartner = dm4.getTopicType("ka2.kontakt.ansprechpartner");
        TopicType kontaktFax = dm4.getTopicType("ka2.kontakt.fax");
        TopicType kontaktTelefon = dm4.getTopicType("ka2.kontakt.telefon");
        TopicType kontaktFacet = dm4.getTopicType("ka2.kontakt.facet");
        workspaceService.assignTypeToWorkspace(kontakt, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktMail, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktAnsprechpartner, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktFax, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktTelefon, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktFacet, kiezatlas.getId());
        //
        TopicType website = dm4.getTopicType("ka2.website.facet");
        workspaceService.assignTypeToWorkspace(website, kiezatlas.getId());
        //
        TopicType beschreibung = dm4.getTopicType("ka2.beschreibung");
        TopicType beschreibungFacet = dm4.getTopicType("ka2.beschreibung.facet");
        TopicType bildFacet = dm4.getTopicType("ka2.bild.facet");
        TopicType sonstiges = dm4.getTopicType("ka2.sonstiges");
        TopicType sonstigesFacet = dm4.getTopicType("ka2.sonstiges.facet");
        TopicType stichworte = dm4.getTopicType("ka2.stichworte");
        TopicType stichworteFacet = dm4.getTopicType("ka2.stichworte.facet");
        TopicType bezirk = dm4.getTopicType("ka2.bezirk");
        TopicType bezirkFacet = dm4.getTopicType("ka2.bezirk.facet");
        TopicType bezirksregion = dm4.getTopicType("ka2.bezirksregion");
        TopicType bezirksregionFacet = dm4.getTopicType("ka2.bezirksregion.facet");
        TopicType lorNummer = dm4.getTopicType("ka2.lor_nummer");
        TopicType lorNummerFacet = dm4.getTopicType("ka2.lor_nummer.facet");
        workspaceService.assignTypeToWorkspace(beschreibung, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(beschreibungFacet, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(bildFacet, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(sonstiges, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(sonstigesFacet, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(stichworte, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(stichworteFacet, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(bezirk, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(bezirkFacet, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(bezirksregion, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(bezirksregionFacet, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(lorNummer, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(lorNummerFacet, kiezatlas.getId());
        // 4) Assign all kiezatlas topics from migration3 to the "kiezatlas" workspace so "admin" can edit these
        // 4.1) Bezirke
        List<Topic> bezirke = dm4.getTopicsByValue("uri", new SimpleValue("ka2.bezirk.*"));
        Iterator<Topic> k = bezirke.iterator();
        while (k.hasNext()) {
            Topic topic = k.next();
            workspaceService.assignToWorkspace(topic, kiezatlas.getId());
            log.info("Assigned bezirk " + topic.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        }
        // 4.2) Bezirksregion
        List<Topic> bezirksregionen = dm4.getTopicsByValue("uri", new SimpleValue("ka2.bezirksregion.*"));
        Iterator<Topic> m = bezirksregionen.iterator();
        while (m.hasNext()) {
            Topic topic = m.next();
            workspaceService.assignToWorkspace(topic, kiezatlas.getId());
            log.info("Assigned bezirksregion " + topic.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        }

        log.info("###### Kiezatlas 2 Workspace<->Types Migration COMPLETE (used the \"Kiezatlas\" Workspace) ######");

    }
}
