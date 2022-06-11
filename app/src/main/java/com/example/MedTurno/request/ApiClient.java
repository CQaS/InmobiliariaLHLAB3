package com.example.MedTurno.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.MedTurno.modelo.Doctor;
import com.example.MedTurno.modelo.Turnos;
import com.example.MedTurno.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ApiClient
{

    private static final String PATH = "http://192.168.1.103:5000/api/";

    public static String getURL()
    {
        return "http://192.168.1.103:5000/";
    }

    public  static  MyApiInterface myApiInterface;

    public static MyApiInterface getMyApiInterface()
    {

        Gson gson= new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)//le pasa la URL ªlocalhostª
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInterface = retrofit.create(MyApiInterface.class);
        return myApiInterface;
    }

    public interface MyApiInterface // si lo mismo! si si
    {
        @FormUrlEncoded
        @POST("Usuarios/login")
        Call<String> Login(@Field("Usuario") String usuario, @Field("Password") String password);
        //llamada<tipoDevuelto> nomMetodo(parametros)

        @GET("Usuarios")
        Call<Usuario> MiPerfil(@Header("Authorization") String token);

        @PUT("Usuarios")
        Call<Usuario> EditarPerfil(@Body Usuario usuario, @Header("Authorization") String token);

        @GET("Doctores")
        Call<ArrayList<Doctor>> ListaDoctor(@Header("Authorization") String token);

        @GET("Turnos")
        Call<ArrayList<Turnos>> ListaTurnos(@Header("Authorization") String token);

        @POST("Turnos")
        Call<Turnos> NuevoTurno(@Body Turnos turno, @Header("Authorization") String token);
    }

    public  static String obtenerToken(Context context)
    {
        String token;
        SharedPreferences sp = context.getSharedPreferences("datos.dat",0);
        token =  sp.getString("Token", "Token no encontrado");

        return token;

    }

}
