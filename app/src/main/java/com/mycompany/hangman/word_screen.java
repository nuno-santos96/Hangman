package com.mycompany.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class word_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);
    }
    public void play(View v){
        String guessWord = ((EditText) findViewById(R.id.display_word)).getText().toString();
        guessWord = guessWord.trim();
        if (!guessWord.equals("")) {
            Intent intent = new Intent(this, main_game.class);
            intent.putExtra("guessWord", guessWord);
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed() {
        finish();
    }
}
