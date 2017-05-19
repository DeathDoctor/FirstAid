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

    private static final String CREAT_PREVENTION_ACCIDENT_TABLE = "CREATE TABLE "+FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_TABLE_NAME+
            "("+
            " "+FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_NAME+" TEXT PRIMARY KEY,"+
            " "+FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_ICON+" TEXT"+
            ");";

    private static final String CREAT_PREVENTION_INSTRUCTION = "CREATE TABLE "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TABLE_NAME+
            "("+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TITLE+" TEXT PRIMARY KEY,"+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_SUBTITLE+" TEXT,"+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_FIRST_ICON+" TEXT,"+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_SECOND_ICON+" TEXT,"+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_DESCRIPTION+" TEXT"+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TITLE+" TEXT,"+
            " "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_ACCIDENT_NAME+" TEXT,"+
            " "+"FOREIGN KEY("+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_ACCIDENT_NAME+") REFERENCES "+FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_TABLE_NAME+"("+FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_NAME+")"+
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
        db.execSQL(CREAT_PREVENTION_ACCIDENT_TABLE);
        db.execSQL(CREAT_PREVENTION_INSTRUCTION);

        Log.e("DB", "All tables created");
        insertIntoContacts(new Contact("pompiers", 14, "urgence"), db);

        insertIntoAccidentEnum("Brulure", db);
        insertIntoAccidentEnum("Chute", db);
        insertIntoAccidentEnum("Ingestion de produits caustiques", db);

        insertIntoPreventionAccident("Etouffements", "@drawable/flame", db);
        insertIntoPreventionAccident("Noyades", "@drawable/flame", db);
        insertIntoPreventionAccident("Chutes", "@drawable/flame", db);
        insertIntoPreventionAccident("Brulure", "@drawable/flame", db);
        insertIntoPreventionAccident("Electrocution", "@drawable/flame", db);

        insertIntoPreventionInstruction(db, "Etouffements", "Les médicaments", "Ne laissez jamais de médicaments à portée de votre enfant","@drawable/etouf1", "@drawable/etouf2",
                "• Rangez toujours les médicaments dans une armoire en hauteur, fermée à clé.\n" +
                        "• Ne laissez pas de médicaments dans un sac à main, sur une table... \n " +
                        "• Respectez toujours les doses de médicaments prescrites par le médecin, les délais entre chaque prise et lisez attentivement les notices d'utilisation." +
                        "• Ne présentez jamais un médicament comme un bonbon.");
        insertIntoPreventionInstruction(db, "Etouffements", "Les médicamentss", "Ne laissez jamais de médicaments à portée de votre enfant","@drawable/etouf1", "@drawable/etouf2",
                "• Rangez toujours les médicaments dans une armoire en hauteur, fermée à clé.\n" +
                        "• Ne laissez pas de médicaments dans un sac à main, sur une table... \n " +
                        "• Respectez toujours les doses de médicaments prescrites par le médecin, les délais entre chaque prise et lisez attentivement les notices d'utilisation." +
                        "• Ne présentez jamais un médicament comme un bonbon.");
        insertIntoPreventionInstruction(db, "Etouffements", "Les médicamentsss", "Ne laissez jamais de médicaments à portée de votre enfant","@drawable/etouf1", "@drawable/etouf2",
                "• Rangez toujours les médicaments dans une armoire en hauteur, fermée à clé.\n" +
                        "• Ne laissez pas de médicaments dans un sac à main, sur une table... \n " +
                        "• Respectez toujours les doses de médicaments prescrites par le médecin, les délais entre chaque prise et lisez attentivement les notices d'utilisation." +
                        "• Ne présentez jamais un médicament comme un bonbon.");

        Log.e("table prevention", "element1");

        insertIntoAccident("T20 - T31", "Brulure", "@drawable/flame", db);
        insertIntoAccident("W00 - T19", "Chute", "@drawable/slide", db);
        insertIntoAccident("A60 - A80", "Ingestion de produits caustiques", "@drawable/slide", db);


        inserIntoDiagnostique("Brulure grave", "grave", db);
        inserIntoDiagnostique("Brulure simple", "faible", db);
        insertIntoGesteSecour("@drawable/refroidir","Refroidir rapidement les brulures avec l'eau froide(entre 10 et 15 degres) pendant 5mn","Brulure simple",db);
        insertIntoGesteSecour("@drawable/cloques","Ne pas percer les cloques","Brulure simple",db);
        insertIntoGesteSecour("@drawable/pansement","Proteger avec un pansement sterile etadhesif","Brulure simple",db);
        insertIntoGesteSecour("@drawable/vaccin","Vérifiez la vaccination antitanique","Brulure simple",db);
        insertIntoGesteSecour("@drawable/appeler","Appeler un medecin dans le cas ou les signe suivants apparaissent: forte rougeur, gonflement...","Brulure simple",db);


        
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

    public void insertIntoContacts(Contact newContact, SQLiteDatabase sqLiteDatabase) {

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

    public void insertIntoPreventionAccident(String nom, String icon, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_NAME, nom);
        contentValues.put(FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_ICON, icon);
        db.insert(FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_TABLE_NAME, null, contentValues);
    }

    public void inserIntoDiagnostique(String titre, String gravite, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.DiagnostiqueManager.TITRE_DIAGNOSTIQUE, titre);
        contentValues.put(FirstAidManager.DiagnostiqueManager.GRAVITE_DIAGNOSTIC, gravite);
        db.insert(FirstAidManager.DiagnostiqueManager.DIAGNOSTIC_TABLE_NAME, null, contentValues);
    }

    public void insertIntoGesteSecour(String icon, String description, String titreDiagnostique, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.GesteSecourManager.ICON_GESTE_SECOUR, icon);
        contentValues.put(FirstAidManager.GesteSecourManager.DESCRIPTION_GESTE_SECOUR, description);
        contentValues.put(FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR, titreDiagnostique);
        db.insert(FirstAidManager.GesteSecourManager.GESTE_SECOUR_TABLE_NAME, null, contentValues);
    }

    public void insertIntoPreventionInstruction(SQLiteDatabase db, String nomAccident, String titre, String sousTitre, String icon1, String icon2, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TITLE, titre);
        contentValues.put(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_SUBTITLE, sousTitre);
        contentValues.put(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_FIRST_ICON, icon1);
        contentValues.put(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_SECOND_ICON, icon2);
        contentValues.put(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_DESCRIPTION, description);
        contentValues.put(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_ACCIDENT_NAME, nomAccident);
        db.insert(FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TABLE_NAME, null, contentValues);
    }

    public Cursor getAllAccidents(SQLiteDatabase db) {

        Cursor cursor;
        String[] projections = {FirstAidManager.AccidentsMangager.ID_ACCIDENT,
                                FirstAidManager.AccidentsMangager.NOM_ACCIDENT,
                                FirstAidManager.AccidentsMangager.ICON_ACCident};
        cursor = db.query(FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public Cursor getGestesDeSecours(SQLiteDatabase db, String requete, String diagnostique) {
        Cursor cursor;
        cursor = db.rawQuery(requete, new String[] {diagnostique});
        return cursor;
    }

    public Cursor getPreventionInstructions(SQLiteDatabase db, String requete, String nomAccident) {
        Cursor cursor;
        cursor = db.rawQuery(requete, new String[] {nomAccident});
        return cursor;
    }

    public Cursor getAllContacts(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {FirstAidManager.ContactManager.NOM_CONTACT,
                                FirstAidManager.ContactManager.NUMERO_CONTACT,
                                FirstAidManager.ContactManager.SPECIFICATION_CONTACT};
        cursor = db.query(FirstAidManager.ContactManager.CONTACT_TABLE_NAME, projections, null, null, null, null, FirstAidManager.ContactManager.NOM_CONTACT+" ASC",null);
        return cursor;
    }

    public Cursor getAllPreventionAccident(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_NAME,
                                FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_ICON};
        cursor = db.query(FirstAidManager.PreventionAccidentManager.PREVENTION_ACCIDENT_TABLE_NAME, projections, null, null, null, null, null, null);
        return cursor;
    }


}
