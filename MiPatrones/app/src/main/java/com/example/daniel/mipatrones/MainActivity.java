package com.example.daniel.mipatrones;

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

    private ListView mListView;
    private ProgressBar mProgressBar;
    private MainActivityPresenter mMainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertarPersona();

        mListView = (ListView) findViewById(R.id.list);
        mListView.setOnItemClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        // Llamada al Presenter
        mMainActivityPresenter = new MainActivityPresenterImpl(this);
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
    public void setItems(List<String> items) {
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    // Mostrar mensaje en la UI
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Evento al dar clic en la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mMainActivityPresenter.onItemClicked(position);
    }

    private void insertarPersona() {
        // Instancia la clase Estudiante y realiza la inserción de datos
        Persona persona = new Persona("1-1000-1000", "Juan", "Imagen1");
        // inserta el estudiante, se le pasa como parametro el contexto de la app
        long newRowId = persona.insertar(getApplicationContext());

        persona = new Persona("2-2000-2000", "Pedro", "Imagen2");
        // inserta el estudiante, se le pasa como parametro el contexto de la app
        newRowId = persona.insertar(getApplicationContext());

        persona = new Persona("3-3000-3000", "Pablo", "Imagen3");
        // inserta el estudiante, se le pasa como parametro el contexto de la app
        newRowId = persona.insertar(getApplicationContext());
    }
}
