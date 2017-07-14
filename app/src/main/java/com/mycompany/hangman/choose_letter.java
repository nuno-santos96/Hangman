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

import java.util.Arrays;

public class choose_letter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_letter);

        String[] items = {};
        Bundle letters = getIntent().getExtras();
        if (letters != null) {
            String available_letters = letters.getString("available_letters");
            items = available_letters.split("");
            items = Arrays.copyOfRange(items, 1, items.length);
        }
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
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

    public void onBackPressed() {
        finish();
    }
}
