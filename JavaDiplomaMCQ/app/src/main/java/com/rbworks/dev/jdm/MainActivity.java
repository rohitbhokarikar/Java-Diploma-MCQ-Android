package com.rbworks.dev.jdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView quiz ,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz = findViewById(R.id.mainquiz);
        about =  findViewById(R.id.mainabout);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {

                    Intent i = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(i);
                }
            }

        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {

                    Intent i = new Intent(MainActivity.this, MainAbout.class);
                    startActivity(i);
                }
            }

        });



    }


}
