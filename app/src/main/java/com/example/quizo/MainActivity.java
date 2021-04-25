package com.example.quizo;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    private int score = 0;
    private int[] radioGroups = {R.id.first_q_correct_a,R.id.second_q_correct_a,R.id.third_q_correct_a,R.id.fourth_q_correct_a};
    private int[] correctCheckBoxes = {R.id.sixth_q_correct_a1,R.id.sixth_q_correct_a2,R.id.sixth_q_correct_a3};
    private int[] wrongCheckBoxes = {R.id.sixth_q_wrong_a1,R.id.sixth_q_wrong_a2,R.id.sixth_q_wrong_a3};

    public void calculateScore(View view) {
        int score = 0;
        for (int i = 0; i < radioGroups.length; i++)
        {
            RadioButton radioButton = (RadioButton) findViewById(radioGroups[i]);
            if (radioButton.isChecked())
            {
                score+=1;
            }
        }
        score = score+checkTextEntry();
        score = score+checkCheckBoxes();
        showToastMsg(score);
    }

    // A function to check the answer of the text entry question, returns 1 if the answer is correct and 0 if not
    public int checkTextEntry(){
        EditText answerEntry = findViewById(R.id.text_entry_answer);
        String answer = answerEntry.getText().toString();
        System.out.print(answer);
        int score = 0;
        if (answer.equals("13")){score=1;}
        else {score = 0;}
        System.out.print(score);
        return score;

    }

    // a function to check the check boxes question is answered correctly
    // returns a 0 if any of the correct answers isn't checked or if any wrong answer is checked
    // otherwise returns 1
    public int checkCheckBoxes(){
        for (int i = 0; i < correctCheckBoxes.length; i++)
        {
            CheckBox  checkbox = (CheckBox) findViewById(correctCheckBoxes[i]);
            if (!checkbox.isChecked())
            {
                return 0;
            }
        }
        for (int i = 0; i < wrongCheckBoxes.length; i++)
        {
            CheckBox  checkbox = (CheckBox) findViewById(wrongCheckBoxes[i]);
            if (checkbox.isChecked())
            {
                return 0;
            }
        }

    return 1;
    }


    public void showToastMsg(int score){
        Context context = getApplicationContext();
        CharSequence text = "";
        if (score==0){
            text = "It seems you never watched an anime before, "+String.valueOf(score)+ " correct";
        }
        else if (score==radioGroups.length+2){
            text = "Hooray you got them all correct otaku boy!";
        }
        else
        {
            text = "Your answered "+String.valueOf(score)+ " correct out of 7";
        }
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}