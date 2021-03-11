package org.legendtitans.whackawormworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class FailedLevelOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_level_one);
        Button levelChangeButton = findViewById(R.id.buttonChangeLevel);
        levelChangeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), LevelOneActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
}