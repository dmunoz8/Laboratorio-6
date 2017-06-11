package com.example.daniel.mipatrones;

import java.util.List;

// Capa de presentacion (Vista)
//    Implementado por MainActivity
public interface MainActivityView {

    // Mostrar el progreso en la UI del avance de la tarea a realizar
    void showProgress();

    // Esconder el indicador de progreso de la UI
    void hideProgress();

    // Mostrar los items de la lista en la UI
    void setItems(List<Persona> items);

    // Mostrar mensaje en la UI
    void showMessage(String message);

    //mostrar detalle de un item de la lista en un fragmento
    void showDetail(Persona persona);
}
