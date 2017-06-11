package com.example.daniel.mipatrones;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.Date;

import java.util.List;

// Este ejemplo realiza una implementacion de una app con el patron MVP y utilizando las recomendaciones de Clean Architecture
//    Se implementa con interfaces para mantener las clases desacopladas entre capas
//    La app muestra al usuario una lista de elementos

//    MainActivity es la actividad principal de la app he implementa la interface MainActivityView
//    View (V) llama a Presenter (P) cuando se da una interaccion  de usuario
//    La implementacion de Presenter (P) llama a la clase de negocio GetListItemsInteractor (P) para obtener los resultados,
//       en este caso la lista de elementos a mostrar
//    La implementacion de GetListItemsInteractor (P) retorna los resultados y devuelve el control a la implementacion de Presenter (P)
//       Interactor utiliza las clases de la capa de datos para obtener los items
//    La implementacion de Presenter (P) llama a los metodos del View (V) para actualizar la UI por medio de su interface
//             Las interfaces de View, Presenter, Interactor y Listener son utilizadas para lograr el desacoplamiento

// Capa de presentacion (Vista)
public class MainActivity extends AppCompatActivity implements
        // interface View (V) para implementar los metodos de UI
        MainActivityView,
        // interface para implementar el listener del metodo onItemClick de la lista
        AdapterView.OnItemClickListener {

    public static String EXTRA_MESSAGE = "MPersona";
    private ListView mListView;
    private ProgressBar mProgressBar;
    private MainActivityPresenter mMainActivityPresenter;
    private List<Persona> mPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mListView = (ListView) findViewById(R.id.lista);
        mListView.setOnItemClickListener(this);
        // Llamada al Presenter
        mMainActivityPresenter = new MainActivityPresenterImpl(this, getApplicationContext());
    }

    @Override
    public void showDetail(Persona persona) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, persona);
        intent.putExtra(DetailActivity.EXTRA_MESSAGE, getPackageName());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainActivityPresenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        mMainActivityPresenter.onDestroy();
        super.onDestroy();
    }

    // Mostrar el progreso en la UI del avance de la tarea a realizar
    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.INVISIBLE);
    }

    // Esconder el indicador de progreso de la UI
    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mListView.setVisibility(View.VISIBLE);
    }

    // Mostrar los items de la lista en la UI
    //    Con la lista de items muestra la lista
    @Override
    public void setItems(List<Persona> items) {
        mPersonas = items;
        Resources mResources = getResources();
        String mPackage = getPackageName();
        LazyAdapter mAdapter = new LazyAdapter(items, getApplicationContext(), mResources, mPackage);
        mListView.setAdapter(mAdapter);
    }

    // Mostrar mensaje en la UI
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Evento al dar clic en la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mMainActivityPresenter.onItemClicked(mPersonas.get(position));
    }
}
