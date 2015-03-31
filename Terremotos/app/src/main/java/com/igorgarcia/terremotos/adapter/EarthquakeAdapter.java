package com.igorgarcia.terremotos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;

import java.util.List;

/**
 * Created by cursomovil on 26/03/15.
 *
 * Adaptador para mostrar los terremotos en pantalla
 */
public class EarthquakeAdapter extends ArrayAdapter <EarthQuake> {

    private int resource;

    public EarthquakeAdapter(Context context, int resource, List<EarthQuake> objects) {
        super(context, resource, objects);

        this.resource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Enlazamos los datos con los controles del fragmento

        LinearLayout layout= (LinearLayout) convertView;
        if (convertView==null){
            layout= new LinearLayout(getContext());
            LayoutInflater li;
            String inflater= Context.LAYOUT_INFLATER_SERVICE;
            li= (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource,layout,true);

        }else{
            layout=(LinearLayout) convertView;
        }

        EarthQuake earthQuake=getItem(position);

        TextView Magnitud=(TextView) layout.findViewById(R.id.TXTMagnitud);
        TextView Datos=(TextView) layout.findViewById(R.id.TxtDatoarriba);
        TextView Fecha=(TextView) layout.findViewById(R.id.TxtDatoAbajo);

        //enlazamos los controles con los datos
        Magnitud.setText( String.valueOf(earthQuake.getMagnitud()));
        Datos.setText(earthQuake.getPlace());
        Fecha.setText(earthQuake.getTime().toString());

        return layout;

    }
}
