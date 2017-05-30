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

    private static final String CREAT_HOPITAL = "CREATE TABLE "+FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME+
            "("+
            " "+FirstAidManager.HopitalManager.HOPITAL_NAME+" TEXT PRIMARY KEY,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_PHOTO+" TEXT,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_WILAYA+" TEXT,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_QUARTIER+" TEXT,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_NUMERO+" TEXT,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_SITE+" TEXT,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_LAT+" TEXT,"+
            " "+FirstAidManager.HopitalManager.HOPITAL_LONG+" TEXT"+
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
        db.execSQL(CREAT_HOPITAL);

        Log.e("DB", "All tables created");
        insertIntoContacts(new Contact("pompiers", 14, "urgence"), db);

        insertIntoAccidentEnum("Brulure", db);
        insertIntoAccidentEnum("Chute", db);
        insertIntoAccidentEnum("Intoxication", db);  // ingestion
        insertIntoAccidentEnum("Inhalation", db);

        insertIntoPreventionAccident("Etouffements", "@drawable/icon_inhalation", db);
        insertIntoPreventionAccident("Noyades", "@drawable/icon_noyade", db);
        insertIntoPreventionAccident("Chutes", "@drawable/slide", db);
        insertIntoPreventionAccident("Brulures", "@drawable/flame", db);
        insertIntoPreventionAccident("Electrocutions", "@drawable/icon_electrocution", db);
        insertIntoPreventionAccident("Intoxications", "@drawable/icon_intox", db);


        // AJOUT DES PREVENTIONS POUR ETOUFFEMENTS

        insertIntoPreventionInstruction(db, "Etouffements", "Les petits objets et petits aliments", "Ne laissez pas de petits objets ou de petits aliments à la portée de votre enfant","@drawable/p_etouf1", "@drawable/p_etouf2",
                        "• Ne laissez pas à portée de votre enfant : pièces de monnaie, billes, bonbons, petits jouets, aliments (olives, cacahuètes...), piles, capuchons de stylos, barrettes à cheveux... \n" +
                        "• Ne laissez pas de médicaments dans un sac à main, sur une table... \n " +
                        "• Surveillez votre enfant pendant ses repas et lorsqu'il joue. ");

        insertIntoPreventionInstruction(db, "Etouffements", "Les sacs en plastiques et d'autres risques d'étouffement", "Mettez les sacs en plastique hors de portée de votre enfant","@drawable/p_etouf3", "@drawable/p_etouf4",
                        "• Rangez les sacs en plastique hors de portée de votre enfant.");

        insertIntoPreventionInstruction(db, "Etouffements", "Dans son lit", "Couchez votre enfant en toute sécurité","@drawable/p_etouf5", "@drawable/p_etouf6",
                        "• Couchez votre enfant sur le dos, toujours seul dans son lit.\n" +
                        "• N’utilisez pas d’oreiller, de couverture ou de couette.  \n " +
                        "• Utilisez un lit rigide et profond, un matelas ferme adapté à la taille du lit et conforme aux exigences de sécurité.\n" +
                        "• Habillez votre enfant d’un surpyjama ou mettez-le dans une gigoteuse, adaptés à sa taille. \n"+
                        "• N’encombrez pas le lit avec des peluches ou des poupées avec lesquels votre enfant pourrait s'étouffer.\n"+
                        "• Ne laissez jamais d'animal entrer dans la chambre de votre enfant.\n"+
                        "• Ne surchauffez pas la chambre, la température idéale est de 19°C. ");

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // AJOUT DES PREVENTIONS POUR NOYADES

        insertIntoPreventionInstruction(db, "Noyades", "Dans son bain", "Votre enfant ne doit jamais rester seul dans son bain, même un court instant","@drawable/p_noy1", "@drawable/p_noy2",
                        "• Ne laissez jamais votre enfant seul dans son bain, même si vous utilisez un siège de bain ou un tapis anti-glissant. Ces dispositifs ne sont pas des articles de sécurité. \n" +
                        "• Ne vous éloignez jamais, même quelques secondes.  \n " +
                        "• Mettez à l’avance à portée de main tout ce dont vous avez besoin (savon, shampoing, serviette...). Ne répondez pas au téléphone et n'allez pas ouvrir la porte. ");

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // AJOUT DES PREVENTIONS POUR CHUTES

        insertIntoPreventionInstruction(db, "Chutes", "Sur sa table à langer", "Ne laissez jamais votre enfant seul sur la table à langer","@drawable/p_ch1", "@drawable/p_ch2",
                        "• Ne laissez jamais votre enfant sans surveillance sur une table à langer.  \n" +
                        "• Prenez toujours votre enfant avec vous, si vous devez vous éloigner, même un court instant.   \n " +
                        "• Mettez à l'avance tout ce dont vous avez besoin (couches, vêtements...) à portée de main. \n"+
                        "• Installez votre enfant face à vous : vos gestes seront plus précis et vous attirerez plus facilement son regard.");

        insertIntoPreventionInstruction(db, "Chutes", "Dans sa chaise haute", "Veillez toujours à ce que votre enfant soit bien attaché","@drawable/p_ch3", "@drawable/p_ch4",
                        "• Utilisez toujours une chaise portant la mention “conforme aux exigences de sécurité”.   \n" +
                        "• Utilisez systématiquement la sangle d'entrejambe et bouclez les attaches.   \n " +
                        "• Ne laissez jamais votre enfant sans surveillance.");

        insertIntoPreventionInstruction(db, "Chutes", "Dans les escaliers", "Installez des barrières de sécurité en haut et en bas de vos escaliers","@drawable/p_ch5", "@drawable/p_ch6",
                        "• Ne laissez jamais votre enfant sans surveillance sur une table à langer.  \n" +
                        "• Installez des barrières portant la mention “conforme aux exigences de sécurité”. \n " +
                        "• Apprenez à votre enfant à monter et à descendre les escaliers.  \n"+
                        "• Ne laissez jamais votre enfant utiliser seul les escaliers jusqu’à ce qu’il sache se tenir à la rampe.");

        insertIntoPreventionInstruction(db, "Chutes", "Par la fenêtre", "Ne laissez jamais votre enfant seul dans une pièce avec une fenêtre ouverte ou sur un balcon","@drawable/p_ch7", "@drawable/p_ch8",
                        "• Ne placez jamais de meubles ou d'objets sous vos fenêtres ou sur le balcon : votre enfant peut grimper dessus. \n" +
                        "• Bloquez si possible l'ouverture des fenêtres par un système de sécurité. \n " +
                        "• Veillez à ce que votre enfant ne puisse pas escalader ou passer à travers les barreaux de votre balcon.\n"+
                        "• Ne laissez jamais votre enfant utiliser seul les escaliers jusqu’à ce qu’il sache se tenir à la rampe.");

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // AJOUT DES PREVENTIONS POUR INTOXICATIONS

        insertIntoPreventionInstruction(db, "Intoxications", "Les médicaments", "Ne laissez jamais de médicaments à portée de votre enfant","@drawable/p_intox1", "@drawable/p_intox2",
                        "• Rangez toujours les médicaments dans une armoire en hauteur, fermée à clé. \n" +
                        "• Ne laissez pas de médicaments dans un sac à main, sur une table... \n " +
                        "• Respectez toujours les doses de médicaments prescrites par le médecin, les délais entre chaque prise et lisez attentivement les notices d'utilisation.\n"+
                        "• Ne présentez jamais un médicament comme un bonbon.");

        insertIntoPreventionInstruction(db, "Intoxications", "Les produits ménagers et de bricolage", "Gardez les produits ménagers et de bricolage hors de portée de votre enfant","@drawable/p_intox3", "@drawable/p_intox4",
                        "• Rangez toujours les produits ménagers et de bricolage en hauteur, hors de portée des enfants et si possible dans un placard fermé à clé. \n" +
                        "• Expliquez à votre enfant les dangers que représentent ces produits et éloignez-le quand vous les utilisez \n " +
                        "• Ne transvasez jamais un produit ménager dans une autre bouteille.\n"+
                        "• Utilisez des produits munis de bouchons de sécurité. \n"+
                        "• Lisez attentivement les étiquettes des produits que vous utilisez pour en connaître la toxicité. Votre enfant peut, par exemple, s’intoxiquer avec des produits cosmétiques (dissolvant, teinture pour les cheveux).\n"+
                        "• Certaines plantes vertes d'intérieur peuvent être toxiques.");

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // AJOUT DES PREVENTIONS POUR BRULURES

        insertIntoPreventionInstruction(db, "Brulures", "L'eau du bain", "La température de l'eau du bain doit être à 37°C","@drawable/p_brul1", "@drawable/p_brul2",
                        "• Avant de mettre votre enfant dans le bain, vérifiez avec un thermomètre que l'eau du bain est à 37°C \n" +
                        "• Installez sur les robinets, si vous le pouvez, des dispositifs de sécurité qui permettent de contrôler la température de l'eau. \n " +
                        "• Ne laissez jamais votre enfant seul dans son bain : il pourrait se noyer mais aussi se brûler en ouvrant le robinet d’eau chaude");

        insertIntoPreventionInstruction(db, "Brulures", "Les objets chauds", "Éloignez votre enfant des appareils électroménagers chauds","@drawable/p_brul3", "@drawable/p_brul4",
                        "• Ne laissez jamais votre enfant seul dans la cuisine.  \n" +
                        "• Expliquez à votre enfant que la porte du four brûle. \n " +
                        "• Equipez-vous si possible d'un four à porte isotherme. \n"+
                        " Méfiez-vous des plaques de cuisson et des brûleurs chauds après leur arrêt.\n"+
                        " Évitez que votre enfant s’approche des objets brûlants : fer à repasser, ampoules électriques, appareils de chauffage... ");

        insertIntoPreventionInstruction(db, "Brulures", "Les liquides et aliments chauds", "Ne laissez jamais les récipients remplis d'un liquide ou d’un aliment chaud à la portée de votre enfant","@drawable/p_brul5", "@drawable/p_brul6",
                        "• Ne posez pas de plat chaud sur le rebord de la table ou du plan de travail. \n" +
                        "• Tournez les queues de casserole vers l'intérieur de la plaque de cuisson. \n " +
                        "• Ne passez pas à côté de votre enfant avec un récipient contenant un liquide bouillant. \n"+
                        "• Ne portez pas votre enfant dans vos bras lorsque vous utilisez un récipient rempli de liquide chaud ou lorsque vous faites la cuisine\n"+
                        "• Ne prenez pas votre enfant sur les genoux si vous buvez une boisson chaude \n"+
                        "• Vérifiez que votre enfant ne puisse pas tirer sur une nappe et renverser sur lui un liquide ou des aliments chauds\n"+
                        " N’utilisez jamais un micro-ondes pour chauffer .\n"+
                        " Le biberon. Agitez bien le biberon.\n"+
                        " Testez la température en faisant couler quelques gouttes sur l'intérieur de votre avant-bras.");

        insertIntoPreventionInstruction(db, "Brulures", "Les flammes et incendies", "Mettez hors de portée les objets risquant de brûler votre enfant ou de provoquer un incendie","@drawable/p_brul7", "@drawable/p_brul8",
                        "• •\tNe laissez pas d’allumettes et de briquets à la portée de votre enfant.  \n" +
                        "• •\tNe laissez jamais de bougies allumées à portée des enfants.  \n " +
                        "• •\tNe laissez pas votre enfant s'approcher d'une cheminée ou d'un barbecue.  \n"+
                        "• •\tN’allumez jamais une cheminée ou un barbecue avec de l’alcool. \n"+
                        "• Ne fumez pas en présence de votre enfant. \n"+
                        "•Faites vérifier vos installations électriques et de chauffage.");

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // AJOUT DES PREVENTIONS POUR ELECTROCUTIONS

        insertIntoPreventionInstruction(db, "Electrocutions", "Les prises et les fils électriques", "Equipez vos prises de dispositifs de sécurité","@drawable/p_elec1", "@drawable/p_elec2",
                        "• Apprenez à votre enfant à ne pas toucher ou jouer avec les prises de courant. \n" +
                        "• Faites installer des prises à éclipse ou des cache-prises à ventouse ou à clé \n " +
                        "• Ne laissez pas de rallonge électrique branchée. \n"+
                        "• Ne surchargez pas vos prises électriques avec des multiprises.");

        insertIntoPreventionInstruction(db, "Electrocutions", "Les appareils électriques", "Évitez d’utiliser un appareil électrique en présence d’eau","@drawable/p_elec3", "@drawable/p_elec4",
                        "• N'ouvrez jamais l'eau du robinet à proximité d'un appareil électrique en marche. \n" +
                        " Débranchez et rangez tous les appareils électriques dès que vous avez fini de les utiliser : sèche-cheveux, rasoir électrique, appareil d'épilation, chauffage mobile, fer à repasser... ");

        insertIntoPreventionInstruction(db, "Electrocutions", "Les installations", "Faites vérifier vos installations électriques","@drawable/p_elec5", "@drawable/p_elec6",
                        "• Ne laissez jamais de fils électriques dénudés.  \n" +
                        "• Faites vérifier vos installations : prises, interrupteurs...  \n " +
                        "• Faites installer, si possible, un système qui coupe le courant au moindre court-circuit (disjoncteur différentiel à haute sensibilité). ");

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        insertIntoHopital(db, "EHU 1er Novembre", "@drawable/photo_hopital_ehu", "Oran", "Usto Hai Essabah", "0798 81 75 07", "ehuoran.dz", "35.700257", "-0.582876");
        insertIntoHopital(db, "Centre Hospitalier et Universitaire d'Oran", "@drawable/photo_hopital_hospitalieruniversitaire", "Oran", "Boulevard Doctor Benzerdjeb", "041 41 22 38", "sante.dz", "35.694615", "-0.641152");
        insertIntoHopital(db, "Clinique Cherrak El Ghosli", "@drawable/photo_hopital_ghosli", "Oran", "Ibn Sina (Ex Delmonte)", "041 46 90 90", "cliniquecherrak.com", "35.693052", "-0.627057");
        insertIntoHopital(db, "Hopital pédiatrique de Canastel", "@drawable/photo_hopital_canastel", "Oran", "Canastel", "041 65 40 78", "Aucun site disponible", "35.738313", "-0.573346");
        insertIntoHopital(db, "Hopital Militaire Regional Universitaire d'Oran", "@drawable/photo_hopital_militaire", "Oran", "Ain Beida", "041 58 71 75", "Aucun site disponible", "35.670201", "-0.667395");
        insertIntoHopital(db, "Clinique Ennadjah", "@drawable/photo_hopital_ennadjah", "Oran", "Rond-Point Les 3 Cliniques", "041 70 60 42", "Aucun site disponible", "35.701584", "-0.597551");

        insertIntoHopital(db, "Hopital Mustapha Bacha", "@drawable/photo_hopital_chumustapha", "Alger", "Place 1er mai", "021 23 68 84", "chu-mustapha.dz", "36.761858", "3.055272");
        insertIntoHopital(db, "Clinique Aya", "@drawable/photo_hopital_cliniqueaya", "Alger", "Zonka, Birkhadem", "023 57 80 97", "cliniqueaya.dz", "36.706397", "3.066402");
        insertIntoHopital(db, "Hopital Birtraria", "@drawable/photo_hopital_birtraria", "Alger", "El Biar", "021 90 00 11", "Aucun site disponible", "36.773126", "3.040215");
        insertIntoHopital(db, "Hopital El Kettar", "@drawable/photo_hopital_kettar", "Alger", "Casbah", "021 96 96 96", "Aucun site disponible", "36.785197", "3.051431");
        insertIntoHopital(db, "Hopital Lamine Debaghine (Ex: Maillot)", "@drawable/photo_hopital_debaghine", "Alger", "Bab El Oued", "021 96 06 06", "Aucun site disponible", "36.794825", "3.049485");







        Log.e("table prevention", "element1");

        insertIntoAccident("T20 - T31", "Brulure", "@drawable/flame", db);
        insertIntoAccident("W00 - T19", "Chute", "@drawable/slide", db);
        insertIntoAccident("T509", "Intoxication", "@drawable/icon_ingestion", db); // ingestion
        insertIntoAccident("J690", "Inhalation", "@drawable/icon_inhalation", db);


        inserIntoDiagnostique("Brulure grave", "grave", db);
        inserIntoDiagnostique("Brulure simple", "faible", db);

        inserIntoDiagnostique("Traumatisme du membre", "faible", db);
        inserIntoDiagnostique("Traumatisme du membre (luxation)", "faible", db);
        inserIntoDiagnostique("Fracture vertébrale", "grave", db);
        inserIntoDiagnostique("Traumatisme cranien", "grave", db);

        inserIntoDiagnostique("Intoxication", "faible", db);  // ingestion

        inserIntoDiagnostique("Enfant tousse", "faible", db);
        inserIntoDiagnostique("Enfant ne respire pas", "grave", db);

        insertIntoGesteSecour("@drawable/refroidir","Refroidir rapidement les brulures avec l'eau froide(entre 10 et 15 degres) pendant 5mn","Brulure simple",db);
        insertIntoGesteSecour("@drawable/cloques","Ne pas percer les cloques","Brulure simple",db);
        insertIntoGesteSecour("@drawable/pansement","Proteger avec un pansement sterile etadhesif","Brulure simple",db);
        insertIntoGesteSecour("@drawable/vaccin","Vérifiez la vaccination antitanique","Brulure simple",db);
        insertIntoGesteSecour("@drawable/appeler","Appeler un medecin dans le cas ou les signe suivants apparaissent: forte rougeur, gonflement...","Brulure simple",db);

        insertIntoGesteSecour("@drawable/refroidir","Arroser rapidement à l’eau froide (10 et 15 degré) pendant 5 mn","Brulure grave",db);
        insertIntoGesteSecour("@drawable/retirer_vetement","Retirer les vêtements de l’enfant sans arracher ceux qui collent à la peau","Brulure grave",db);
        insertIntoGesteSecour("@drawable/allonger_enfant","Allonger l’enfant sur un drap propre","Brulure grave",db);
        insertIntoGesteSecour("@drawable/appeler","Alerter les secours aussi vite que possible","Brulure grave",db);
        insertIntoGesteSecour("@drawable/respiration","Surveiller la conscience, la respiration, le pouls, jusqu’à l’arrivée des secours","Brulure grave",db);

        insertIntoGesteSecour("@drawable/immobiliser_fracture","Immobiliser ou empêcher de bouger : \n  Membre inférieur : caler le membre avec des vêtements, couverture, coussin dans la position où il se trouve","Traumatisme du membre",db);
        insertIntoGesteSecour("@drawable/membre_inf","Membre supérieur : caler à l’aide de vêtement, triangle de toile voir figures ","Traumatisme du membre",db);
        insertIntoGesteSecour("@drawable/compression","En cas de fracture ouverte recouvrir la plaie avec des compresses stériles ","Traumatisme du membre",db);
        insertIntoGesteSecour("@drawable/appeler","Alerter les secours : pour réaliser une radiographie","Traumatisme du membre",db);
        insertIntoGesteSecour("@drawable/suveillez_enfant","Surveiller l’enfant en attendant","Traumatisme du membre",db);

        insertIntoGesteSecour("@drawable/pas_remettre_fracture","Ne jamais essayer de remettre en place l'articulation luxée, car on peut provoquer des lésions irréversibles. \n Immobiliser l'articulation dans la position où elle se trouve","Traumatisme du membre (luxation)",db);
        insertIntoGesteSecour("@drawable/appeler","Alerter les secours : pour réaliser une radiographie ","Traumatisme du membre (luxation)",db);
        insertIntoGesteSecour("@drawable/suveillez_enfant","Surveiller l’enfant en attendant...","Traumatisme du membre (luxation)",db);

        insertIntoGesteSecour("@drawable/chute_tete","Maintenez le blessé immobile et, aussi rapidement que possible, tenez sa tête en posant vos mains à plat de chaque côté. Grâce à ce maintien on évite tout mouvement de la colonne. De plus, la rectitude de l'axe tête-cou-tronc droit est ainsi conservée","Fracture vertébrale",db);
        insertIntoGesteSecour("@drawable/suveillez_enfant","Surveillez l’enfant jusqu'à l'arrivée des secours et prêtez attention à d'éventuels signes de gravité. ","Fracture vertébrale",db);
        insertIntoGesteSecour("@drawable/pls","En cas d'inconscience, positionnez prudemment la victime en position latérale de sécurité, malgré le risque de traumatisme liée à cette situation délicate","Fracture vertébrale",db);

        insertIntoGesteSecour("@drawable/allonger","Mettre la victime au repos en position allongée","Traumatisme cranien",db);
        insertIntoGesteSecour("@drawable/appeler","Alerter les secours rapidement","Traumatisme cranien",db);
        insertIntoGesteSecour("@drawable/pls","surveiller la conscience ; si la victime devient inconsciente, la mettre en position latérale de sécurité à condition que la respiration reste efficace","Traumatisme cranien",db);

        insertIntoGesteSecour("@drawable/appeler","Alerter les secours immédiatement","Intoxication",db);  //ingestion
        insertIntoGesteSecour("@drawable/pas_vomir","Ne pas faire boire","Intoxication",db);
        insertIntoGesteSecour("@drawable/pas_boire","Ne pas faire vomir","Intoxication",db);
        insertIntoGesteSecour("@drawable/pas_medicament","Ne pas donner d’antidotes\n" +
                "Ni de pansement gastrique\n" +
                "ou faire lavage gastrique ","Intoxication",db);
        insertIntoGesteSecour("@drawable/respiration","Surveiller la conscience, la respiration et le pouls, jusqu’à l’arrivée des secours","Intoxication",db);


        insertIntoGesteSecour("@drawable/bouche_enfant","ne pas suspendre l’enfant par les pieds \n ne pas introduire les doigts dans la bouche de l’enfant ","Enfant tousse",db);

        insertIntoGesteSecour("@drawable/assise_debout","si l’objet est bloqué, conduire l’enfant en position assise ou debout au service d’urgence le plus proche. ","Enfant tousse",db);


        insertIntoGesteSecour("@drawable/appeler","Appeler le SAMU","Enfant ne respire pas",db);
        insertIntoGesteSecour("@drawable/les_tapes","Essayer la méthode des claques dans le dos \n Placer le bébé à plat sur les genoux du sauveteur en lui soutenant la tête \n Donner 5 tapes avec la main entre les omoplates de façon à mobiliser le corps étranger ","Enfant ne respire pas",db);
        insertIntoGesteSecour("@drawable/heimlich","En cas d’échec de l’expulsion, appliquer immédiatement la méthode de Heimlich : \n Placer la main sur le dos du bébé et le retourner, tête basse en la soutenant. \n Effectuer avec 3 doigts 4 poussés, ou pressions sur le devant du thorax au milieu de setrum. ","Enfant ne respire pas",db);






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

    public void insertIntoHopital(SQLiteDatabase db, String nom, String photo, String wilaya, String quartier, String numero, String site, String lat, String longt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_NAME, nom);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_PHOTO, photo);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_WILAYA, wilaya);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_QUARTIER, quartier);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_NUMERO, numero);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_SITE, site);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_LAT, lat);
        contentValues.put(FirstAidManager.HopitalManager.HOPITAL_LONG, longt);
        db.insert(FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME, null, contentValues);

    }

    public Cursor getAllAccidents(SQLiteDatabase db) {

        Cursor cursor;
        String[] projections = {FirstAidManager.AccidentsMangager.ID_ACCIDENT,
                                FirstAidManager.AccidentsMangager.NOM_ACCIDENT,
                                FirstAidManager.AccidentsMangager.ICON_ACCident};
        cursor = db.query(FirstAidManager.AccidentsMangager.ACCIDENTS_TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }


    public Cursor getAllHospitals(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {FirstAidManager.HopitalManager.HOPITAL_PHOTO,
                                FirstAidManager.HopitalManager.HOPITAL_NAME,
                                FirstAidManager.HopitalManager.HOPITAL_QUARTIER,
                                FirstAidManager.HopitalManager.HOPITAL_NUMERO};
        cursor = db.query(FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public void deleteContact(SQLiteDatabase db,String name) {
        db.delete(FirstAidManager.ContactManager.CONTACT_TABLE_NAME, FirstAidManager.ContactManager.NOM_CONTACT+"=?", new String[]{name});

    }

    public Cursor getGestesDeSecours(SQLiteDatabase db, String requete, String diagnostique) {
        Cursor cursor;
        cursor = db.rawQuery(requete, new String[] {diagnostique});
        return cursor;
    }

    public Cursor getHospitalInformations(SQLiteDatabase db, String requete, String nomHopital) {
        Cursor cursor;
        cursor = db.rawQuery(requete, new String[] {nomHopital});
        return cursor;
    }

    public Cursor getPreventionInstructions(SQLiteDatabase db, String requete, String nomAccident) {
        Cursor cursor;
        cursor = db.rawQuery(requete, new String[] {nomAccident});
        return cursor;
    }

    public Cursor getHospitals(SQLiteDatabase db, String requete, String wilaya) {
        Cursor cursor;
        cursor = db.rawQuery(requete, new String[] {wilaya});
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
