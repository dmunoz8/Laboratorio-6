package com.example.daniel.mipatrones;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;


public class Persona implements Parcelable{

    private String id;
    private String nombre;
    private String imagen;

    public Persona()
    {

    }

    public Persona(String id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    protected Persona(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        imagen = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(imagen);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }
        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    public long insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE, getNombre());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGEN, getImagen());
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA, null,
                values);
    }


}
