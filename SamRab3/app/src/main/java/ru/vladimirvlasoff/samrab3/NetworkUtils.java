package ru.vladimirvlasoff.samrab3;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    // базовая ссылка
    public static final String BASE_URL = "https://api.unsplash.com/photos/random";

    // параметр API-ключ
    private static final String PARAM_CLIENT_ID = "client_id";

    // сам ключ
    private static final String API_KEY = "KB6tcxUknBcuXvtAJ4Xv2QiBEmRfJ8VBiOX87nY2Ek4";

    // строим ссылку
    public static URL buildURL() {
        URL url = null;
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_CLIENT_ID, API_KEY)
                .build();
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    // получаем JSON
    public static JSONObject getJSON() {
        JSONObject object = null;
        URL url = buildURL();
        try {
            object = new LoadJSONTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return object;
    }

    // делаем запрос
    // сначала хотел использовать Retrofit2
    // но потом подумал, что через AsyncTask проще будет
    private static class LoadJSONTask extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject object = null;
            if (urls == null || urls.length == 0) {
                return object;
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) urls[0].openConnection();
                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while(line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                object = new JSONObject(builder.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return object;
        }
    }
}
