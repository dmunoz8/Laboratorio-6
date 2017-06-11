package com.example.daniel.mipatrones;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    private TextView nombre;
    private ImageView imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Parametros desde la actividad DetailActivity
        Bundle bundle = getActivity().getIntent().getExtras();
        Persona mFilosofo = bundle.getParcelable(MainActivity.EXTRA_MESSAGE);
        String mPackage = bundle.getString(DetailActivity.EXTRA_MESSAGE);

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        nombre = (TextView)view.findViewById(R.id.nombre);
        imagen = (ImageView)view.findViewById(R.id.imagen);

        nombre.setText(mFilosofo.getNombre());

        int resID = getResources().getIdentifier(mFilosofo.getImagen() , "drawable", mPackage);
        imagen.setImageResource(resID);

        return view;
    }
}
