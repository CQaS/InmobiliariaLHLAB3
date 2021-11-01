package com.example.InmobiliariaLHLAB3.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.InmobiliariaLHLAB3.modelo.Contrato;
import com.example.InmobiliariaLHLAB3.modelo.Inmueble;
import com.example.InmobiliariaLHLAB3.modelo.Inquilino;
import com.example.InmobiliariaLHLAB3.modelo.Pago;
import com.example.InmobiliariaLHLAB3.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ApiClient
{

    private static final String PATH = "http://192.168.1.104:5000/Api/";
    //1.1

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

    public interface MyApiInterface
    {
        @FormUrlEncoded
        @POST("Propietarios/login")
        Call<String> Login(@Field("Usuario") String usuario, @Field("Clave") String clave);
      //llamada<tipoDevuelto> nomMetodo(parametros)

        @GET("Propietarios")
        Call<Propietario> MiPerfil(@Header("Authorization") String token);

        @PUT("Propietarios")
        Call<Propietario> EditarPerfil(@Body Propietario propietario, @Header("Authorization") String token);

        @GET("Inmuebles")
        Call<ArrayList<Inmueble>>ListaInmueble(@Header("Authorization") String token);

        @GET("Inquilinos")
        Call<ArrayList<Inquilino>>ListaInquilino(@Header("Authorization") String token);

        @GET("Inmuebles/{id}")
        Call<Inmueble>VerInmueble(@Path("id") int id,@Body Inmueble inmueble,@Header("Authorization") String token);

        @PUT("Inmuebles/{id}")
        Call<Inmueble>EditarInmueble(@Path("id") int id, @Body Inmueble inmueble, @Header("Authorization") String token);

        @GET("Contratos")
        Call<ArrayList<Contrato>>ListaContrato(@Header("Authorization") String token);

        @GET("Pagos/{id}")
        Call<ArrayList<Pago>>PagosxContrato(@Path("id") int id,@Header("Authorization") String token);

        @GET("Pagos")
        Call<ArrayList<Pago>>ListaPago(@Header("Authorization") String token);
    }

    public  static String obtenerToken(Context context)
    {
        String token;
        SharedPreferences sp = context.getSharedPreferences("datos.dat",0);
        return  sp.getString("Token", "Token no encontrado");

    }

}
