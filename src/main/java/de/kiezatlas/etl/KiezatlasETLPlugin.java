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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class KiezatlasETLPlugin extends PluginActivator implements KiezatlasETLService {

    private final Logger log = Logger.getLogger(getClass().getName());

    // --- Service Injection (and this class are just) here to provide these Services to Migration6 --- //

    @Inject private WorkspacesService workspaceService;
    @Inject private AccessControlService accessControlService;
    @Inject private KiezatlasService kiezService;
    @Inject private FacetsService facets;

    /**
     * Fetches a combined list of Geo Objects and Angebote to be displayed in two-tier dropdown menu.
     * @param referer
     * @param query
     * @return A list of topics which represent search input suggestions.
     */
    @GET
    @Path("/search/autocomplete/keywords")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Topic> getKeywordSuggestionsForSearch(@HeaderParam("Referer") String referer,
                                                      @QueryParam("query") String query) {
        if (!isValidReferer(referer)) throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        List<Topic> results = new ArrayList<Topic>();
        try {
            String queryValue = query.trim();
            if (queryValue.length() > 2) {
                // DO Search for "Not Empty" AND "WITH ASTERISK ON BOTH SIDES" in NAME ONLY
                log.log(Level.INFO, "> autoComplete keywordQuery for \"{0}\"", queryValue);
                List<Topic> themen = dm4.searchTopics(queryValue, THEMA_CRIT);
                List<Topic> angebote = dm4.searchTopics(queryValue, ANGEBOT_CRIT);
                List<Topic> zielgruppen = dm4.searchTopics(queryValue, ZIELGRUPPE_CRIT);
                // List<Topic> traeger = dm4.searchTopics(queryValue, TRAEGER_CRIT);
                // List<Topic> stichworte = dm4.searchTopics(queryValue, STICHWORTE);
                results.addAll(themen);
                results.addAll(angebote);
                results.addAll(zielgruppen);
                // resutls.addAll(stichworte);
            }
        } catch (Exception e) {
            throw new RuntimeException("Searching geo object for auto completion failed", e);
        }
        return results;
    }

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

    private boolean isValidReferer(String ref) {
        if (ref == null) return false;
        if (ref.contains(".kiezatlas.de/") || ref.contains("localhost:8080")) {
            return true;
        } else {
            log.warning("Invalid Request Referer \"" + ref + "\"");
            return false;
        }
    }

}
