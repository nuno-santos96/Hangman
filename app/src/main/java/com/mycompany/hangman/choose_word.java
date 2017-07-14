package com.mycompany.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class choose_word extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);
    }

    public void submit(View v){
        String guessWord = ((EditText) findViewById(R.id.guess_word)).getText().toString();
        guessWord = guessWord.trim();
        if (!guessWord.equals("")) {
            Intent intent = new Intent();
            intent.putExtra("word", guessWord);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onBackPressed() {
        finish();
    }
}
