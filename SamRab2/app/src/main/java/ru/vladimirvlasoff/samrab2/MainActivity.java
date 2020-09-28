package ru.vladimirvlasoff.samrab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonGuessNumber;
    private TextView textViewLastScore;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGuessNumber = findViewById(R.id.btnGuessNumber);
        textViewLastScore = findViewById(R.id.tvLastScore);

        buttonGuessNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        textViewLastScore.setText(String.valueOf(score));
    }
}