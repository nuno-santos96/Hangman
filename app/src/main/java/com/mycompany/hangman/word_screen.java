package com.mycompany.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class word_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);
    }
    public void play(View v){
        String guessWord = ((EditText) findViewById(R.id.guess_word)).getText().toString();
        Intent intent = new Intent(this, main_game.class);
        intent.putExtra("guessWord", guessWord);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        finish();
    }
}
