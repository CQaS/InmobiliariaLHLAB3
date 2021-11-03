package com.example.InmobiliariaLHLAB3.ui.pagos;

import androidx.annotation.RequiresApi;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.InmobiliariaLHLAB3.R;
import com.example.InmobiliariaLHLAB3.modelo.Pago;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PagosAdapter extends ArrayAdapter<Pago>
{
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater li;
    private PagosViewModel vm;


    public PagosAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects,LayoutInflater li)
    {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
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
            item= li.inflate(R.layout.item_pago,parent,false);
        }

        Pago pago = pagos.get(position);

        TextInputEditText etNroPago =  item.findViewById(R.id.etdNroPago);
        etNroPago.setText((""+ pago.getNum_pago()));

        TextInputEditText etImportePago = item.findViewById(R.id.etdImportePago);
        etImportePago.setText("$" +pago.getImporte());

        TextInputEditText etFechaPago =  item.findViewById(R.id.etdFechaPago);
        LocalDateTime fecha = LocalDateTime.parse(pago.getFecha());
        LocalDate fff = fecha.toLocalDate();
        etFechaPago.setText(fff.toString());

        return  item;

    }
}