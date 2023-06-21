package org.healthnlp.deepphe.core;

import org.apache.ctakes.dictionary.lookup2.dictionary.JdbcRareWordDictionary;
import org.apache.ctakes.dictionary.lookup2.term.RareWordTerm;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

public class DictQuery {
    String[] locations = new String[] {
            "abdomen",
            "ankle",
            "anus	",
            "appendix",
            "arm",
            "armpit",
            "artery",
            "back",
            "beard",
            "belly",
            "belly-button",
            "biceps",
            "bladder",
            "blood",
            "bone",
            "brains",
            "breast",
            "bosom",
            "butt-cheek",
            "calf",
            "capillary",
            "cell",
            "cervix",
            "cheek",
            "chest",
            "chin",
            "cranium",
            "cuticle",
            "epidermis",
            "skin",
            "ear",
            "eardrum",
            "elbow",
            "esophagus",
            "eye",
            "eyebrow",
            "eyelash",
            "eyelid",
            "face",
            "finger",
            "fingernail",
            "fist",
            "foot",
            "forearm",
            "forehead",
            "foreskin",
            "gall bladder",
            "gland",
            "groin",
            "gum",
            "hair",
            "hairdo",
            "hand",
            "head",
            "heart",
            "heel",
            "hip",
            "intestine",
            "jaw",
            "kidney",
            "knee",
            "kneecap",
            "knuckle",
            "leg",
            "ligament",
            "lip",
            "liver",
            "lung",
            "mandible",
            "moustache",
            "mouth",
            "muscle",
            "neck",
            "neckline",
            "nerve",
            "nipple",
            "nose",
            "nostril",
            "organ",
            "palate",
            "palm",
            "pancreas",
            "penis",
            "prostate",
            "pubis",
            "rectum",
            "rib",
            "scalp",
            "scrotum",
            "shin",
            "shoulder",
            "shoulder blade",
            "sinus",
            "skeleton",
            "skin",
            "skull",
            "sole",
            "spine",
            "spleen",
            "sternum",
            "stomach",
            "temple",
            "tendon",
            "testicle",
            "thigh",
            "thorax",
            "throat",
            "thumb",
            "thyroid",
            "toe",
            "toenail",
            "tongue",
            "tonsil",
            "tooth",
            "umbilical cord",
            "uterus",
            "urethra",
            "uvula",
            "vagina",
            "vein",
            "vena",
            "waist",
            "windpipe",
            "trachea",
            "wrist"};

    JdbcRareWordDictionary jdbcRareWordDictionary;
    public DictQuery() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("jdbcDriver", "org.hsqldb.jdbcDriver");
        properties.setProperty("jdbcUrl", "jdbc:hsqldb:file:org/apache/ctakes/dictionary/lookup/fast/ncit_plus_21aa/ncit_plus_21aa");
        properties.setProperty("jdbcUser", "sa");
        properties.setProperty("jdbcPass", "");
        properties.setProperty("rareWordTable", "cui_terms");
        jdbcRareWordDictionary = new JdbcRareWordDictionary("ncit_plus_21aaTerms", null, properties);
    }
//if space in term then select from TEXT
    public Collection<RareWordTerm> queryForTerm(String term) {
        Collection<RareWordTerm> rareWordTermCollection = jdbcRareWordDictionary.getRareWordHits(term);
        return rareWordTermCollection;
    }


    public static void main(String[] args) throws SQLException {
        DictQuery dictQuery = new DictQuery();

    }

}
