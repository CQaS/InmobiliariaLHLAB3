package com.example.MedTurno.ui.mapaInicio;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Usuario;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapaInicio extends Fragment
{
    private GoogleMap map;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    String msg;
    private View mapaDetalle = null;
    private View clinicaUbicacion = null;

    private OnMapReadyCallback callback = new OnMapReadyCallback()
    {

        @Override
        public void onMapReady(GoogleMap googleMap)
        {

            map = googleMap;
            Ubicaciones();

        }
    };

    private void locationStart()
    {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(gpsEnable)
        {
            Intent setInt = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(setInt);
        }
    }

    LocationListener loclistener = new LocationListener()
    {
        @Override
        public void onLocationChanged(@NonNull Location location)
        {
            /*ActualizarUbicacion(location);
            setLocation(location);*/
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }

        @Override
        public void onProviderEnabled(@NonNull String provider)
        {
            msg = "GPS Activado";
            Mensaje();
        }

        @Override
        public void onProviderDisabled(@NonNull String provider)
        {
            msg = "GPS Desactivado";
            locationStart();
            Mensaje();
        }
    };

    private void Mensaje()
    {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private static int PETICION_PERMISO_LOCALIZACION = 101;

    private void Ubicaciones()
    {
        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
            return;
        }
        else
        {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            ActualizarUbicacion(location);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1200,0, loclistener);
        }
    }

    private void ActualizarUbicacion(Location location)
    {
        if(location != null)
        {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcadores(lat, lng);
        }
    }

    private  void agregarMarcadores(double lat, double lng)
    {
        /*map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
        {
            @Override
            public View getInfoWindow(Marker marker)
            {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker)
            {
                if(clinicaUbicacion == null)
                {
                    clinicaUbicacion = getLayoutInflater().inflate(R.layout.clinica_ubicacion,null);
                }

                //añade texto e imagen ala posicion en mapa....
                TextView infoC = clinicaUbicacion.findViewById(R.id.txtMarcaClinica);
                infoC.setText(R.string.aquiEstamos);
                ImageView imageInfoC = clinicaUbicacion.findViewById(R.id.ivClinica);
                imageInfoC.setImageResource(R.drawable.grecia);

                return (clinicaUbicacion);
            }
        });*/

            /*
            .
            .
                UBICACION ACTUAL DE LA CLINICA
            .
            .
            */
        LatLng Clinica = new LatLng(-33.304311, -66.337024);

        map.addMarker(new MarkerOptions()
                .position(Clinica)
                .title("Tu clinica amiga!")
                .icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .snippet("Av. Fulanito 234"));

        //añade circunferencia...
        CircleOptions circleOptionsC = new CircleOptions()
                .center(Clinica)
                .radius(40)
                .strokeColor(Color.parseColor("#800080"))
                .strokeWidth(4)
                .fillColor(Color.argb(32, 128, 0, 128));
        map.addCircle(circleOptionsC);

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.getUiSettings().setZoomControlsEnabled(true);

        CameraPosition camPosC = new CameraPosition.Builder()
                .target(Clinica)
                .zoom(18.0f)
                .bearing(45)
                .tilt(90)
                .build();

        CameraUpdate camUpC = CameraUpdateFactory.newCameraPosition(camPosC);
        map.animateCamera(camUpC);

            /*
            .
            .
                MI UBICACION ACTUAL
            .
            .
            */
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate MiUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,16);

        //if(marcador != null)marcador.remove();
        //añade circunferencia...
        CircleOptions circleOptionsP = new CircleOptions()
                .center(coordenadas)
                .radius(40)
                .strokeColor(Color.parseColor("#00FF00"))
                .strokeWidth(4)
                .fillColor(Color.argb(32, 0, 255, 0));
        map.addCircle(circleOptionsP);

        marcador = map.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Aqui te encuentras!!"));
        map.animateCamera(MiUbicacion);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_mapa_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaInicio);


        mapFragment.getMapAsync(callback);

    }
}