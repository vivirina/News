package com.example.news.api;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.news.InputStreamUtils;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Ирина on 07.06.2017.
 */

public class Server {
    private static final String BASE_URL = "http://testtask.sebbia.com";

    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    public static final int DEFAULT_READ_TIMEOUT = 30000;

    private static String paramsToString(Map<String, String> params) {
        try {
            StringBuilder paramsStr = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (paramsStr.length() > 0) {
                    paramsStr.append("&");
                }
                paramsStr.append(param.getKey());
                paramsStr.append("=");
                paramsStr.append(URLEncoder.encode(param.getValue(), "UTF-8"));
            }
            return paramsStr.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public interface OnRequestListener {
        void onRequestComplete(Response response);
    }

    public static void performRequestAsync(final Request request, final OnRequestListener listener) {
        new AsyncTask<Void, Void, Response>() {
            @Override
            protected Response doInBackground(Void... params) {
                return performRequest(request);
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);

                if (listener != null) {
                    listener.onRequestComplete(response);
                }
            }
        }.execute();
    }

    public static Response performRequest(Request request) {
        Log.d("Network", "Starting request " + request);

        URL url;

        try {
            Uri.Builder uriBuilder = Uri.parse(BASE_URL).buildUpon();
            uriBuilder.appendEncodedPath(request.getMethod().getPath(request.getUrlParams()));
            if (request.getHttpMethod().equalsIgnoreCase("GET")) {
                for (Map.Entry<String, String> param : request.getParams().entrySet()) {
                    uriBuilder.appendQueryParameter(param.getKey(), param.getValue());
                }
            }
            url = new URL(uriBuilder.build().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Cannot create url", e);
        }

        InputStream input = null;
        OutputStream ostream = null;
        try  {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
            connection.setReadTimeout(DEFAULT_READ_TIMEOUT);
            connection.setRequestMethod(request.getHttpMethod());

            connection.setUseCaches(false);
            connection.setDoInput(true);

            if (request.getHttpMethod().equalsIgnoreCase("POST") && request.getParams().size() > 0) {
                connection.setDoOutput(true);

                String params = paramsToString(request.getParams());
                ostream = new DataOutputStream(connection.getOutputStream());
                ostream.write(params.getBytes("UTF-8"));
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                String responseString = InputStreamUtils.toString(connection.getInputStream());
                Log.d("Network", "Request finished: " + responseString);
                return new Response(responseCode, new JSONObject(responseString));
            } else {
                String errorString = InputStreamUtils.toString(connection.getErrorStream());
                Log.e("Network", "Request failed with code: " + responseCode + " error: " + errorString);
                return new Response(responseCode);
            }
        } catch (Exception e) {
            Log.e("Network", "Request failed with exception", e);
            return new Response(0);
        } finally {
            InputStreamUtils.close(input);
            InputStreamUtils.close(ostream);

        }
    }
}
