/*
 * Copyright (C) 2014 Atlas of Living Australia
 * All Rights Reserved.
 *
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 */
package au.org.ala.names.model;

import org.gbif.api.vocabulary.Rank;

import java.util.*;


/**
 * An enumeration of the rank types available in ALA, with some text vocabulary that can be used to match to a RankType.
 * <p/>
 * This is based on org.ala.web.util.RankFacet
 *
 * @author Natasha
 */
public enum RankType {

    DOMAIN(800, "kingdom", Rank.DOMAIN, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Domain", "Domain"),
    KINGDOM(1000, "kingdom", Rank.KINGDOM, 2f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Kingdom", "Kingdom"),
    SUBKINGDOM(1200, "subkingdom", Rank.SUBKINGDOM, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subkingdom"),
    SUPERPHYLUM(1800, "superphylum", Rank.SUPERPHYLUM, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Superphylum"),
    PHYLUM(2000, "phylum", Rank.PHYLUM, 2f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Phylum", "Phylum", "division botany", "Division Botany"),
    SUBPHYLUM(2200, "subphylum", Rank.SUBPHYLUM, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subphylum", "subdivision botany"),
    SUPERCLASS(2800, "superclass", Rank.SUPERCLASS, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Superclass"),
    CLASS(3000, "class", Rank.CLASS, 2f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Class", "Class"),
    SUBCLASS(3200, "subclass", Rank.SUBCLASS, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subclass"),
    INFRACLASS(3350, "infraclass", null, null),
    SUBINFRACLASS(3370, "subinfraclass", null, null),
    SUPERDIVISION_ZOOLOGY(3450, "superdivision zoology", null, null),
    DIVISION_ZOOLOGY(3500, "division zoology", null, null),
    SUBDIVISION_ZOOLOGY(3550, "subdivision zoology", null, null),
    SUPERCOHORT(3650, "supercohort", null, null),
    COHORT(3700, "cohort", null, null),
    SUBCOHORT(3750, "subcohort", null, null),
    SUPERORDER(3800, "superorder", Rank.SUPERORDER, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Superorder"),
    ORDER(4000, "order", Rank.ORDER, 2f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Order", "Order"),
    SUBORDER(4200, "suborder", Rank.SUBORDER, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Suborder"),
    INFRAORDER(4350, "infraorder", Rank.INFRAORDER, null),
    PARVORDER(4400, "parvorder", Rank.PARVORDER, null),
    SUPERSERIES_ZOOLOGY(4445, "superseries zoology", null, null),
    SERIES_ZOOLOGY(4450, "series zoology", null, null),
    SUBSERIES_ZOOLOGY(4455, "subseries zoology", null, null),
    SUPERSECTION_ZOOLOGY(4465, "supersectionzoology", null, null),
    SECTION_ZOOLOGY(4470, "section zoology", null, null),
    SUBSECTION_ZOOLOGY(4475, "subsection zoology", null, null),
    SUPERFAMILY(4500, "superfamily", Rank.SUPERFAMILY, null, "Superfamily"),
    FAMILY(5000, "family", Rank.FAMILY, 2f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Family", "Family"),
    SUBFAMILY(5500, "subfamily", Rank.SUBFAMILY, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subfamily"),
    TRIBE(5600, "tribe", Rank.TRIBE, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Tribe"),
    SUBTRIBE(5700, "subtribe", Rank.SUBTRIBE, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subtribe"),
    SUPERGENUS(5900, "genus", null, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Supergenus", "Supergenus"),
    GENUS_GROUP(5950, "genus group", null, null, "aggregate genera", "Aggregate Genera", "Genus Group"),
    GENUS(6000, "genus", Rank.GENUS, 3f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Genus", "Genus"),
    SUBGENUS(6500, "subgenus", Rank.SUBGENUS, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subgenus"),
    SUPERSECTION_BOTANY(6550, "supersection botany", Rank.SECTION, null),
    SECTION_BOTANY(6600, "section botany", Rank.SECTION, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Section"),
    SUBSECTION_BOTANY(6700, "subsection botany", Rank.SUBSECTION, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subsection"),
    SUPERSERIES_BOTANY(6750, "superseries botany", null, null),
    SERIES_BOTANY(6800, "series botany", Rank.SERIES, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Series"),
    SUBSERIES_BOTANY(6900, "subseries botany", Rank.SUBSERIES, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subseries"),
    INFRAGENERICNNAME(6925, "infragenericname", Rank.INFRAGENERIC_NAME, null),
    SPECIES_GROUP(6950, "species group", null, null, "aggregate species", "Aggregate Species", "Species Group"),
    SUPERSPECIES(6960, "superspecies", null, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Species", "Superspecies"),
    SPECIES_SUBGROUP(6975, "species subgroup", null, null, "Species Subroup"),
    SPECIES(7000, "species", Rank.SPECIES, 2f, "http://rs.tdwg.org/ontology/voc/TaxonRank#Species", "Species"),
    NOTHOSPECIES(7001, "nothospecies", null, null),
    HOLOMORPH(7100, "holomorph", null, null),
    ANAMORPH(7120, "anamorph", null, null),
    TELEOMORPH(7140, "teleomorph", null, null),
    SUBSPECIES(8000, "subspecies", Rank.SUBSPECIES, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subspecies", "subsp", "subsp.", "ssp", "subtaxon", "staxon", "subsp..", "susp"),
    NOTHOSUBPECIES(8001, "nothosubspecies", null, null),
    INFRASPECIFICNAME(8005, "infraspecificname", Rank.INFRASPECIFIC_NAME, null, "Infraspecies"),
    INFRASUBSPECIESNAME(-1, "infrasubspeciesname", Rank.INFRASUBSPECIFIC_NAME, null),
    VARIETY(8010, "variety", Rank.VARIETY, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Variety", "var.", "var,", "var"),
    NOTHOVARIETY(8011, "nothovariety", null, null),
    SUBVARIETY(8015, "subvariety", Rank.SUBVARIETY, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Sub-Variety", "subvar."),
    FORM(8020, "form", Rank.FORM, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Form", "forma"),
    NOTHOFORM(8021, "nothoform", null, null),
    SUBFORM(8025, "subform", Rank.SUBFORM, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Subform"),
    BOIVAR(8030, "biovar", null, null),
    SEROVAR(8040, "serovar", null, null),
    CULTIVARGROUP(-1, "cultivargroup", Rank.CULTIVAR, null),
    CULTIVAR(8050, "cultivar", Rank.CULTIVAR, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#Cultivar", "cv."),
    PATHOVAR(8080, "pathovar", null, null),
    INFORMAL(-1, "informal", null, null),
    UNRANKED(0, "unranked", Rank.UNRANKED, null),
    SUPRAGENERICNAME(8200, "supragenericname", Rank.SUPRAGENERIC_NAME, null, "http://rs.tdwg.org/ontology/voc/TaxonRank#SupragenericTaxon"),
    HYBRID(8150, "hybrid", null, null);


    // Allow reverse-lookup (based on http://www.ajaxonomy.com/2007/java/making-the-most-of-java-50-enum-tricks)
    private static final Map<String, RankType> fieldLookup = new HashMap<String, RankType>();
    private static final Map<Integer, RankType> idLookup = new HashMap<Integer, RankType>();
    private static final Map<Rank, RankType> cbRankLookup = new HashMap<Rank, RankType>();

    private static final Map<String, RankType> strRankLookup = new HashMap<String, RankType>();

    static {
        for (RankType rt : EnumSet.allOf(RankType.class)) {
            try {
                fieldLookup.put(rt.getRank(), rt);
                idLookup.put(rt.getId(), rt);
                if (rt.cbRank != null)
                    cbRankLookup.put(rt.cbRank, rt);
                strRankLookup.put(rt.getRank(), rt);
                if (rt.strRanks != null) {
                    for (String rank : rt.strRanks)
                        strRankLookup.put(rank.toLowerCase(), rt);
                }
            } catch (RuntimeException ex) {
                System.err.println("Unable to load " + rt);
                throw ex;
            }
        }
    }

    private Integer id;
    private String field;
    private Rank cbRank;
    private Float boost;
    private String[] strRanks;

    RankType(Integer id, String field, Rank rank, Float boost, String... strRanks) {


        this.id = id;
        this.field = field;
        this.cbRank = rank;
        this.strRanks = strRanks;
        this.boost = boost;

    }

    public static Set<RankType> getAllRanksBelow(Integer rank) {
        Set<RankType> ranks = new TreeSet<RankType>();
        for (RankType rt : EnumSet.allOf(RankType.class)) {
            if (rt.getId() >= rank)
                ranks.add(rt);
        }
        return ranks;
    }

    /**
     * @return id the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return field the field
     */
    public String getRank() {
        return this.field;
    }


    /**
     * @return The additional labels for the rank
     */
    public String[] getStrRanks() {
        return strRanks;
    }

    /**
     * Gets the Rank Type for the supplied raw string...
     *
     * @param rank
     * @return
     */
    public static RankType getForStrRank(String rank) {
        return strRankLookup.get(rank.toLowerCase());
    }

    public Float getBoost() {
        return boost;
    }

    /**
     * @param field
     * @return The rankType for the field
     */
    public static RankType getForName(String field) {
        return fieldLookup.get(field);
    }

    public static RankType getForCBRank(Rank cbRank) {
        return cbRankLookup.get(cbRank);
    }

    /**
     * @param id
     * @return The RankType for the specified Portal id
     */
    public static RankType getForId(Integer id) {
        return idLookup.get(id);
    }

}


