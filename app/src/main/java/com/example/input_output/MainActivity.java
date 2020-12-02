package com.example.input_output;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView title;
    TextView answerText;
    String[] answers;
    int appearDelay;
    int disappearDelay;
    int totalDelay;
    boolean isOffCooldown;
    EditText checkText;
    String savedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title_TV);
        TextPaint paint = title.getPaint();
        float width = paint.measureText("Magic 8-Ball");

        Shader textShader = new LinearGradient(0, 0, width, title.getTextSize(), new int[]
        {
                Color.parseColor("#FF00FF"),
                Color.parseColor("#000000")
        }, null, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);

        checkText = findViewById(R.id.ask_ET);

        answerText = findViewById(R.id.answer_TV);
        answerText.setAlpha(0f);

        appearDelay = 500;
        disappearDelay = 2500;
        totalDelay = 3500;

        isOffCooldown = true;

        answers = new String[] {
                "Don't count on it.", //No
                "Ask again later.", //Neutral
                "It is certain.", //Yes
                "It is decidedly so.", //Yes
                "Most likely.", //Yes
                "Outlook not so good.", //No
                "Signs point to yes.", //Yes
                "Definitely.", //Yes
                "You may rely on it.", //Yes
                "My sources say no.", //No
                "Doubtful.", //No
                "My reply is no.", //No
                "Not likely.",
                "Reply hazy, try again." //Neutral
        };
    }

    public void pickAnswer(View v) {
        savedText = checkText.getText().toString();
        if (isOffCooldown && savedText.length() > 0) {
            isOffCooldown = false;

            answerText.setAlpha(0f);
            int answerNumber = (int) (Math.random() * 14 + 1);
            answerText.setText(answers[answerNumber]);


            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                answerText.animate().alpha(1f).setDuration(1000);
            }, appearDelay);
            handler.postDelayed(() -> {
                answerText.animate().alpha(0f).setDuration(1000);
            }, disappearDelay);
            handler.postDelayed(() -> {
                isOffCooldown = true;
            }, totalDelay);
        }
    }
}