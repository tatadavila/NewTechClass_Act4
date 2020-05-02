package com.edu.uac.co.grupo6_act4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Covid19TestsBD extends SQLiteOpenHelper {

    private static final int BD_VERSION = 1;

    public Covid19TestsBD(Context context) {
        super(context, DbDef.BD_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DbDef.CREATE_TESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

}