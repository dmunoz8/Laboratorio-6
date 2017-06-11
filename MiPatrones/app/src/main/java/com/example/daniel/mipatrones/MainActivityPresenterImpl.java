package com.example.daniel.mipatrones;

import android.content.Context;

import java.util.List;

// Capa de presentacion (Presenter)
//    Presenter (P) -> Implementacion de MainActivityPresenter y de GetListItemsInteractor.OnFinishedListener
public class MainActivityPresenterImpl implements
        // Implementacion de MainActivityPresenter de la Capa (P)
        MainActivityPresenter,
        // La interface OnFinishedListener de GetListItemsInteractor para obtener el valor de retorno de la capa Iteractor (P)
        GetListItemsInteractor.OnFinishedListener {

    private MainActivityView mMainActivityView;
    private GetListItemsInteractor mGetListItemsInteractor;

    public MainActivityPresenterImpl(MainActivityView mainActivityView, Context context) {
        this.mMainActivityView = mainActivityView;
        // Capa de negocios (Interactor)
        this.mGetListItemsInteractor = new GetListItemsInteractorImpl(context);
    }

    @Override public void onResume() {
        if (mMainActivityView != null) {
            mMainActivityView.showProgress();
        }

        // Obtener los items de la capa de negocios (Interactor)
        mGetListItemsInteractor.getItems(this);
    }

    // Evento de clic en la lista
    @Override public void onItemClicked(Persona p) {
        if (mMainActivityView != null) {
            mMainActivityView.showMessage(String.format("Se seleccionó a la persona %s", p.getNombre()));
            mMainActivityView.showDetail(p);
        }
    }

    @Override public void onDestroy() {
        mMainActivityView = null;
    }

    @Override public void onFinished(List<Persona> items) {
        if (mMainActivityView != null) {
            mMainActivityView.setItems(items);
            mMainActivityView.hideProgress();
        }
    }

    // Retornar la vista
    public MainActivityView getMainView() {
        return mMainActivityView;
    }
}


