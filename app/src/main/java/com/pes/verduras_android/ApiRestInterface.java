package com.pes.verduras_android;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiRestInterface {
    String BASE_URL = "http://192.168.137.1:9000/";
    OkHttpClient clientHttp = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    @GET("alimentos")
    Call<String> getListaAlimentos();


    static ApiRestInterface createAPIRest() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientHttp)
                .build();
        //hay que cambiar el language level al 8 para que funcione
        return retrofit.create(ApiRestInterface.class);
    }

}
