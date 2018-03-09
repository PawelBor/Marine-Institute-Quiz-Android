package com.pawbor.android.marine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pawel Borzym on 7/24/2017.
 */
public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Toast
       //Toast.makeText(getApplicationContext(), MainActivity.userName + MainActivity.userScore, Toast.LENGTH_SHORT).show();

        TextView textView = (TextView)findViewById(R.id.finalresult);

        textView.setText("Congratulations " + MainActivity.userName + "!" + "Your score is: " + MainActivity.userScore + "%.");
    }

    @Override
    public void onBackPressed(){

        try{
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }catch(Exception e){

        }


    }


}
