package com.example.daniel.mipatrones;

import java.util.List;

// Capa de datos (Model)
//    El repositorio decide de que fuente de datos obtiene los valroes
public class ItemsRepositoryImpl implements ItemsRepository {

    private DataBaseDataSource mDataBaseDataSource;

    @Override
    public List<String> obtainItems() throws CantRetrieveItemsException {

        List<String> items = null;
        // Se realizan las llamadas a las fuentes de datos de donde se obtienen los datos
        //    Ejemplo: base de datos local, archivos locales, archivos en la red, bases de datos en la red

        try {

            mDataBaseDataSource = new DataBaseDataSourceImpl();
            items = mDataBaseDataSource.obtainItems();

        } catch (BaseDataItemsException e) {
            throw new CantRetrieveItemsException(e.getMessage());
        }

        return items;
    }
}
