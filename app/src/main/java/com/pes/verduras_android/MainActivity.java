package com.pes.verduras_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Logger log = Logger.getLogger(MainActivity.class.getName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Server connection starting", Toast.LENGTH_LONG).show();
        new myAsyncTask(this).execute("http://192.168.137.1:9000/");
        log.info("Se inicia la conexion");
    }

    public void startAlimentos(View view) {
        Intent intent=new Intent(MainActivity.this,AlimentosActivity.class);
        startActivity(intent);
    }

    private class myAsyncTask extends AsyncTask<String, Void, String> {
        Context context;
        String result = null;
        InputStream stream = null;

        private myAsyncTask(Context context) {
            this.context = context;
        }


        protected String doInBackground(String... strings) {
            try {
                String query = String.format(strings[0]);
                log.info(strings[0]);
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                stream = conn.getInputStream();

                BufferedReader reader = null;

                StringBuilder sb = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(stream));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();

            } catch (IOException e) {
                return null;
            }

            return result;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(this.context, "Base de datos iniciada", Toast.LENGTH_SHORT).show();
            //TextView n = (TextView) findViewById(R.id.textoBienvenida);
            //n.setText(result);

        }
    }
}