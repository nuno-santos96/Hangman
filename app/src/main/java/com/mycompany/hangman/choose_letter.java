package com.mycompany.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class choose_letter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_letter);
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" +
                "L", "M", "N", "O", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void submit(View v){
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String letter = spinner.getSelectedItem().toString();
        Intent intent = new Intent();
        intent.putExtra("letter", letter);
        setResult(RESULT_OK, intent);
        finish();
    }
}
