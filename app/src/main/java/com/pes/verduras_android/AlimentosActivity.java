package com.pes.verduras_android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlimentosActivity extends AppCompatActivity {
    private ApiRestInterface myapirest;
    ProgressDialog progressDialog;
    TextView nombre;
    TextView tipo;
    TextView vegeta;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alimentos_layout);
        Intent intent = getIntent();

        nombre = findViewById(R.id.textView4);
        tipo = findViewById(R.id.textView5);
        vegeta = findViewById(R.id.textView6);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Waiting for the server");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        myapirest = ApiRestInterface.createAPIRest();

        getListaAlimentos();
}

    private void getListaAlimentos() {
        Call<String> myCall = myapirest.getListaAlimentos();

        myCall.enqueue(new Callback<String>() {


            @Override
            public void onResponse(Call<String > call, Response<String> response) {
                if (response.isSuccessful()) {
                    String resultado = response.body();
                    progressDialog.hide();
                    Gson g = new Gson();
                    Alimento a = g.fromJson(resultado, Alimento.class);

                    nombre.setText(a.nombre);
                    tipo.setText(a.tipo);
                    vegeta.setText(a.vegetariano);

                } else {
                    Log.e("Response failure", String.valueOf(response.errorBody()));

                    progressDialog.hide();

                    //Show the alert dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AlimentosActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("No api connection", t.getMessage());

                progressDialog.hide();

                //Show the alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AlimentosActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
    }


