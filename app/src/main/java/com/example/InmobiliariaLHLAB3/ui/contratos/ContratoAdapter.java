package com.example.InmobiliariaLHLAB3.ui.contratos;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.InmobiliariaLHLAB3.R;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.InmobiliariaLHLAB3.modelo.Contrato;

public class ContratoAdapter extends ArrayAdapter<Contrato>
{

    private  Context context;
    private List<Contrato> contratos;
    private LayoutInflater li;

    public ContratoAdapter(@NonNull Context context, int resource, @NonNull List<Contrato> objects, LayoutInflater layoutInflater)
    {
        super(context, resource, objects);

        context = context;
        contratos = objects;
        li = layoutInflater;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View item= convertView;

        if(item == null)
        {
            item= li.inflate(R.layout.contrato_fragment,parent,false);
        }

        Contrato contrato =contratos.get(position);
        Button btn = item.findViewById(R.id.btVerpagos);
        btn.setVisibility(View.INVISIBLE);


        TextInputEditText fechaIngreso= item.findViewById(R.id.etdFechaIngreso);
        LocalDateTime fi = LocalDateTime.parse(contrato.getFe_ini());
        LocalDate fff = fi.toLocalDate();
        fechaIngreso.setText(fff.toString());

        TextInputEditText fechaSalida= item.findViewById(R.id.etdFechaSalida);
        LocalDateTime fc = LocalDateTime.parse(contrato.getFe_fin());
        LocalDate hhh = fc.toLocalDate();
        fechaSalida.setText(hhh.toString());

        TextInputEditText direccionInmueble=item.findViewById(R.id.etdDireccionInmueble);
        direccionInmueble.setText(contrato.getInmueble().getDireccion_in());

        return  item;
    }
}
