package com.mycompany.hangman;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class main_game extends AppCompatActivity {
    private List<String> theWord = new ArrayList<>();
    private List<String> display = new ArrayList<>();
    private List<String> used_letters = new ArrayList<>();
    private int curr_image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Bundle guessWord = getIntent().getExtras();
        if (guessWord != null) {
            String word = guessWord.getString("guessWord");
            for (int i=0;i<word.length();i++)
                theWord.add(Character.toString(word.charAt(i)));
        }
        for(String s : theWord) {
            if (s.equals(" "))
                display.add(" ");
            else
                display.add("_");
        }
        updateDisplay();
    }

    public void guess_letter_button(View v){
        Intent i = new Intent(this, choose_letter.class);
        startActivityForResult(i, 1);
    }

    public void guess_word_button(View v){
        Intent i = new Intent(this, choose_word.class);
        startActivityForResult(i, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String letter = data.getStringExtra("letter");
                guess_letter(letter);
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                String word = data.getStringExtra("word");
                guess_word(word);
            }
        }
    }

    public void guess_letter(String letter) {
        if (theWord.contains(letter)) {
            for (int i = 0; i < theWord.size(); i++) {
                if (theWord.get(i).equals(letter))
                    display.set(i, letter);
            }
            updateDisplay();
            if (TextUtils.join(" ", display).equals(TextUtils.join(" ", theWord))){
                Intent intent = new Intent(this, word_screen.class);  //change to end_game activity
                startActivity(intent);
                finish();
            }
        } else
            updateHang();
    }

    public void guess_word(String word) {
        if (TextUtils.join(" ", theWord).equals(word)){
            Intent intent = new Intent(this, word_screen.class);  //change to end_game activity
            startActivity(intent);
            finish();
        } else {
            updateHang();
        }
    }

    public void updateDisplay(){
        TextView word = (TextView) findViewById(R.id.display_word);
        word.setText(TextUtils.join(" ", display));
    }

    public void updateHang() {
        curr_image++;
        ImageView hang = (ImageView) findViewById(R.id.hang);
        Resources res = getResources();
        int hangID = res.getIdentifier("hm" + Integer.toString(curr_image) , "drawable", getPackageName());
        hang.setImageResource(hangID);
        if (curr_image == 7){
            Intent intent = new Intent(this, word_screen.class);  //change to end_game activity
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, word_screen.class);
        startActivity(intent);
        finish();
    }
}
