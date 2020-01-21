package com.pes.verduras_android;

import android.util.Log;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {

    final static Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    private static final String TAG = "MyActivity";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.i(TAG, "PRUEBA: Sending request "+request.url()+" "+chain.connection()+" "+request.headers());

        /*logger.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));*/

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logger.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}