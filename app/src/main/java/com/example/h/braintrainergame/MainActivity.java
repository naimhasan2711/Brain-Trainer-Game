package com.example.h.braintrainergame;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    Button startButton , button0 , button1 , button2 , button3 , playAgainButton;
    TextView sumTextView , resultTextView , pointsTextView , timerTextView;


    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestion = 0;

    public void playAgain(View view){

        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000)+"s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0");
                resultTextView.setText("Your score : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();



    }

    public void generateQuestion(){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        answers.clear();

        for(int i=0;i<4;i++){

            if(i== locationOfCorrectAnswer){

                answers.add(a+b);

            }else{

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a+b){

                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);

    }

    public void choseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            resultTextView.setText("Correct Answer!");

        }else{

            resultTextView.setText("Wrong Answer!!");

        }

        numberOfQuestion++;

        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);




        playAgain(findViewById(R.id.playAgainButton));


    }
}
