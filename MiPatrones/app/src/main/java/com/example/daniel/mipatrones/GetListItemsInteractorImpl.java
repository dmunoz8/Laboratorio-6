package com.example.daniel.mipatrones;

import android.os.Handler;

import java.util.List;

// Capa de Negocios (Presenter o Controller)
//    Implementacion de GetListItemsInteractor de la capa de negocio (P o M) para obtener los resultados de la lista de elementos a mostrar
//    Representa el Interactor (casos de uso), se comunica con las entidades y el presenter
public class GetListItemsInteractorImpl implements GetListItemsInteractor {

    private ItemsRepository mItemsRepository;

    @Override public void getItems(final OnFinishedListener listener) {
        // Enviamos el hilo de ejecucion con un delay para mostar la barra de progreso
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {

                List<String> items = null;
                mItemsRepository = new ItemsRepositoryImpl();
                try {

                    // obtenemos los items
                    items = mItemsRepository.obtainItems();

                } catch (CantRetrieveItemsException e) {
                    e.printStackTrace();
                }

                // Al finalizar retornamos los items
                listener.onFinished(items);
            }
        }, 2000);
    }
}
