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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlimentosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApiRestInterface myapirest;
    private RecyclerAdapter recyclerAdapter;
    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alimentos_layout);
        //Intent intent = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerV);
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        Call<List<Alimento>> myCall = myapirest.getListaAlimentos();

        myCall.enqueue(new Callback<List<Alimento>>() {
            @Override
            public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {
                if (response.isSuccessful()) {
                    List<Alimento> lista = response.body();
                    //Alimento alimento = ;
                    //lista.add(alimento);

                    //Log.i("nombre: " + alimento.getNombre(), response.message());
                    if (lista.size() != 0) {
                        recyclerAdapter.addAlimentos(lista);
                    }

                    progressDialog.hide();

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
            public void onFailure(Call<List<Alimento>> call, Throwable t) {
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


