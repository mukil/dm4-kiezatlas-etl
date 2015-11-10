package de.kiezatlas.etl.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.plugins.accesscontrol.AccessControlService;
import de.deepamehta.plugins.workspaces.WorkspacesService;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


public class Migration5 extends Migration {


    private Logger log = Logger.getLogger(getClass().getName());

    static final String KIEZATLAS_WORKSPACE_URI = "de.kiezatlas.workspace";

    @Inject
    private WorkspacesService workspaceService;

    @Inject
    private AccessControlService accessControlService;

    // ### Note: The "System" Workspace seems to be not the right place for our Kiezatlas types and topics as not
    // "everyone" can READ the type definitions.
    // ### Note: The "DeepaMehta" Workspace neiter, since this is where all the "Geo Objects" will live and normal user
    // will be member.

    @Override
    public void run() {

        // 1) Assign all our types from migration1 to the "Kiezatlas" workspace so "admin" can edit these definitions
        Topic kiezatlas = workspaceService.getWorkspace(KIEZATLAS_WORKSPACE_URI);
        TopicType themaCriteria = dms.getTopicType("ka2.criteria.thema.facet");
        TopicType thema = dms.getTopicType("ka2.criteria.thema");
        TopicType angebotCriteria = dms.getTopicType("ka2.criteria.angebot.facet");
        TopicType angebot = dms.getTopicType("ka2.criteria.angebot");
        TopicType zielgruppeCriteria = dms.getTopicType("ka2.criteria.zielgruppe.facet");
        TopicType zielgruppe = dms.getTopicType("ka2.criteria.zielgruppe");
        TopicType traegerCriteria = dms.getTopicType("ka2.criteria.traeger.facet");
        TopicType traeger = dms.getTopicType("ka2.criteria.traeger");
        TopicType ueberregionalCriteria = dms.getTopicType("ka2.criteria.ueberregional.facet");
        TopicType ueberregional = dms.getTopicType("ka2.criteria.ueberregional");
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
        List<Topic> categories = dms.getTopics("uri", new SimpleValue("ka2.category.*"));
        Iterator<Topic> i = categories.iterator();
        while (i.hasNext()) {
            Topic topic = i.next();
            workspaceService.assignToWorkspace(topic, kiezatlas.getId());
            log.info("Assigned category " + topic.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        }

        // 3) Assign all kiezatlas facets from migration3 to the "kiezatlas" workspace so "admin" can edit these defs
        // ### Träger
        TopicType kaTraeger = dms.getTopicType("ka2.traeger");
        TopicType kaTraegerName = dms.getTopicType("ka2.traeger.name");
        TopicType kaTraegerArt = dms.getTopicType("ka2.traeger.art");
        TopicType kaTraegerFacet = dms.getTopicType("ka2.traeger.facet");
        workspaceService.assignTypeToWorkspace(kaTraeger, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kaTraegerName, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kaTraegerArt, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kaTraegerFacet, kiezatlas.getId());
        //
        TopicType oeffnungszeiten = dms.getTopicType("ka2.oeffnungszeiten");
        TopicType oeffnungszeitenFacet = dms.getTopicType("ka2.oeffnungszeiten.facet");
        workspaceService.assignTypeToWorkspace(oeffnungszeiten, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(oeffnungszeitenFacet, kiezatlas.getId());
        //
        TopicType kontakt = dms.getTopicType("ka2.kontakt");
        TopicType kontaktMail = dms.getTopicType("ka2.kontakt.email");
        TopicType kontaktAnsprechpartner = dms.getTopicType("ka2.kontakt.ansprechpartner");
        TopicType kontaktFax = dms.getTopicType("ka2.kontakt.fax");
        TopicType kontaktTelefon = dms.getTopicType("ka2.kontakt.telefon");
        TopicType kontaktFacet = dms.getTopicType("ka2.kontakt.facet");
        workspaceService.assignTypeToWorkspace(kontakt, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktMail, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktAnsprechpartner, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktFax, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktTelefon, kiezatlas.getId());
        workspaceService.assignTypeToWorkspace(kontaktFacet, kiezatlas.getId());
        //
        TopicType website = dms.getTopicType("ka2.website.facet");
        workspaceService.assignTypeToWorkspace(website, kiezatlas.getId());
        //
        TopicType beschreibung = dms.getTopicType("ka2.beschreibung");
        TopicType beschreibungFacet = dms.getTopicType("ka2.beschreibung.facet");
        TopicType bildFacet = dms.getTopicType("ka2.bild.facet");
        TopicType sonstiges = dms.getTopicType("ka2.sonstiges");
        TopicType sonstigesFacet = dms.getTopicType("ka2.sonstiges.facet");
        TopicType stichworte = dms.getTopicType("ka2.stichworte");
        TopicType stichworteFacet = dms.getTopicType("ka2.stichworte.facet");
        TopicType bezirk = dms.getTopicType("ka2.bezirk");
        TopicType bezirkFacet = dms.getTopicType("ka2.bezirk.facet");
        TopicType bezirksregion = dms.getTopicType("ka2.bezirksregion");
        TopicType bezirksregionFacet = dms.getTopicType("ka2.bezirksregion.facet");
        TopicType lorNummer = dms.getTopicType("ka2.lor_nummer");
        TopicType lorNummerFacet = dms.getTopicType("ka2.lor_nummer.facet");
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
        // ### Bezirke
        List<Topic> bezirke = dms.getTopics("uri", new SimpleValue("ka2.bezirk.*"));
        Iterator<Topic> k = bezirke.iterator();
        while (k.hasNext()) {
            Topic topic = k.next();
            workspaceService.assignToWorkspace(topic, kiezatlas.getId());
            log.info("Assigned bezirk " + topic.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        }
        // ### Bezirksregion
        List<Topic> bezirksregionen = dms.getTopics("uri", new SimpleValue("ka2.bezirksregion.*"));
        Iterator<Topic> m = bezirke.iterator();
        while (m.hasNext()) {
            Topic topic = m.next();
            workspaceService.assignToWorkspace(topic, kiezatlas.getId());
            log.info("Assigned bezirksregion " + topic.getSimpleValue() + " to public workspace \"Kiezatlas\"");
        }
    }
}
