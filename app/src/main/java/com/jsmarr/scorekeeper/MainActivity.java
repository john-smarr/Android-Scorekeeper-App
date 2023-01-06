package com.jsmarr.scorekeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    // Member variables for holding the score
    private int mScore1;
    private int mScore2;

    // Member variables for holding the score
    private TextView mScoreText1;
    private TextView mScoreText2;

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the TextViews by ID
        mScoreText1 = (TextView) findViewById(R.id.score_1);
        mScoreText2 = (TextView) findViewById(R.id.score_2);

        SharedPreferences sp;
        sp = getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE);



        if (sp != null) {


            //mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            //mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            mScore1 = sp.getInt("score1", 0);
            mScore2 = sp.getInt("score2", 0);
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));

            Toast.makeText(MainActivity.this, "Info Saved", Toast.LENGTH_LONG).toString();
            //mScoreText1.setText(String.valueOf(mScore1));
            //mScoreText2.setText(String.valueOf(mScore2));
        }
    }


    public void decreaseScore(View view) {
        // Get the ID of the button that was clicked
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int viewID = view.getId();
        switch (viewID) {
            //If it was on Team 1
            case R.id.decreaseTeam1:
                //Decrement the score and update the TextView
                mScore1--;

                editor.putInt("score1",mScore1);
                editor.commit();

                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //If it was Team 2
            case R.id.decreaseTeam2:
                //Decrement the score and update the TextView
                mScore2--;

                editor.putInt("score2",mScore2);
                editor.commit();

                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
            if (item.getItemId() == R.id.night_mode) {
                // Get the night mode state of the app.
                int nightMode = AppCompatDelegate.getDefaultNightMode();
                //Set the theme mode for the restarted activity
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
// Recreate the activity for the theme change to take effect.
                recreate();
            }
            // TODO: Get the night mode state of the app.
        return true;
    }

    public void increaseScore(View view) {
        //Get the ID of the button that was clicked
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int viewID = view.getId();
        switch (viewID) {
            //If it was on Team 1
            case R.id.increaseTeam1:
                //Increment the score and update the TextView
                mScore1++;

                editor.putInt("score1",mScore1);
                editor.commit();

                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //If it was Team 2
            case R.id.increaseTeam2:
                //Increment the score and update the TextView
                mScore2++;

                editor.putInt("score2",mScore2);
                editor.commit();

                mScoreText2.setText(String.valueOf(mScore2));
        }
    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        // Save the scores.
//        outState.putInt(STATE_SCORE_1, mScore1);
//        outState.putInt(STATE_SCORE_2, mScore2);
//        super.onSaveInstanceState(outState);
//    }
    public void resetScores(View view){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.clear();
        editor.commit();

        mScore1 = 0;
        mScore2 = 0;

        recreate();
    }
}