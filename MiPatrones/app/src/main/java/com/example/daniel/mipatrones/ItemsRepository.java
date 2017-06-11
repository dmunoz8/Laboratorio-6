package com.example.daniel.mipatrones;

import java.util.List;

// Capa de datos (Model)
//    El repositorio decide de que fuente de datos obtiene los valores
public interface ItemsRepository {

    List<Persona> obtainItems() throws CantRetrieveItemsException;
}
