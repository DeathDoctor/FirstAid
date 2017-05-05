package com.example.lkhedma.firstaid;

/**
 * Created by LKHEDMA on 07/04/2017.
 */

public class FirstAidManager {

    public static abstract class ContactManager {

        public static final String CONTACT_TABLE_NAME = "contacts";
        public static final String ID_CONTACT = "id";
        public static final String NOM_CONTACT = "nom";
        public static final String NUMERO_CONTACT = "numero";
        public static final String SPECIFICATION_CONTACT = "specification";

    }

    public static abstract class AccidentEnumManganager {

        public static final String ACCIDENT_ENUM_TABLE_NAME = "accident_enum";
        public static final String NOM_ACCIDENT = "nom_accident";

    }

    // public static abstract class

    public static abstract class AccidentsMangager {

        public static final String ACCIDENTS_TABLE_NAME = "accidents";
        public static final String ID_ACCIDENT = "id_accident";
        public static final String NOM_ACCIDENT = "nom_accident";
        public static final String ICON_ACCident = "icon_accident";

    }

    public static abstract class DiagnostiqueManager {

        public static final String DIAGNOSTIC_TABLE_NAME = "diagnostique";
        public static final String TITRE_DIAGNOSTIQUE = "titre_diagnostique";
        public static final String GRAVITE_DIAGNOSTIC = "gravite_diagnostic";
    }

    public static abstract class GesteSecourManager {

        public static final String GESTE_SECOUR_TABLE_NAME = "geste_secour";
        public static final String DESCRIPTION_GESTE_SECOUR = "description_geste_secour";
        public static final String ICON_GESTE_SECOUR = "icon_geste_secour";
        public static final String DIAGNOSTIC_GESTE_SECOUR = "titre_diagnostic";
    }









}
