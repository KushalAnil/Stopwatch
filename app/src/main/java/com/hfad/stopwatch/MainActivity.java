package com.hfad.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running,wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");

            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimmer();
    }
    public void onClickStart(View view){
        running = true;

    }
    public void onClickStop(View view){
        running = false;
    }
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }
    public void runTimmer(){
        final TextView Txt = (TextView)findViewById((R.id.display));
        final Handler handler = new Handler();
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             int hr, s, min;
                             hr = seconds/3600;
                             min = (seconds%3600 )/ 60;
                             s = seconds%60;

                             String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hr, min, s);
                             Txt.setText(time);
                             if (running) {
                                 seconds++;

                             }
                             handler.postDelayed(this, 1000);
                         }
                     }
        );

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running =  false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunning)running = wasRunning;

    }

}