package com.mycompany.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void random_word(View v){
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("words.txt")));
            String word;
            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(words.size());
            Intent intent = new Intent(this, main_game.class);
            intent.putExtra("guessWord", words.get(randomInt));
            startActivity(intent);
            finish();
        } catch (IOException ex) {

        }
    }
}
