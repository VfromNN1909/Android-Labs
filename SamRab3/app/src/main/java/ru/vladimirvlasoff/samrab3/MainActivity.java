package ru.vladimirvlasoff.samrab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private FloatingActionButton buttonGoToWebSite;
    private FloatingActionButton buttonGetImage;

    private JSONObject jsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        buttonGoToWebSite = findViewById(R.id.buttonGoToWebsite);
        buttonGetImage = findViewById(R.id.buttonGetRandomImage);

        jsonObject = NetworkUtils.getJSON();
        // проверка JSON объекта
        if (jsonObject == null) {
            Toast.makeText(this, "Oooops...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Oh, yes!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setImage(JSONObject object) {
        // ставим изображение в imageview при помощи glide
        Glide.with(this).load(getUrlFromJSON(object)).into(imageView);
    }
    // получаем сслыку из JSON объекта
    private String getUrlFromJSON(JSONObject object) {
        String url = null;
        try {
            JSONObject urlsObject = object.getJSONObject("urls");
            url = urlsObject.getString("small");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }

    public void onClickSetImage(View view) {
        setImage(jsonObject);
    }

    public void onClickGoToWebsite(View view) {
        Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
        startActivity(intent);
    }
}