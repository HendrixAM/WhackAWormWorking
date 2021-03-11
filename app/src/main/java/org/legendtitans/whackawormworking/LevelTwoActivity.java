package org.legendtitans.whackawormworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LevelTwoActivity extends AppCompatActivity {
    TextView textViewTimer;
    TextView textViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        textViewTimer = findViewById(R.id.timerTextView);
        textViewScore = findViewById(R.id.textViewScore);
        Random randNum = new Random();
        int randomNum = randNum.nextInt(7);
        ImageView[] imageViewWorm1 = {findViewById(R.id.imageViewWorm1)};
        ImageView imageViewWorm2 = findViewById(R.id.imageViewWorm2);
        ImageView imageViewWorm3 = findViewById(R.id.imageViewWorm3);
        ImageView imageViewWorm4 = findViewById(R.id.imageViewWorm4);
        ImageView imageViewWorm5 = findViewById(R.id.imageViewWorm5);
        ImageView imageViewWorm6 = findViewById(R.id.imageViewWorm6);
        ImageView imageViewWorm7 = findViewById(R.id.imageViewWorm7);
        ImageView imageViewWorm8 = findViewById(R.id.imageViewWorm8);
        ImageView[] imageViewWorms = {imageViewWorm1[0], imageViewWorm2, imageViewWorm3, imageViewWorm4, imageViewWorm5, imageViewWorm6, imageViewWorm7, imageViewWorm8};
        final int[] pointsCounter = {0};
        int[] wormsToSpawn = {1, 3, 4};
        for (ImageView i : imageViewWorms) {
            //set each image to invisible
            i.setVisibility(View.INVISIBLE);
            i.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (i.getVisibility() == View.VISIBLE) {
                        i.setVisibility(View.INVISIBLE);
                        pointsCounter[0]++;
                        // Need to add +"" as otherwise its a data type mismatch
                        textViewScore.setText(pointsCounter[0] + "");
                    }
                    return false;

                }

            });
        }

        // Main timer counts for 60 seconds at an interval of 1 second each tick.
        new CountDownTimer(30000, 1000) {
            // spawnWormTimer variable allows for checking every 3 seconds without starting a new timer
            int spawnWormTimer = 1000;
            public void onTick(long milisUntilFinished) {
                long sec = (milisUntilFinished / 1000);
                textViewTimer.setText(sec + "");
                // If statement checks to see if variable is >= 2, if so it initalizes a new timer for the worm that gets spawned
                if (spawnWormTimer >= 2) {
                    spawnWormTimer = 0;
                    // Worm Timer
                    new CountDownTimer(3000, 3000) {
                        // Random call to randomize the worm that gets spawned based on the array
                        Random randNum = new Random();
                        int[] wormsSpawned = {-10, -10, -10, -10};
                        Random randNumWormsSpawned = new Random();
                        int randomNum = randNum.nextInt(8);
                        int randomNumWormsSpawned = randNum.nextInt(3);
                        int previousNum = -10;
                        int wormsCurrentlySpanwed = 0;

                        public void onTick(long millisUntilFinished) {
                            long sec = (millisUntilFinished / 3000);
                            for (int i = 0; i < wormsToSpawn[randomNumWormsSpawned] ; i++) {
                                while (randomNum == wormsSpawned[0] || randomNum == wormsSpawned[1] || randomNum == wormsSpawned[2] || randomNum == wormsSpawned[3]) {
                                    randomNum++;
                                    if (randomNum > 7) {
                                        //Toast.makeText(LevelTwoActivity.this, "RandomNum Var Has Exceeded Limit", Toast.LENGTH_SHORT).show();
                                        randomNum = 0;
                                    }
                                }
                                wormsSpawned[i] = randomNum;

                                /*
                                Previous Worm Spawn Code
                                if (wormsCurrentlySpanwed  < 1) {
                                    wormsSpawned[i] = randomNum;
                                } else if (randomNum != previousNum) {
                                    wormsSpawned[i] = randomNum;
                                } else {
                                    while (randomNum == wormsSpawned[0] || randomNum == wormsSpawned[1] || randomNum == wormsSpawned[1]) {
                                        randomNum++;
                                        if (randomNum > 7) {
                                            Toast.makeText(LevelTwoActivity.this, "RandomNum Var Has Exceeded Limit", Toast.LENGTH_SHORT).show();
                                            randomNum = 0;
                                        }
                                    }
                                    wormsSpawned[i] = randomNum;
                                }
                                */

                                previousNum = randomNum;
                                wormsCurrentlySpanwed++;
                            }
                            // Set the worm that appears based on the array using the random call above
                            for (int i : wormsSpawned) {
                                if (i > -1 && i < 8) {
                                    imageViewWorms[i].setVisibility(View.VISIBLE);
                                }
                            }
                        }

                        // When the task is over it will print 00:00:00 there
                        public void onFinish() {
                            for (int i : wormsSpawned) {
                                if (i > -1 && i < 8) {
                                    imageViewWorms[i].setVisibility(View.INVISIBLE);
                                }
                            }

                        }
                    }.start();
                } else {
                    // This else is paired with the "if (spawnWormTimer >= 2)" conditional above. It increments the variable spawnWormTimer
                    // In turn this allows the check for every 3 seconds to work
                    spawnWormTimer++;
                }
            }

            public void onFinish() {
                for (ImageView i : imageViewWorms) {
                    //set each image to invisible
                    i.setVisibility(View.INVISIBLE);
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                textViewTimer.setText("0");
            }
        }.start();
    }
}