package com.example.forbess.gcd_minesweeper_sf;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // overridden destroy method that should be used to free up memory as this
    // activity is being destroyed
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy() called");
    }

    // overridden resume method that should be used to restart threads and processing
    // when the activity becomes fullscreen and unobstructed. this method should be
    // quick at processing because it has the potential to be called many times in a
    // single activity
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume() called");
    }

    // overridden pause method that should be used to temporarily stop threads and
    // processing when the activity has become partially obstructed (e.g. a dialog
    // potential to be called many times in a single activity
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause() called");
    }

    // overridden stop method that is used to temporarily free up resources for other
    // activities and applications. This is called whenever the activity becomes
    // completely obscured or backgrounded.
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop() called");
    }

    // overridden restart method that is used to restart an activity. most of the time
    // you can ignore this method as the on start method will be called imediately
    // after this method. It remains for compatibility purposes
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart() called");
    }

    // overridden start method that is used to allocate resources to the activity both
    // when it is created or when it is being restarted from a stopped state. Any
    // memory freed from onStop should be reallocated here
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart() called");
    }
}
