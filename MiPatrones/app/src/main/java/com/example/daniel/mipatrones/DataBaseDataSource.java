package com.example.daniel.mipatrones;

import java.util.List;

// Capa de datos (Model)
//    Obtiene los valores de la fuente de datos
public interface DataBaseDataSource {

    List<Persona> obtainItems () throws BaseDataItemsException;
}
