package com.example.vijay.braintrainer;

import android.media.VolumeShaper;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout layout;
    Button button1,button2,button3,button4,go,playAgain;
    TextView initial,score,numberOfQuestions,timer,question;
    int count=0;
    int answersCorrect=0;
    boolean gameIsOn=false;
    int option;
    int x=0;
    boolean fixRandom=false;
    float answer;



    public void random(){
            Random random = new Random();
            String abc[] = {"+", "-", "*", "/"};
            String operater = abc[random.nextInt(abc.length)];
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            question.setText(String.format(Locale.getDefault(), "%02d %s %02d", a, operater, b));
            answer = 0;
            switch (operater) {
                case "+":
                    answer = a + b;
                    break;
                case "-":
                    answer = a - b;
                    break;
                case "*":
                    answer = a * b;
                    break;
                case "/":
                    if (b!=0){
                     answer =  (float)a / (float) b;
                     answer=(float) Math.round(answer*100)/100;}
                     else random();

                    break;
            }
            if (!fixRandom){
           option = ((random.nextInt(4)) + 1);
             x=option;
             float min=-1000f;
             float max=1000f;
        float randomAnswer = random.nextFloat()*(max-min)+min;
            float randomAnswer1 = random.nextFloat() *(max-min)+min;
            float randomAnswer2 = random.nextFloat() *(max-min)+min;
            if (x == 1) {
                button1.setText(String.format(Locale.getDefault(), "%.2f", answer));
                button2.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer));
                button3.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer1));
                button4.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer2));

            } else if (x == 2) {
                button1.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer));
                button2.setText(String.format(Locale.getDefault(), "%.2f", answer));
                button3.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer1));
                button4.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer2));
            } else if (x == 3) {
                button1.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer));
                button2.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer1));
                button3.setText(String.format(Locale.getDefault(), "%.2f", answer));
                button4.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer2));
            } else if (x == 4) {
                button1.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer));
                button2.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer2));
                button3.setText(String.format(Locale.getDefault(), "%.2f", randomAnswer1));
                button4.setText(String.format(Locale.getDefault(), "%.2f", answer));
            }
            fixRandom=true;
            }











    }

    public void answer(View view){
        if (!gameIsOn){
            fixRandom=false;

            count++;

        Button anybutton=(Button)view;
        float check=Float.parseFloat(anybutton.getText().toString());
        float doubleCheck=answer;
            random();
        int tag=Integer.parseInt(anybutton.getTag().toString());



        if (check==doubleCheck){
            answersCorrect++;
            score.setText(String.format(Locale.getDefault(),"%s","CORRECT!"));
        }
        else {
            score.setText(String.format(Locale.getDefault(), "%s", "WRONG!"));
        }
        numberOfQuestions.setText(String.format(Locale.getDefault(),"%02d/%02d",answersCorrect,count));
        }

    }

    public void go(View view){
        layout.setVisibility(View.VISIBLE);
        initial.setVisibility(View.INVISIBLE);
        go.setVisibility(View.INVISIBLE);
        numberOfQuestions.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        new CountDownTimer(30000+100, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.format(Locale.getDefault(),"%02d",(int)l/1000));

            }

            @Override
            public void onFinish() {
                timer.setText(String.format(Locale.getDefault(),"%02d",0));
                playAgain.setVisibility(View.VISIBLE);
                score.setText(String.format(Locale.getDefault(),"Your score is : %d",answersCorrect));
                gameIsOn=true;

            }
        }.start();

        random();


    }
    public void playAgain(View view){
        gameIsOn=false;
        fixRandom=false;
        go(view);
        playAgain.setVisibility(View.INVISIBLE);
        answersCorrect=0;
        count=0;
        numberOfQuestions.setText(String.format(Locale.getDefault(),"%02d/%02d",answersCorrect,count));
        score.setText(String.format(Locale.getDefault(),"%s",""));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial=findViewById(R.id.textView2);
        score=findViewById(R.id.textView);
        numberOfQuestions=findViewById(R.id.numberOfQuestions);
        timer=findViewById(R.id.timer);
        question=findViewById(R.id.question);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        go=findViewById(R.id.go);
        playAgain=findViewById(R.id.playAgain);
        layout=findViewById(R.id.gridLayout);

    }
}
