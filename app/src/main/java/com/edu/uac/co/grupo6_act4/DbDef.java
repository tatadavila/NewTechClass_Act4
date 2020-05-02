package com.edu.uac.co.grupo6_act4;

public class DbDef {

    public static final String BD_NAME = "COVID19TESTS";
    public static final String TABLE_NAME = "TESTS";
    public static final String ID_COL = "ID";
    public static final String NAME_COL = "NAME";
    public static final String ADDRESS_COL = "ADDRESS";
    public static final String ATTENT_PLACE_COL = "ATTENTION_PLACE";
    public static final String DATE_COL = "POSITIVE_TEST_DATE";

    public static final String CREATE_TESTS_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+
            " "+ID_COL+" TEXT PRIMARY KEY," +
            " "+NAME_COL+" TEXT," +
            " "+ADDRESS_COL+" TEXT," +
            " "+ATTENT_PLACE_COL+" TEXT," +
            " "+DATE_COL+" TEXT" +
            ");";

}