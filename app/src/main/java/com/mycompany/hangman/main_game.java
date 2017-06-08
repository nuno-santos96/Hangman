package com.mycompany.hangman;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class main_game extends AppCompatActivity {
    private List<String> theWord = new ArrayList<>();
    private List<String> display = new ArrayList<>();
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

    public void guess_letter(View v){
        String letter = ((EditText) findViewById(R.id.letter)).getText().toString();
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

    public void updateDisplay(){
        TextView word = (TextView) findViewById(R.id.guess_word);
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
