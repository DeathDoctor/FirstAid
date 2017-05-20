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

    public static abstract class PreventionAccidentManager {

        public static final String PREVENTION_ACCIDENT_TABLE_NAME = "accident_prevention";
        public static final String PREVENTION_ACCIDENT_NAME = "nom_accident_prevention";
        public static final String PREVENTION_ACCIDENT_ICON = "icon_accident_prevention";

    }

    public static abstract class PreventnionInstructionManager {

        public static final String PREVENTION_INSTRUCTION_TABLE_NAME = "prevention_instruction";
        public static final String PREVENTION_INSTRUCTION_TITLE = "prevention_instruction_titre";
        public static final String PREVENTION_INSTRUCTION_SUBTITLE = "prevention_instruction_soustitre";
        public static final String PREVENTION_INSTRUCTION_FIRST_ICON = "prevention_instruction_icon_one";
        public static final String PREVENTION_INSTRUCTION_SECOND_ICON = "prevention_instruction_icon_two";
        public static final String PREVENTION_INSTRUCTION_DESCRIPTION = "prevention_instruction_description";

        public static final String PREVENTION_INSTRUCTION_ACCIDENT_NAME = "nom_accident_prevention";

    }

    public static abstract class HopitalManager {

        public static final String HOPITAL_TABLE_NAME = "hopital";
        public static final String HOPITAL_NAME = "nom_hopital";
        public static final String HOPITAL_PHOTO = "photo_hopital";
        public static final String HOPITAL_WILAYA = "wilaya_hopital";
        public static final String HOPITAL_QUARTIER = "quartier_hopital";
        public static final String HOPITAL_NUMERO = "numero_hopital";
        public static final String HOPITAL_SITE = "site_hopital";
        public static final String HOPITAL_LAT = "lat_hopital";
        public static final String HOPITAL_LONG = "long_hopital";

    }









}
