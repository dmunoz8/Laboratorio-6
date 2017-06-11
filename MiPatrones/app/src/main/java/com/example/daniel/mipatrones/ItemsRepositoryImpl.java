package com.example.daniel.mipatrones;

import android.content.Context;

import java.util.List;

// Capa de datos (Model)
//    El repositorio decide de que fuente de datos obtiene los valroes
public class ItemsRepositoryImpl implements ItemsRepository {

    private DataBaseDataSource mDataBaseDataSource;
    private Context mContext;

    public ItemsRepositoryImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public List<Persona> obtainItems() throws CantRetrieveItemsException {

        List<Persona> items = null;
        // Se realizan las llamadas a las fuentes de datos de donde se obtienen los datos
        //    Ejemplo: base de datos local, archivos locales, archivos en la red, bases de datos en la red

        try {
            mDataBaseDataSource = new DataBaseDataSourceImpl(mContext);
            items = mDataBaseDataSource.obtainItems();

        } catch (BaseDataItemsException e) {
            throw new CantRetrieveItemsException(e.getMessage());
        }

        return items;
    }
}
