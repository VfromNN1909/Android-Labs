package ru.vladimirvlasoff.samrab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    // объявляем компоненты
    private EditText editTextEnterNumber;
    private Button buttonGuessNumber;

    // переменные для логики игры
    private int takes = 0;
    private int maxTakes = 5;
    private final int trueAnswer = (int) (Math.random() * (101 - 1)) + 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // инициализируем компоненты
        editTextEnterNumber = findViewById(R.id.etEnterNumber);
        buttonGuessNumber = findViewById(R.id.btnGuessNumber);

    }

    // функция игры
    private void playGame() {
        Log.i("trueAnswer", trueAnswer + "");
        try {
            // получаем ответ
            int answer = Integer.parseInt(editTextEnterNumber.getText().toString());
            // если он не входит в диапозон
            if (answer < 1 || answer > 100) {
                // выводим сообщение
                Toast.makeText(this, "Введите число от 1 до 100!", Toast.LENGTH_SHORT).show();
                // чтоб игра не завершилась преждевременно
                takes--;
            }
            // если число не правильное
            if (answer != trueAnswer) {
                // увеличиваем количество попыток
                takes++;
                Log.i("take", takes + "");
                // даем подсказку
                if (answer > trueAnswer)
                    Toast.makeText(this, "Много", Toast.LENGTH_SHORT).show();
                if (answer < trueAnswer)
                    Toast.makeText(this, "Мало", Toast.LENGTH_SHORT).show();

            }
            // если попытки кончились
            if (takes == maxTakes) {
                // выводим сообщение
                Toast.makeText(this, R.string.toast_you_lose, Toast.LENGTH_SHORT).show();
                // переходим на другую активность
                startActivity(new Intent(GameActivity.this, MainActivity.class));
                // занляем попытки
                takes = 0;
            }
            // если ответ угадан
            if (takes != maxTakes && answer == trueAnswer) {
                // вводим сообщение
                Toast.makeText(this, R.string.toast_you_win, Toast.LENGTH_SHORT).show();
                // переходим на начальную активность и выводим количество попыток(типо "счет")
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                intent.putExtra("score", takes);
                takes = 0;
                startActivity(intent);
            }
        // ловим ошибку
        } catch (Exception e) {
            // выводим сообщение
            Toast.makeText(this, "Ошибка! Введите число!", Toast.LENGTH_SHORT).show();
            // чтоб игра не завершилась преждевременно
            if (takes > 0) takes--;
        }
    }

    public void onClickGuessNumber(View view) {
        playGame();
    }
}