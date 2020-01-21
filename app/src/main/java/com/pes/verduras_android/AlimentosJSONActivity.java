package com.pes.verduras_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlimentosJSONActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second );
        Intent intent = getIntent();
    }
    public void ejecutarAlimentos(View view) {

        Toast.makeText(this, "Server connection starting", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            InputStream stream = null;
            String str = "";
            String result = null;
            Handler handler = new Handler();
            String aJsonString;

            public void run() {

                try {
                    String query = String.format("http://192.168.1.204:9000/Application/alimentos/");
                    URL url = new URL(query);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);


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

                    JSONObject jObject = new JSONObject(result);
                    aJsonString = jObject.getString("nombre");
                    Log.i("JSON ALIMENTOS", "Poblacio " + aJsonString);

                    JSONArray jArray = jObject.getJSONArray("llistaHospitals");
                    aJsonString = jArray.toString();
                    //Log.i("JsonO",jArray.toString());
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject elem = (JSONObject) jArray.get(i);
                        Log.i("JsonO", "Hospital " + elem.getString("nom") + "-" + elem.getInt("nLlits"));
                    }

                    // Mostrar resultat en el quadre de text.
                    // Codi incorrecte
                    // EditText n = (EditText) findViewById (R.id.edit_message);
                    //n.setText(result);

                    //Codi correcte

                    Log.i("loginThreads", result);

                    handler.post(new Runnable() {
                        public void run() {
                            TextView n = (TextView) findViewById(R.id.textView3);
                            n.setText(aJsonString);

                             Intent intent = new Intent(AlimentosJSONActivity.this, MainActivity.class);
                             startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}