package de.hbrs.se2.womm.views.components;


public enum SEARCHFILTER {
    UNTERNEHMENID,
    NAME,
    BESCHREIBUNG,
    GRUENDUNG,
    NUTZER;

    public static SEARCHFILTER[] allSEARCHFILTER() {
        SEARCHFILTER[] searchFilters = {NAME, BESCHREIBUNG, GRUENDUNG, UNTERNEHMENID, NUTZER};
        return searchFilters;
    }
}