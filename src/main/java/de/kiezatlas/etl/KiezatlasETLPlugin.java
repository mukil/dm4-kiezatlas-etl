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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;



public class KiezatlasETLPlugin extends PluginActivator implements KiezatlasETLService {

    private final Logger log = Logger.getLogger(getClass().getName());

    // --- Service Injection (and this class are just) here to provide these Services to Migration6 --- //

    @Inject private WorkspacesService workspaceService;
    @Inject private AccessControlService accessControlService;
    @Inject private KiezatlasService kiezService;
    @Inject private FacetsService facets;

    @Override
    public List<Topic> searchFulltextInCategories(@HeaderParam("Referer") String referer,
                                                  @QueryParam("query") String query) {
        if (!isValidReferer(referer)) throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        HashMap<Long, Topic> uniqueResults = new HashMap<Long, Topic>();
        String queryString = prepareLuceneQueryString(query, false, true, false, true);
        if (queryString != null) {
            List<Topic> themen = dm4.searchTopics(queryString, THEMA_CRIT);
            for (Topic thema : themen) {
                List<RelatedTopic> geoObjects = thema.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                    "dm4.core.parent", KiezatlasService.GEO_OBJECT);
                log.info("Fetching "  + geoObjects.size() + " Orte \""+queryString+"\" in Thema \"" + thema.getSimpleValue()+ "\"");
                addGeoObjectsToResults(uniqueResults, geoObjects);
            }
            List<Topic> angebote = dm4.searchTopics(queryString, ANGEBOT_CRIT);
            for (Topic angebot : angebote) {
                List<RelatedTopic> geoObjects = angebot.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                    "dm4.core.parent", KiezatlasService.GEO_OBJECT);
                log.info("Fetching " + geoObjects.size() + " Orte \""+queryString+"\" in Angebot \"" + angebot.getSimpleValue()+ "\" ");
                addGeoObjectsToResults(uniqueResults, geoObjects);
            }
            List<Topic> zielgruppen = dm4.searchTopics(queryString, ZIELGRUPPE_CRIT);
            for (Topic zielgruppe : zielgruppen) {
                List<RelatedTopic> geoObjects = zielgruppe.getRelatedTopics("dm4.core.aggregation", "dm4.core.child",
                    "dm4.core.parent", KiezatlasService.GEO_OBJECT);
                log.info("Fetching " + geoObjects.size() + " Orte \""+queryString+"\" in Zielgruppe \"" + zielgruppe.getSimpleValue() + "\" ");
                addGeoObjectsToResults(uniqueResults, geoObjects);
            }
        }
        return new ArrayList(uniqueResults.values());
    }

    /**
     * Fetches a list of all kiezatlas criterias (category names).
     * @param referer
     * @param query
     * @return A list of topics which represent search input suggestions.
     */
    @Override
    public List<Topic> searchCategoryNames(@HeaderParam("Referer") String referer, @QueryParam("query") String query) {
        if (!isValidReferer(referer)) throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        List<Topic> results = new ArrayList<Topic>();
        try {
            String queryValue = prepareLuceneQueryString(query, false, true, false, true);
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
    public Topic getImageFileFacetByGeoObject(Topic geoObject) {
        return facets.getFacet(geoObject, IMAGE_FACET);
    }

    /** Note: This facet depends on the installation of the dm4-kiezatlas-etl plugin. */
    @Override
    public void updateImageFileFacet(Topic geoObject, String imageFilePath) {
        facets.updateFacet(geoObject, IMAGE_FACET,
            mf.newFacetValueModel(IMAGE_PATH).put(imageFilePath));
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

    private void addGeoObjectsToResults(HashMap<Long, Topic> uniqueResults, List<RelatedTopic> geoObjects) {
        for (Topic geoObject : geoObjects) {
            if (geoObject != null && !uniqueResults.containsKey(geoObject.getId())) {
                uniqueResults.put(geoObject.getId(), geoObject);
            }
        }
    }

    /** Find 1:1 copy in dm4-kiezatlas-angebote plugin */
    private String prepareLuceneQueryString(String userQuery, boolean doSplitWildcards,
                                            boolean appendWildcard, boolean doExact, boolean leadingWildcard) {
        String queryPhrase = new String();
        // 1) split query input by whitespace and append a wildcard to each term
        if (doSplitWildcards) {
            String[] terms = userQuery.split(" ");
            int count = 1;
            for (String term : terms) {
                if (appendWildcard && !term.isEmpty()) {
                    queryPhrase += term + "* ";
                } else if (!term.isEmpty()) {
                    queryPhrase += term;
                    if (terms.length < count) queryPhrase += " ";
                }
                count++;
            }
            queryPhrase = queryPhrase.trim();
        } else if (doExact) {
            // 3) remove (potential "?", introduced as trigger for exact search), quote query input and append fuzzy command
            queryPhrase = userQuery.trim().replaceAll("\\?", "");
            queryPhrase = "\"" + queryPhrase + "\"~0.9";
        } else if (!doSplitWildcards && !appendWildcard  && !leadingWildcard) {
            // 4) if none, return trimmed user query input
            queryPhrase = userQuery.trim();
        } else if (appendWildcard && leadingWildcard && !doSplitWildcards) {
            queryPhrase = "*" + userQuery.trim() + "*";
        } else if (appendWildcard && !doSplitWildcards) {
            // 2) trim and append a wildcard to the query input
            queryPhrase = userQuery.trim() + "*";
        } else if (leadingWildcard && !doSplitWildcards) {
            queryPhrase = "*" + userQuery.trim();
        }
        log.info("Prepared Query Phrase \""+userQuery+"\" => \""+queryPhrase+"\" (doSplitWildcards: "
            + doSplitWildcards + ", appendWildcard: " + appendWildcard + ", leadingWildcard: "
            + leadingWildcard +", doExact: " + doExact + ")");
        return queryPhrase;
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
