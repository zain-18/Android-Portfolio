package com.locantoapps.stupidquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {
    TextView tv, tvtotal;
    Button btn1, btn2, btn3, btn4;
    private level5 level5 = new level5();
    private String answer;
    //private int questionLength = question.questions.length;
    public int sequence = 0;
    public int totalCorrect = 0;
    public int totalQuestions = 9;
    public int totalAttempt = 0;
    public int wrong = 0;
    public int currentSatge = 1;
    String flag="ok5";
    public String unlockflag="unlock6";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        sequence = 0;
        btn1 = (Button) findViewById(R.id.option1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.option2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.option3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.option4);
        btn4.setOnClickListener(this);
        tv = findViewById(R.id.question);
        tvtotal = findViewById(R.id.total);
        NextQuestion(sequence);
        tvtotal.setText(sequence + "/" + totalQuestions);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option1:
                if (btn1.getText() == answer) {
                    totalCorrect++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);

                }
                else {
                    wrong++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);

                }

                break;

            case R.id.option2:
                if (btn2.getText() == answer) {
                    totalCorrect++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);
                } else {
                    wrong++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);

                }

                break;
            case R.id.option3:
                if (btn4.getText() == answer) {
                    totalCorrect++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);
                } else {
                    wrong++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);

                }

                break;



            case R.id.option4:
                if (btn4.getText() == answer) {
                    totalCorrect++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);
                } else {
                    wrong++;
                    conditions();
                    tvtotal.setText(sequence + "/" + totalQuestions);

                }

                break;
        }
    }

    private void NextQuestion(int num) {
        tv.setText(level5.getQuestion(num));
        btn1.setText(level5.getchoice1(num));
        btn2.setText(level5.getchoice2(num));
        btn3.setText(level5.getchoice3(num));
        btn4.setText(level5.getchoice4(num));
        answer = level5.getCorrectAnswer(num);
    }
    public void conditions(){
        if(sequence==totalQuestions){
            if(totalCorrect>=5){
                Intent intent=new Intent(Main5Activity.this,MainActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
            else{
                Intent intent=new Intent(Main5Activity.this,TryAgain.class);
                intent.putExtra("unlock",unlockflag);
                intent.putExtra("Wrong",wrong);
                startActivity(intent);
            }
        }
        else{
            sequence++;
            tvtotal.setText(sequence + "/" + totalQuestions);
            NextQuestion(sequence);
        }
    }
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }
}
