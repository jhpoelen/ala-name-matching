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

import org.gbif.api.model.checklistbank.ParsedName;
import org.gbif.api.vocabulary.Rank;

import java.util.regex.Pattern;


/**
 * Stores the extra information for an ALA Parsed Phrase name.
 *
 * @author Natasha Carter
 */
public class ALAParsedName extends ParsedName {

    public String locationPhraseDescription = null;
    public String cleanPhrase = null;
    public String phraseVoucher = null;
    public String cleanVoucher = null;// a clean voucher is on that is missing all punctuation and initials for people
    public String phraseNominatingParty = null;
    public static final Pattern multipleSpaces = Pattern.compile("\\s{2,}");
    public static final Pattern voucherBlacklist = Pattern.compile(" and | AND | And | s.n.| sn ");
    public static final Pattern voucherRemovePattern = Pattern.compile("[^\\w]");
    public static final Pattern potentialVoucherId = Pattern.compile("([^A-Z][A-Z]{1,3} [0-9])");
    public static final Pattern initialOnePattern = Pattern.compile("(?:[A-Z][\\.]){1,3}");//supports initials like A.B.C.
    public static final Pattern initialTwoPattern = Pattern.compile("(?:[^A-Z][A-Z]{1,3} )");//supports initials like AB
    public static final Pattern phraseBlacklist = Pattern.compile("&| AND | and |Stn|Stn\\.|Station|Mt |Mt\\.|Mount");
    public static final Pattern phrasePunctuationRemoval = Pattern.compile("'|\"");

    public ALAParsedName() {}

    public ALAParsedName(ParsedName pn) {
        this.setAuthorsParsed(pn.isAuthorsParsed());
        this.setAuthorship(pn.getAuthorship());
        this.setBracketAuthorship(pn.getBracketAuthorship());
        this.setBracketYear(pn.getBracketYear());
        //this.setCode(pn.getCode());
        this.setCultivarEpithet(pn.getCultivarEpithet());
        this.setGenusOrAbove(pn.getGenusOrAbove());
        //this.setId(pn.getId());
        this.setInfraGeneric(pn.getInfraGeneric());
        this.setInfraSpecificEpithet(pn.getInfraSpecificEpithet());
        this.setNomStatus(pn.getNomStatus());
        this.setNotho(pn.getNotho());
        this.setRank(pn.getRank());
        //this.setRankMarker(pn.getRankMarker());
        this.setRemarks(pn.getRemarks());
        this.setSensu(pn.getSensu());
        this.setSpecificEpithet(pn.getSpecificEpithet());
        this.setType(pn.getType());
        this.setYear(pn.getYear());
    }

    /**
     * @deprecated typo with the getter. Use correct spelling.
     * @return
     */
    public String getLocationPhraseDesciption() {
        return locationPhraseDescription;
    }

    public String getLocationPhraseDescription() {
        return locationPhraseDescription;
    }

    public void setLocationPhraseDescription(String locationPhraseDescription) {
        this.locationPhraseDescription = locationPhraseDescription;
        if (this.getRank() == Rank.SPECIES) {
            this.setSpecificEpithet(locationPhraseDescription);
        } else if (this.getRank() == Rank.CULTIVAR) {
            this.setCultivarEpithet(locationPhraseDescription);
        } else {
            this.setInfraSpecificEpithet(locationPhraseDescription);
        }
        if (locationPhraseDescription != null) {
            cleanPhrase = phraseBlacklist.matcher(" " + locationPhraseDescription).replaceAll(" ").trim();
            cleanPhrase = phrasePunctuationRemoval.matcher(cleanPhrase).replaceAll("");
            cleanPhrase = multipleSpaces.matcher(cleanPhrase).replaceAll(" ");
        }

    }

    public String getPhraseNominatingParty() {
        return phraseNominatingParty;
    }

    public void setPhraseNominatingParty(String phraseNominatingParty) {
        this.phraseNominatingParty = phraseNominatingParty;
    }

    public String getPhraseVoucher() {
        return phraseVoucher;
    }

    public void setPhraseVoucher(String phraseVoucher) {
        this.phraseVoucher = phraseVoucher;
        //set the clean version of the phrase voucher
        if (phraseVoucher != null) {
            this.cleanVoucher = phraseVoucher;
            java.util.regex.Matcher m = potentialVoucherId.matcher(this.cleanVoucher);
            if (m.find())
                cleanVoucher = m.replaceFirst(" " + m.group().replaceAll(" ", ""));

            this.cleanVoucher = voucherBlacklist.matcher(cleanVoucher).replaceAll(" ");
            this.cleanVoucher = initialOnePattern.matcher(cleanVoucher).replaceAll(" ");
            this.cleanVoucher = initialTwoPattern.matcher(cleanVoucher).replaceAll(" ");
            this.cleanVoucher = voucherRemovePattern.matcher(cleanVoucher).replaceAll("");
        }
    }
}
