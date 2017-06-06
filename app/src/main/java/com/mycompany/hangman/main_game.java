package com.mycompany.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class main_game extends AppCompatActivity {
    private String theWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Bundle guessWord = getIntent().getExtras();
        if (guessWord != null) {
            theWord = guessWord.getString("guessWord");
        }

        String initial = "";
        for(int i=0;i<theWord.length();i++)
            initial += "_ ";

        LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);
        TextView tv = new TextView(this);
        tv.setText(initial);
        tv.setTextSize(16);
        layout.addView(tv);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        tv.setLayoutParams(lp);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, word_screen.class);
        startActivity(intent);
        finish();
    }
}
