package com.example.daniel.mipatrones;

import java.util.List;

// Capa de datos (Model)
//    El repositorio decide de que fuente de datos obtiene los valroes
public interface ItemsRepository {

    List<String> obtainItems() throws
            CantRetrieveItemsException;

}
