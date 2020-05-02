package com.edu.uac.co.grupo6_act4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PatientController {

    private Covid19TestsBD db;

    public PatientController(Context context) {
        this.db = new Covid19TestsBD(context);
    }

    public long registerPatient(Patient p) {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DbDef.ID_COL, p.id);
            values.put(DbDef.NAME_COL, p.name);
            values.put(DbDef.ADDRESS_COL, p.address);
            values.put(DbDef.ATTENT_PLACE_COL, p.attentionPlace);
            values.put(DbDef.DATE_COL, p.positiveDate);

            long rowId = database.insert(DbDef.TABLE_NAME, null, values);
            Log.d("ThisIsLog", "" + rowId);

            return rowId;
        } catch (Exception e) {
            System.out.println("Error al Insertar");
            return 0;
        }
    }

    public boolean findPatient(String pId) {
        SQLiteDatabase database = db.getReadableDatabase();
        String[] args = {pId};

        Cursor c = database.query(DbDef.TABLE_NAME, null, "ID=?", args, null, null, null);

        if (c.getCount() > 0) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    public void updatePatient(Patient p) {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] args = {p.id};

            values.put(DbDef.ID_COL, p.id);
            values.put(DbDef.NAME_COL, p.name);
            values.put(DbDef.ADDRESS_COL, p.address);
            values.put(DbDef.ATTENT_PLACE_COL, p.attentionPlace);
            values.put(DbDef.DATE_COL, p.positiveDate);

            database.update(DbDef.TABLE_NAME, values, "ID=?", args);
            database.close();

        } catch (Exception e) {
            System.out.println("Update Error");
        }
    }

    public void deletePatient(Patient p) {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            String[] args = {p.id};
            database.delete(DbDef.TABLE_NAME, "ID=?", args);
        } catch (final Exception e) {
            System.out.println("Deleteing Error");
        }
    }

    public Cursor allPatients() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor c = database.rawQuery("select ID as _id , NAME, ADDRESS, ATTENTION_PLACE, POSITIVE_TEST_DATE from TESTS", null);
            return c;
        } catch (final Exception e) {
            System.out.println("Consult Error");
            return null;
        }
    }


}
