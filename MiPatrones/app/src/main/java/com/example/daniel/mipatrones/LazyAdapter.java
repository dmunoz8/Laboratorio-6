package com.example.daniel.mipatrones;

/**
 * Created by Jeffry on 10/06/2017.
 */
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class LazyAdapter extends BaseAdapter {

    private List<Persona> personas;
    private Context mContext;
    private Resources mResources;
    private String mPackage;

    public LazyAdapter(List<Persona> data, Context context, Resources resources, String _package) {
        personas = data;
        mContext = context;
        mResources = resources;
        mPackage = _package;
    }

    public int getCount() {
        return personas.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = null;
        //
        Persona mPersona = personas.get(position);
        //
        rowView = inflater.inflate(R.layout.list_row, parent, false);
        //
        TextView nombre = (TextView)rowView.findViewById(R.id.nombre);
        ImageView imagen = (ImageView)rowView.findViewById(R.id.imagen);
        nombre.setText(mPersona.getNombre());
        int resID = mResources.getIdentifier(mPersona.getImagen() , "drawable", mPackage);
        imagen.setImageResource(resID);

        return rowView;
    }
}
