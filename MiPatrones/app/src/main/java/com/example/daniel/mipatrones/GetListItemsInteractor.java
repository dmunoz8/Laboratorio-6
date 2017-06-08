package com.example.daniel.mipatrones;

import java.util.List;

// Capa de Negocios (Presenter o Controller)
//    Interface de la capa de negocio (P o M) para obtener los resultados de la lista de elementos a mostrar
//       Representa el Interactor (casos de uso), se comunica con las entidades y el presenter
public interface GetListItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void getItems(OnFinishedListener listener);
}
