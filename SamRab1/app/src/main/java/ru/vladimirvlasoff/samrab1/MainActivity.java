package ru.vladimirvlasoff.samrab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSayHello(View view) {
        Toast.makeText(this, R.string.hello_android, Toast.LENGTH_SHORT).show();
    }
}