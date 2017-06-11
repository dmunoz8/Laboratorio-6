package com.example.daniel.mipatrones;

import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.ArrayList;


// Capa de datos (Model)
//    Obtiene los valores de la fuente de datos
public class DataBaseDataSourceImpl implements DataBaseDataSource {
    private Context mContext;
    public DataBaseDataSourceImpl(Context context) {
        mContext = context;
    }

    @Override
    public List<Persona> obtainItems() throws BaseDataItemsException {
        List<Persona> items;
        try {
            items = getPersonas(mContext);

        } catch (Exception e) {
            throw new BaseDataItemsException(e.getMessage());
        }

        return items;
    }


    public List<Persona> getPersonas(Context applicationContext) {
        ArrayList<Persona> lista = new ArrayList<Persona>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(applicationContext);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA, null);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        String id = c.getString(c.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA + "." + DataBaseContract.DataBaseEntry._ID));
                        String nombre = c.getString(c.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA + "." + DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE));
                        String imagen = c.getString(c.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA + "." + DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGEN));
                        Persona p = new Persona(id, nombre, imagen);
                        lista.add(p);
                    } while (c.moveToNext());
                }
            }
        }
        finally {
            c.close();
            db.close();
        }
        return lista;
    }
}
