package com.example.daniel.mipatrones;

import android.provider.BaseColumns;


public final class DataBaseContract {
    // Para asegurar que no se instancie la clase hacemos el constructor privado
    private DataBaseContract() {
    }

    public static class DataBaseEntry implements BaseColumns {
        // Clase Persona
        public static final String TABLE_NAME_PERSONA = "Persona";
        // private String identificacion; Utilizamos DataBaseEntry._ID de BaseColumns
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        // private String Nombre
        public static final String COLUMN_NAME_IMAGEN = "imagen";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_PERSONA =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_PERSONA + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_IMAGEN + TEXT_TYPE + " )";

    public static final String SQL_DELETE_PERSONA =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_PERSONA;


}
