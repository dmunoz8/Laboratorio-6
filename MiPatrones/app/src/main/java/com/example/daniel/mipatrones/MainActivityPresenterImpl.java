package com.example.daniel.mipatrones;

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

    public MainActivityPresenterImpl(MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
        // Capa de negocios (Interactor)
        this.mGetListItemsInteractor = new GetListItemsInteractorImpl();
    }

    @Override public void onResume() {
        if (mMainActivityView != null) {
            mMainActivityView.showProgress();
        }

        // Obtener los items de la capa de negocios (Interactor)
        mGetListItemsInteractor.getItems(this);
    }

    // Evento de clic en la lista
    @Override public void onItemClicked(int position) {
        if (mMainActivityView != null) {
            mMainActivityView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override public void onDestroy() {
        mMainActivityView = null;
    }

    @Override public void onFinished(List<String> items) {
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


