package org.legendtitans.whackawormworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public int currentLevel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageViewPlay = (ImageView) findViewById(R.id.imageButtonPlay);
        //Button debugLevelTwo = findViewById(R.id.debugLevelTwo);
        imageViewPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), LevelOneActivity.class);
                startActivity(intent);
                return true;
            }
        });
        /*debugLevelTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), LevelTwoActivity.class);
                startActivity(intent);
                return true;
            }
        });
         */
    }
}