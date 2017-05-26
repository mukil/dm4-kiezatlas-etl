package de.kiezatlas.etl;

import de.deepamehta.core.osgi.PluginActivator;
import de.deepamehta.core.service.Inject;
import de.deepamehta.accesscontrol.AccessControlService;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.facets.FacetsService;
import de.deepamehta.workspaces.WorkspacesService;
import de.kiezatlas.KiezatlasService;
import java.util.ArrayList;
import java.util.List;


public class KiezatlasETLPlugin extends PluginActivator implements KiezatlasETLService {

    // --- Service Injection (and this class are just) here to provide these Services to Migration6 --- //

    @Inject private WorkspacesService workspaceService;

    @Inject private AccessControlService accessControlService;

    @Inject private KiezatlasService kiezService;

    @Inject private FacetsService facets;

    @Override
    public Topic getFacetBeschreibung(Topic geoObject) {
        return facets.getFacet(geoObject, BESCHREIBUNG_FACET);
    }

    @Override
    public Topic getFacetStichworte(Topic geoObject) {
        return facets.getFacet(geoObject, STICHWORTE_FACET);
    }

    @Override
    public List<RelatedTopic> getAllCategories(Topic geoObject) {
        List<RelatedTopic> cats = new ArrayList<RelatedTopic>();
        List<RelatedTopic> themen = facets.getFacets(geoObject, THEMA_FACET);
        List<RelatedTopic> zielgruppen = facets.getFacets(geoObject, ZIELGRUPPE_FACET);
        List<RelatedTopic> angebot = facets.getFacets(geoObject, ANGEBOT_FACET);
        cats.addAll(themen);
        cats.addAll(zielgruppen);
        cats.addAll(angebot);
        return cats;
    }

}
