package com.example.daniel.mipatrones;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // Nombre de la base de datos
    public static final String DATABASE_NAME = "Personas.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Crear la base de datos de la app
        db.execSQL(DataBaseContract.SQL_CREATE_PERSONA);
        db.execSQL(DataBaseContract.SQL_INSERT_PERSONAS);
    }
    // implementamos el metodo para la actualizacion de la base de datos
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseContract.SQL_DELETE_PERSONA);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

