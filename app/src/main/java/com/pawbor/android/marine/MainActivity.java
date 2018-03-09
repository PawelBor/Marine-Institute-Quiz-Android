package com.pawbor.android.marine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static EditText nameField;
    private EditText question6;
    private RadioGroup questionGroup1;
    private RadioGroup questionGroup3;
    private RadioGroup questionGroup4;
    private RadioGroup questionGroup5;
    private CheckBox question2_a1;
    private CheckBox question2_a2;
    private CheckBox question2_a3;
    private CheckBox question2_a4;
    private Button submitButton;
    public static String userName;
    private double userPoints = 0;
    public static double userScore;
    private final int amountOfMaxPoints = 6;
    private ArrayList<String> radioGroupsCorrectAnswers;
    public static int answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting Views
        questionGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
        questionGroup3 = (RadioGroup) findViewById(R.id.radio_group3);
        questionGroup4 = (RadioGroup) findViewById(R.id.radio_group4);
        questionGroup5 = (RadioGroup) findViewById(R.id.radio_group5);
        question2_a1 = (CheckBox) findViewById(R.id.question2_a1);
        question2_a2 = (CheckBox) findViewById(R.id.question2_a2);
        question2_a3 = (CheckBox) findViewById(R.id.question2_a3);
        question2_a4 = (CheckBox) findViewById(R.id.question2_a4);
        nameField = (EditText) findViewById(R.id.name);
        question6 = (EditText) findViewById(R.id.question6_answer);
        submitButton = (Button) findViewById(R.id.submit_button);
        //setting correct answers for RadioGroups
        radioGroupsCorrectAnswers = new ArrayList<String>();
        radioGroupsCorrectAnswers.add(getString(R.string.question1_a1));//They feed themselves this way
        radioGroupsCorrectAnswers.add(getString(R.string.question3_a3));//Digestive Gland
        radioGroupsCorrectAnswers.add(getString(R.string.question4_a2));//Clear Zone
        radioGroupsCorrectAnswers.add(getString(R.string.question5_a4));//GA


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        resetLoad();
    }

    private int checkAnswersForRadioGroup(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (radioGroupsCorrectAnswers.contains(((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString()))
                userPoints++;
        } else
            return -1;
        return 0;
    }

    private int checkAnswersForQuestion2() { // Stomach, Large  + Small Intestine
        if (question2_a1.isChecked() && question2_a2.isChecked() && question2_a3.isChecked() && !question2_a4.isChecked())
            userPoints++;
        else if (!question2_a1.isChecked() && !question2_a2.isChecked() && !question2_a3.isChecked() && !question2_a4.isChecked())
            return -1;
        return 0;
    }

    private int checkAnswerForQuestion6() {
        String answer = question6.getText().toString();
        if (answer.equals(""))
            return -1;
        else {
            if (answer.trim().toLowerCase().equals(getString(R.string.question6_ok_answer)))
                userPoints++;
        }
        return 0;
    }

    public static void getName() {
        userName = nameField.getText().toString();
    }

    private String composeAnswer() {
        userScore = Math.round(userPoints / amountOfMaxPoints * 100);
        return getString(R.string.finish_answer,userName,Double.toString(userScore));
    }

    private void invokeResultActivity(){
        Intent myIntent = new Intent(MainActivity.this, ResultsActivity.class);
        startActivity(myIntent);
    }

    public void submitAnswers(View view) {
        userPoints = 0;
        getName();
        if (userName.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_name), Toast.LENGTH_SHORT).show();
            return;
        } else {
            answers = 0;
            answers += checkAnswersForRadioGroup(questionGroup1);
            answers += checkAnswersForQuestion2();
            answers += checkAnswersForRadioGroup(questionGroup3);
            answers += checkAnswersForRadioGroup(questionGroup4);
            answers += checkAnswersForRadioGroup(questionGroup5);
            answers += checkAnswerForQuestion6();
            if (answers < 0) {
                Toast.makeText(getApplicationContext(), getString(R.string.no_all_answers), Toast.LENGTH_SHORT).show();
                return;
            }
            //Toast.makeText(getApplicationContext(), composeAnswer(), Toast.LENGTH_SHORT).show();
            composeAnswer();
            invokeResultActivity(); // Starts the new activity ResultActivity
        }
    }

    public void resetLoad(){
        RadioGroup radioGroup1 = (RadioGroup)findViewById(R.id.radio_group1);
        RadioGroup radioGroup3 = (RadioGroup)findViewById(R.id.radio_group3);
        RadioGroup radioGroup4 = (RadioGroup)findViewById(R.id.radio_group4);
        RadioGroup radioGroup5 = (RadioGroup)findViewById(R.id.radio_group5);
        radioGroup1.clearCheck();
        radioGroup3.clearCheck();
        radioGroup4.clearCheck();
        radioGroup5.clearCheck();

        CheckBox checkBox = (CheckBox) findViewById(R.id.question2_a1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.question2_a2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.question2_a3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.question2_a4);
        checkBox.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);

        EditText editText = (EditText) findViewById(R.id.question6_answer);
        EditText editTextName = (EditText) findViewById(R.id.name);
        editText.setText("");
        editTextName.setText("");
    }
}