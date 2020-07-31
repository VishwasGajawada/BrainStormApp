package com.example.brainstorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctLocation;
    int score=0;
    int total=0;
    TextView resultText;
    TextView pointsText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgain;
    ConstraintLayout layout;

    public void playAgain(View view){
        score=0;
        total=0;

        timerTextView.setText("30s");
        pointsText.setText("0/0");
        resultText.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");

                resultText.setText("Your Score :" + Integer.toString(score)+"/"+Integer.toString(total));

                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);

            }
        }.start();

    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));

        correctLocation = rand.nextInt(4);

        answers.clear();

        int incorrect;

        for(int i =0;i<4;i++){
            if(i == correctLocation){
                answers.add(a+b);
            }
            else{
                incorrect = rand.nextInt(41);
                while(incorrect == a+b){
                    incorrect = rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(correctLocation))){
            score++;

            resultText.setText("Correct");

        }
        else{
            resultText.setText("Wrong!!!!");
        }
        total++;
        pointsText.setText(Integer.toString(score)+"/"+Integer.toString(total));
        generateQuestion();

    }


    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        layout.setVisibility(ConstraintLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainbutton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = findViewById(R.id.startButton);
        sumTextView  = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultText = findViewById(R.id.resultTextView);
        pointsText = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgainbutton);
        layout = findViewById(R.id.constraintLayout);




    }
}