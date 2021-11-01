package com.example.InmobiliariaLHLAB3;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.InmobiliariaLHLAB3.modelo.Propietario;
import com.example.InmobiliariaLHLAB3.ui.perfil.PerfilViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
    private MainActivityViewModel mavm;
    private ImageView imageAvatar;
    private TextView nombre, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mavm.getPropietarioMutable().observe(this, new Observer<Propietario>()
        {
            @Override
            public void onChanged(Propietario propietario)
            {
                final ImageView imageAvatar = (ImageView) header.findViewById(R.id.imageAvatar);
                nombre = header.findViewById(R.id.txtNombre);
                mail = header.findViewById(R.id.txtMail);


                //setea el avatar....
                Glide.with(MainActivity.this)
                        .load("http://192.168.1.104:5000" + propietario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageAvatar);

                nombre.setText(propietario.getNombre());
                mail.setText(propietario.getEmail());

            }
        });

        mavm.getError().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String er)
            {
                Toast.makeText(MainActivity.this, er, Toast.LENGTH_LONG).show();
            }
        });


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                                    R.id.nav_perfil,
                                    R.id.nav_inmuebles,
                                    R.id.nav_inquilinos,
                                    R.id.nav_pagos,
                                    R.id.nav_contratos)
                        .setDrawerLayout(drawer)
                        .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Infla el menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}