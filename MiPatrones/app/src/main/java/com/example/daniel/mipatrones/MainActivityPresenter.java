package com.example.daniel.mipatrones;

import java.util.List;

// Capa de presentacion (Presenter)
//    Presenter (P) -> Interface de la capa Presenter implementado por MainActivityPresenterImpl
public interface MainActivityPresenter {

    // resumir
    void onResume();

    // evento cuando se hace clic en la lista de elementos
    void onItemClicked(Persona persona);

    // destruir
    void onDestroy();
}