package com.example.lkhedma.firstaid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LKHEDMA on 07/04/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "first_aid.db";
    private static final int DB_VERSION = 1;

    private static final String CREAT_CONTACTS_TABLE = "CREATE TABLE "+FirstAidManager.ContactManager.CONTACT_TABLE_NAME+
            "(" +
            " "+FirstAidManager.ContactManager.ID_CONTACT+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            " "+FirstAidManager.ContactManager.NOM_CONTACT+" TEXT," +
            " "+FirstAidManager.ContactManager.NUMERO_CONTACT+" INTEGER," +
            " "+FirstAidManager.ContactManager.SPECIFICATION_CONTACT+" TEXT" +
            ");";


    private static final String CREAT_ACCIDENT_ENUM_TABLE = "CREATE TABLE "+FirstAidManager.AccidentEnumManganager.ACCIDENT_ENUM_TABLE_NAME+
            "("+
            " "+FirstAidManager.AccidentEnumManganager.NOM_ACCIDENT+" TEXT PRIMARY KEY"+
            ");";


    private static final String CREAT_ACCIDENTS_TABLE = "CREATE TABLE "+FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME+
            "("+
            " "+FirstAidManager.AccidentsMangager.ID_ACCIDENT+" TEXT PRIMARY KEY,"+
            " "+FirstAidManager.AccidentsMangager.NOM_ACCIDENT+" TEXT,"+
            " "+FirstAidManager.AccidentsMangager.ICON_ACCident+" TEXT,"+
            " "+"FOREIGN KEY("+FirstAidManager.AccidentsMangager.NOM_ACCIDENT+") REFERENCES "+FirstAidManager.AccidentEnumManganager.ACCIDENT_ENUM_TABLE_NAME+"("+FirstAidManager.AccidentEnumManganager.NOM_ACCIDENT+")" +
            ");";

    private static final String CREAT_DIAGNOSTIQUE_TABLE = "CREATE TABLE "+FirstAidManager.DiagnostiqueManager.DIAGNOSTIC_TABLE_NAME+
            "("+
            " "+FirstAidManager.DiagnostiqueManager.TITRE_DIAGNOSTIQUE+" TEXT PRIMARY KEY,"+
            " "+FirstAidManager.DiagnostiqueManager.GRAVITE_DIAGNOSTIC+" TEXT"+
            ");";

    private static final String CREAT_GESTE_SECOUR_TABLE = "CREATE TABLE "+FirstAidManager.GesteSecourManager.GESTE_SECOUR_TABLE_NAME+
            "("+
            " "+FirstAidManager.GesteSecourManager.DESCRIPTION_GESTE_SECOUR+" TEXT PRIMARY KEY,"+
            " "+FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR+" TEXT,"+
            " "+FirstAidManager.GesteSecourManager.ICON_GESTE_SECOUR+" TEXT,"+
            " "+"FOREIGN KEY("+FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR+") REFERENCES "+FirstAidManager.DiagnostiqueManager.DIAGNOSTIC_TABLE_NAME+"("+FirstAidManager.DiagnostiqueManager.TITRE_DIAGNOSTIQUE+")"+
            ");";


    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.e("DB Operations", "DB created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_CONTACTS_TABLE);
        Log.e("DB Operations", "Tables contacts created");
        db.execSQL(CREAT_ACCIDENT_ENUM_TABLE);
        db.execSQL(CREAT_ACCIDENTS_TABLE);
        db.execSQL(CREAT_DIAGNOSTIQUE_TABLE);
        db.execSQL(CREAT_GESTE_SECOUR_TABLE);
        insertIntoAccidentEnum("Brulure", db);
        insertIntoAccidentEnum("Chute", db);
        insertIntoAccident("T20 - T31", "Brulure", "@drawable/flame", db);
        insertIntoAccident("W00 - T19", "Chute", "@drawable/slide", db);
        inserIntoDiagnostique("Brulure grave", "grave", db);
        inserIntoDiagnostique("Brulure simple", "faible", db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
//            case 1:        //from 1 to 2
//                db.execSQL(CREAT_ACCIDENT_ENUM_TABLE);
//                db.execSQL(CREAT_ACCIDENTS_TABLE);
//                insertIntoAccidentEnum("Brulure", db);
//                insertIntoAccidentEnum("Chute", db);
//                insertIntoAccident("T20 - T31", "brulure", "icon brulure", db);
//                insertIntoAccident("W00 - T19", "chute", "icon chute", db);
//
//            case 2:
//                db.delete(FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME,"T20 - T31",null);
//                Log.e("DB Operations", "brulure deleted");
//                insertIntoAccident("T20 - T31", "Brulure", "icon brulure", db);
//                //insertIntoAccident("W00 - T19", "Chute", "icon chute", db);
//            case 3:
//                String[] where = {"T20 - T31"};
//                db.delete(FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME,FirstAidManager.AccidentsMangager.ID_ACCIDENT,where);
//                Log.e("DB Operations", "brulure deleted");
//                insertIntoAccident("T20 - T31", "Brulure", "icon brulure", db);
        }

    }

    public void addNewContact(Contact newContact, SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.ContactManager.NOM_CONTACT, newContact.getNom());
        contentValues.put(FirstAidManager.ContactManager.NUMERO_CONTACT, newContact.getNumero());
        contentValues.put(FirstAidManager.ContactManager.SPECIFICATION_CONTACT, newContact.getSpecification());

        sqLiteDatabase.insert(FirstAidManager.ContactManager.CONTACT_TABLE_NAME, null, contentValues);
        Log.e("DB Operations", "One nw contact");
    }

    public void insertIntoAccidentEnum(String nom_accident, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.AccidentEnumManganager.NOM_ACCIDENT, nom_accident);
        db.insert(FirstAidManager.AccidentEnumManganager.ACCIDENT_ENUM_TABLE_NAME, null, contentValues);
    }

    public void insertIntoAccident(String id, String nom, String icon, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.AccidentsMangager.ID_ACCIDENT, id);
        contentValues.put(FirstAidManager.AccidentsMangager.NOM_ACCIDENT, nom);
        contentValues.put(FirstAidManager.AccidentsMangager.ICON_ACCident, icon);
        db.insert(FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME, null, contentValues);
    }

    public void inserIntoDiagnostique(String titre, String gravite, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.DiagnostiqueManager.TITRE_DIAGNOSTIQUE, titre);
        contentValues.put(FirstAidManager.DiagnostiqueManager.GRAVITE_DIAGNOSTIC, gravite);
        db.insert(FirstAidManager.DiagnostiqueManager.DIAGNOSTIC_TABLE_NAME, null, contentValues);
    }

    public void insertIntoGesteSecour(String description, String icon, String titreDiagnostique, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.GesteSecourManager.DESCRIPTION_GESTE_SECOUR, description);
        contentValues.put(FirstAidManager.GesteSecourManager.ICON_GESTE_SECOUR, icon);
        contentValues.put(FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR, titreDiagnostique);
    }

    public Cursor getAllAccidents(SQLiteDatabase db) {

        Cursor cursor;
        String[] projections = {FirstAidManager.AccidentsMangager.ID_ACCIDENT,
                                FirstAidManager.AccidentsMangager.NOM_ACCIDENT,
                                FirstAidManager.AccidentsMangager.ICON_ACCident};
        cursor = db.query(FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }


}
