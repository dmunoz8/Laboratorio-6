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

    @Override
    public List<String> obtainItems() throws BaseDataItemsException {

        List<String> items = null;

        try {

            // TODO: Obtener de la base de datos
            items = createArrayList();

        } catch (Exception e) {
            throw new BaseDataItemsException(e.getMessage());
        }

        return items;
    }

    // Esta lista debe recuperarla de la base de datos
    //   para el ejemplo la inicializamos con datos dummy
    private List<String> createArrayList() {
        return Arrays.asList(
                "Soda 1",
                "Soda 2",
                "Soda 3",
                "Soda 4",
                "Soda 5",
                "Soda 6",
                "Soda 7",
                "Soda 8",
                "Soda 9",
                "Soda 10"
        );
    }

    public String[] buscarPersonas(Context applicationContext) {
        ArrayList<String> lista = new ArrayList<String>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(applicationContext);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA
                + " where fechaInicio = ", null);
        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                do {
                    String nombre = c.getString(1);
                    lista.add(nombre);
                } while (c.moveToNext());
            }
        }
        c.close();
        db.close();
        int tamano = lista.size();
        String[] values = new String[tamano];
        for (int i = 0; i < tamano; i++) {
            values[i] = lista.get(i);
        }
        return values;
    }
}
