package de.kiezatlas.etl;

import java.util.HashMap;
import java.util.Map;



public class Transformation {

    public static Map<String, String> CRITERIA_MAP = new HashMap();
    public static Map<String, String> CATEGORY_MAP = new HashMap();

    static {
        putCrit("t-253478",  "ka2.criteria.kategorie.facet");      // Mitte
        putCrit("t-239975",  "ka2.criteria.kategorie.facet");      // FrKr
        putCrit("t-253482",  "ka2.criteria.angebot.facet");        // Mitte
        putCrit("t-239977",  "ka2.criteria.angebot.facet");        // FrKr
        putCrit("t-253480",  "ka2.criteria.zielgruppe.facet");     // Mitte
        putCrit("t-239979",  "ka2.criteria.zielgruppe.facet");     // FrKr
        putCrit("t-1220610", "ka2.criteria.ueberregional.facet");  // FrKr
    }

    static {
        // Kategorien
        putCat("t-253525", "ka2.category.ausbildung_und_arbeit");                                 // Mitte
        putCat("t-240011", "ka2.category.ausbildung_und_arbeit");                                 // FrKr
        putCat("t-253526", "ka2.category.beratung");                                              // Mitte
        putCat("t-240012", "ka2.category.beratung");                                              // FrKr
        putCat("t-253527", "ka2.category.familie");                                               // Mitte
        putCat("t-240013", "ka2.category.familie");                                               // FrKr
        putCat("t-507124", "ka2.category.frauen");                                                // Mitte
        putCat("t-240014", "ka2.category.gesundheit");                                            // FrKr
        putCat("t-253528", "ka2.category.gesundheit_und_behinderung");                            // Mitte
        putCat("t-240016", "ka2.category.jugend");                                                // FrKr
        putCat("t-253530", "ka2.category.jugendamt");                                             // Mitte
        putCat("t-240017", "ka2.category.jugendamt");                                             // FrKr
        putCat("t-253529", "ka2.category.kinder_und_jugendfreizeit");                             // Mitte
        putCat("t-240018", "ka2.category.kinder");                                                // FrKr
        putCat("t-253531", "ka2.category.kinderbetreuung");                                       // Mitte
        putCat("t-240019", "ka2.category.kinderbetreuung");                                       // FrKr
        putCat("t-253532", "ka2.category.kultur_und_bildung");                                    // Mitte
        putCat("t-240020", "ka2.category.kultur_und_bildung");                                    // FrKr
        putCat("t-240015", "ka2.category.menschen_mit_behinderungen");                            // FrKr
        putCat("t-253533", "ka2.category.nachbarschaftstreffpunkte_und_stadtteilarbeit");         // Mitte
        putCat("t-240021", "ka2.category.nachbarschaftstreffpunkte_und_stadtteilarbeit");         // FrKr
        putCat("t-266441", "ka2.category.netzwerk");                                              // Mitte
        putCat("t-253534", "ka2.category.notdienste");                                            // Mitte
        putCat("t-240022", "ka2.category.notdienste");                                            // FrKr
        putCat("t-253535", "ka2.category.schule");                                                // Mitte
        putCat("t-240023", "ka2.category.schule");                                                // FrKr
        putCat("t-266443", "ka2.category.seniorinnen_und_senioren");                              // Mitte
        putCat("t-240026", "ka2.category.seniorinnen_und_senioren");                              // FrKr
        putCat("t-240025", "ka2.category.spezielle_angebote_fuer_migranten_und_migrantinnen");    // FrKr
        putCat("t-266440", "ka2.category.sport");                                                 // Mitte
        putCat("t-240024", "ka2.category.sport");                                                 // FrKr
        putCat("t-266442", "ka2.category.wohnung_und_unterkunft");                                // Mitte

        // Angebote
        putCat("t-253548", "ka2.category.angebote_fuer_menschen_mit_behinderung");                // Mitte
        putCat("t-240059", "ka2.category.angebote_fuer_menschen_mit_behinderung");                // FrKr
        putCat("t-240041", "ka2.category.angebote_in_familienbildung_elternbildung_familienselbsthilfe_familienberatung_familienerholung");   // FrKr
        putCat("t-240083", "ka2.category.babysitter");                                            // FrKr
        putCat("t-240038", "ka2.category.beratung_bei_trennung_und_scheidung");                   // FrKr
        putCat("t-240094", "ka2.category.beratung_und_hilfe_in_krisensituationen");               // FrKr
        putCat("t-253549", "ka2.category.beratungsangebote");                                     // Mitte
        putCat("t-240028", "ka2.category.berufsausbildung");                                      // FrKr
        putCat("t-240036", "ka2.category.berufsberatung_und_orientierung");                       // FrKr
        putCat("t-240065", "ka2.category.berufsberatung_und_orientierung");                       // FrKr
        putCat("t-240034", "ka2.category.drogenberatung");                                        // FrKr
        putCat("t-240058", "ka2.category.drogenentzugseinrichtung");                              // FrKr
        putCat("t-266732", "ka2.category.eltern_kind_gruppen");                                   // FrKr
        putCat("t-240060", "ka2.category.fallmanagement_jugendamt_behindertenhilfe");             // FrKr
        putCat("t-253550", "ka2.category.erziehungs_und_familienberatung");                       // Mitte
        putCat("t-240070", "ka2.category.erziehungs_und_familienberatung");                       // FrKr
        putCat("t-240040", "ka2.category.erziehungs_und_familienberatung");                       // FrKr
        putCat("t-240043", "ka2.category.familiencafes");                                         // FrKr
        putCat("t-240049", "ka2.category.familienkasse");                                         // FrKr
        putCat("t-240042", "ka2.category.familienzentren");                                       // FrKr
        putCat("t-240030", "ka2.category.fort_und_weiterbildungen");                              // FrKr
        putCat("t-240050", "ka2.category.frauenhaeuser");                                         // FrKr
        putCat("t-253551", "ka2.category.frauenprojekte");                                        // Mitte
        putCat("t-240056", "ka2.category.geburtshaeuser");                                        // FrKr
        putCat("t-240051", "ka2.category.gezielte_gesundheitsfoerderung");                        // FrKr
        putCat("t-240082", "ka2.category.grosselterndienst");                                     // FrKr
        putCat("t-240097", "ka2.category.grund_und_oberschulen");                                 // FrKr
        putCat("t-253552", "ka2.category.grundschulen");                                          // Mitte
        putCat("t-266731", "ka2.category.hausaufgabenbetreuung");                                 // FrKr
        putCat("t-240055", "ka2.category.hebammen");                                              // FrKr
        putCat("t-240047", "ka2.category.hilfen_in_besonderen_notsituationen");                   // FrKr
        putCat("t-253553", "ka2.category.hilfen_zur_erziehung");                                  // Mitte
        putCat("t-240073", "ka2.category.hilfen_zur_erziehung");                                  // FrKr
        putCat("t-240057", "ka2.category.hiv_beratung");                                          // FrKr
        putCat("t-240096", "ka2.category.hotline");                                               // FrKr
        putCat("t-240031", "ka2.category.jobcenter_agentur_fuer_arbeit");                         // FrKr
        putCat("t-253554", "ka2.category.jugendberatung_und_jugendberufshilfe");                  // Mitte
        putCat("t-240062", "ka2.category.jugendfreizeiteinrichtungen");                           // FrKr
        putCat("t-240074", "ka2.category.jugendgerichtshilfe_bewaehrungshilfe");                  // FrKr
        putCat("t-240067", "ka2.category.jugendgerichtshilfe_bewaehrungshilfe");                  // FrKr
        putCat("t-240066", "ka2.category.jugendnotdienst");                                       // FrKr
        putCat("t-240063", "ka2.category.jugendsozialarbeit");                                    // FrKr
        putCat("t-240087", "ka2.category.kieznahe_mehrgenerationsangebote");                      // FrKr
        putCat("t-240054", "ka2.category.kinder_und_jugendgesundheitsdienste");                   // FrKr
        putCat("t-240095", "ka2.category.kinder_und_jugendnotdienst");                            // FrKr
        putCat("t-240039", "ka2.category.kinder_und_jugendpsychiatrische_beratungsstelle");       // FrKr
        putCat("t-240075", "ka2.category.kinderfreizeiteinrichtungen");                           // FrKr
        putCat("t-240077", "ka2.category.kindernotdienst");                                       // FrKr
        putCat("t-240076", "ka2.category.kinderschutzteam");                                      // FrKr
        putCat("t-240045", "ka2.category.kinderspielplaetze");                                    // FrKr
        putCat("t-240085", "ka2.category.kirchen_und_religionsgemeinschaften");                   // FrKr
        putCat("t-240068", "ka2.category.kjpd");                                                  // FrKr
        putCat("t-240052", "ka2.category.krankenhaeuser");                                        // FrKr
        putCat("t-240084", "ka2.category.kultur_und_bildungseinrichtungen");                      // FrKr
        putCat("t-240069", "ka2.category.leitung_und_team_des_jugendamtes");                      // FrKr
        putCat("t-240079", "ka2.category.logopaedische_praxen");                                  // FrKr
        putCat("t-240037", "ka2.category.mietberatung");                                          // FrKr
        putCat("t-253555", "ka2.category.migration_und_integration");                             // Mitte
        putCat("t-240086", "ka2.category.musikschulen_vhs");                                      // FrKr
        putCat("t-253556", "ka2.category.oberschulen");                                           // Mitte
        putCat("t-240088", "ka2.category.offene_treffpunkte_mit_sozialkulturellem_charakter");    // FrKr
        putCat("t-240029", "ka2.category.qualifizierung_und_beschaeftigung");                     // FrKr
        putCat("t-240090", "ka2.category.quartiersmanagement");                                   // FrKr
        putCat("t-240035", "ka2.category.rechtsberatung");                                        // FrKr
        putCat("t-240072", "ka2.category.regionale_dienste");                                     // FrKr
        putCat("t-240061", "ka2.category.reha_im_jobcenter_arbeitsamt");                          // FrKr
        putCat("t-240098", "ka2.category.schulbezogene_angebote_der_jugendhilfe");                // FrKr
        putCat("t-253557", "ka2.category.schuldner_und_insolvenzberatung");                       // Mitte
        putCat("t-240032", "ka2.category.schuldner_und_insolvenzberatung");                       // FrKr
        putCat("t-253558", "ka2.category.schulpsychologische_beratungsstelle");                   // Mitte
        putCat("t-255713", "ka2.category.schulsporthalle");                                       // FrKr
        putCat("t-255714", "ka2.category.schwimmbad");                                            // FrKr
        putCat("t-240093", "ka2.category.selbsthilfegruppen");                                    // FrKr
        putCat("t-253559", "ka2.category.sonstige_schulen");                                      // Mitte
        putCat("t-240033", "ka2.category.sozialhilfeberatung");                                   // FrKr
        putCat("t-240099", "ka2.category.sport_und_bolzplaetze");                                 // FrKr
        putCat("t-240101", "ka2.category.sportbezogene_jugendfreizeitangebote");                  // FrKr
        putCat("t-240100", "ka2.category.sportvereine");                                          // FrKr
        putCat("t-240089", "ka2.category.stadtteilarbeit");                                       // FrKr
        putCat("t-240092", "ka2.category.streetworker");                                          // FrKr
        putCat("t-240080", "ka2.category.tagesbetreuung_fuer_kinder");                            // FrKr
        putCat("t-240081", "ka2.category.tagesbetreuung_fuer_kinder");                            // FrKr
        putCat("t-240044", "ka2.category.traeger_der_jugendhilfe");                               // FrKr
        putCat("t-240048", "ka2.category.unterhalt_vaterschaftsanerkennung_im_jugendamt");        // FrKr
        putCat("t-262210", "ka2.category.wohnen");                                                // FrKr
        putCat("t-240053", "ka2.category.aerzte");                                                // FrKr

        // Zielgruppen
        putCat("t-253538", "ka2.category.alle_altersgruppen");                                        // Mitte
        putCat("t-240105", "ka2.category.alle_altersgruppen");                                        // FrKr
        putCat("t-240106", "ka2.category.auszubildende");                                             // FrKr
        putCat("t-253539", "ka2.category.auszubildende_und_junge_erwachsene");                        // Mitte
        putCat("t-253540", "ka2.category.erwachsene");                                                // Mitte
        putCat("t-240108", "ka2.category.erwachsene");                                                // FrKr
        putCat("t-240109", "ka2.category.familien");                                                  // FrKr
        putCat("t-253541", "ka2.category.familien_und_alleinerziehende_mit_kindern_im_schulalter");   // Mitte
        putCat("t-240111", "ka2.category.familien_und_alleinerziehende_mit_kindern_im_schulalter");   // FrKr
        putCat("t-253542", "ka2.category.familien_und_alleinerziehende_mit_kleinkindern");            // Mitte
        putCat("t-240112", "ka2.category.familien_und_alleinerziehende_mit_kleinkindern");            // FrKr
        putCat("t-253543", "ka2.category.familien_und_alleinerziehende_mit_saeuglingen");             // Mitte
        putCat("t-240113", "ka2.category.familien_und_alleinerziehende_mit_saeuglingen");             // FrKr
        putCat("t-253544", "ka2.category.zielgruppe_frauen");                                         // Mitte
        putCat("t-240114", "ka2.category.zielgruppe_frauen");                                         // FrKr
        putCat("t-253545", "ka2.category.jugendliche");                                               // Mitte
        putCat("t-240115", "ka2.category.jugendliche");                                               // FrKr
        putCat("t-240107", "ka2.category.junge_erwachsene");                                          // FrKr
        putCat("t-240110", "ka2.category.zielgruppe_kinder");                                         // FrKr
        putCat("t-253546", "ka2.category.kinder_im_schulalter");                                      // Mitte
        putCat("t-240116", "ka2.category.kinder_im_schulalter");                                      // FrKr
        putCat("t-240117", "ka2.category.zielgruppe_seniorinnen_und_senioren");                       // FrKr
        putCat("t-253547", "ka2.category.sonstige");                                                  // Mitte
        putCat("t-240118", "ka2.category.sonstige");                                                  // FrKr

        // Überregional
        putCat("t-1220613", "ka2.category.berlinweit");                                               // FrKr
        putCat("t-1220614", "ka2.category.gesamtbezirk");                                             // FrKr
    }

    private static void putCrit(String ka1CriteriaTypeId, String ka2FacetTypeUri) {
        if (CRITERIA_MAP.put(ka1CriteriaTypeId, ka2FacetTypeUri) != null) {
            throw new RuntimeException("More than one entry for KA1 criteria \"" + ka1CriteriaTypeId + "\"");
        }
    }

    private static void putCat(String ka1CategoryTopicId, String ka2CategoryUri) {
        if (CATEGORY_MAP.put(ka1CategoryTopicId, ka2CategoryUri) != null) {
            throw new RuntimeException("More than one entry for KA1 category \"" + ka1CategoryTopicId + "\"");
        }
    }
}
