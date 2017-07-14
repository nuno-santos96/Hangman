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
import java.util.Arrays;
import java.util.List;

public class main_game extends AppCompatActivity {
    private List<String> theWord = new ArrayList<>();
    private List<String> display = new ArrayList<>();
    private List<String> available_letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
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
        i.putExtra("available_letters", TextUtils.join("", available_letters));
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
        boolean hit = false;
        available_letters.remove(letter);

        //add to used letters
        TextView used_letters = (TextView) findViewById(R.id.used_letters);
        used_letters.append(" " + letter);

        for (int i = 0; i < theWord.size(); i++) {
            if ((theWord.get(i).toLowerCase()).equals(letter.toLowerCase())){
                display.set(i, theWord.get(i));
                hit = true;
            }
        }
        if (hit) {
            updateDisplay();
            if (TextUtils.join("", display).equals(TextUtils.join("", theWord))) {
                end_game(true);
            }
        } else {
            updateHang();
        }
    }

    public void guess_word(String word) {
        if (TextUtils.join("", theWord).toLowerCase().equals(word.toLowerCase())){
            TextView display = (TextView) findViewById(R.id.display_word);
            display.setText(TextUtils.join(" ", theWord));
            end_game(true);
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
        if (curr_image == 7)
            end_game(false);
    }

    public void end_game(boolean isVictory){
        View b1 = findViewById(R.id.guess_letter_button);
        View b2 = findViewById(R.id.guess_word_button);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        View b3 = findViewById(R.id.restart_button);
        b3.setVisibility(View.VISIBLE);
        if (isVictory){
            TextView victory = (TextView) findViewById(R.id.win_message);
            victory.setVisibility(View.VISIBLE);
        } else {
            TextView defeat = (TextView) findViewById(R.id.lost_message);
            defeat.setVisibility(View.VISIBLE);
        }
    }

    public void restart(View v){
        Intent intent = new Intent(this, word_screen.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, word_screen.class);
        startActivity(intent);
        finish();
    }
}
