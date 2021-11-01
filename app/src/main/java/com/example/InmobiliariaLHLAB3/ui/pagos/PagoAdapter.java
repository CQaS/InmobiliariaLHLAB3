package com.example.InmobiliariaLHLAB3.ui.pagos;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;

import com.example.InmobiliariaLHLAB3.R;
import com.example.InmobiliariaLHLAB3.modelo.Contrato;
import com.example.InmobiliariaLHLAB3.modelo.Pago;
import com.example.InmobiliariaLHLAB3.ui.contratos.ContratosViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



public class PagoAdapter extends ArrayAdapter<Contrato>
{

    private  Context context;
    List<Contrato> contratos;
    private LayoutInflater li;
    private ContratosViewModel vm;
    Button btVerpagos;


    public PagoAdapter(@NonNull Context context, int resource, List<Contrato> contratos, LayoutInflater li)
    {
        super(context, resource, contratos);
        this.context = context;
        this.contratos = contratos;
        this.li = li;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View item= convertView;

        if(item == null)
        {
            item = li.inflate(R.layout.contrato_fragment,parent,false);
        }


        Contrato contrato = contratos.get(position);

        TextInputEditText fechaIngreso = item.findViewById(R.id.etdFechaIngreso);
        LocalDateTime fi = LocalDateTime.parse(contrato.getFe_ini());
        LocalDate fff = fi.toLocalDate();
        fechaIngreso.setText(fff.toString());

        TextInputEditText fechaSalida = item.findViewById(R.id.etdFechaSalida);
        LocalDateTime fc = LocalDateTime.parse(contrato.getFe_fin());
        LocalDate hhh = fc.toLocalDate();
        fechaSalida.setText(hhh.toString());

        TextInputEditText direccionInm = item.findViewById(R.id.etdDireccionInmueble);
        direccionInm.setText(contrato.getInmueble().getDireccion_in());

        btVerpagos = item.findViewById(R.id.btVerpagos);

        btVerpagos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", contrato.getId());

                Navigation.findNavController((Activity) context, R.id.nav_host_fragment)
                        .navigate(R.id.pagoFragment, bundle);
            }
        });

        return  item;
    }
}
