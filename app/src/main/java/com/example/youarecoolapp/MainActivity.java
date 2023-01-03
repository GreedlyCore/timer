package com.example.youarecoolapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;


public class MainActivity extends AppCompatActivity {

    DialogFragment dlg;


    TextView ratingCount;
    SharedPreferences pref;
    Button rebootBtn;
    Button showDataBtn;
    TickerView daysGone;
    TickerView hoursGone;
    TickerView minsGone;
    TickerView secondsGone;


    Handler handler = new Handler();
    boolean started = false;
    int delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

        startCounting();
        Constants.setContext(getApplicationContext());


        rebootBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Здраствуй..")
                        .setMessage("Ты точно решил обнулиться?")
                        .setCancelable(true)
                        .setNegativeButton("Не в этот раз!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Да...", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateRating();
                                rebootChronometer();
                                dialog.cancel();
                            }
                        }).show();


            }
        });

        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity();
            }
        });

    }

    public void openActivity() {
        Intent intent = new Intent(this, OpenHistoryActivity.class);
        startActivity(intent);
    }


    private void startCounting() {
        pref = getApplicationContext().getSharedPreferences("sp", 0);
        if (pref.getLong("past_time", 0) == 0) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong("past_time", System.currentTimeMillis() / 1000L);
            editor.commit();
            started = true;
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                updateChronometer();
                handler.postDelayed(this, delay);
            }
        }, delay);

    }

    private void updateChronometer() {

        long start = pref.getLong("past_time", 0);
        long now = System.currentTimeMillis() / 1000L;
        long raceDurationInMillis = now - start;
        long tmp = raceDurationInMillis % 86400;

        long days = raceDurationInMillis / 86400;
        long hours = (tmp) / 3600;
        long mins = (tmp % 3600) / 60;
        long seconds = (tmp % 3600) % 60;

        daysGone.setText(String.valueOf(days));
        hoursGone.setText(String.valueOf(hours));
        minsGone.setText(String.valueOf(mins));
        secondsGone.setText(String.valueOf(seconds));


    }

    private void updateRating() {
        SharedPreferences pref = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("rating", Integer.parseInt(ratingCount.getText().toString()) - 1);
        editor.commit();
        ratingCount.setText(Long.toString(pref.getLong("rating", 0)));
    }

    private void rebootChronometer() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("past_time", System.currentTimeMillis() / 1000L);
        editor.commit();
        Toast.makeText(Constants.getContext(), "Better luck next time!",
                Toast.LENGTH_SHORT).show();
    }

    private void setupUI() {


        ratingCount = findViewById(R.id.tv_rating_count);

        SharedPreferences pref = this.getSharedPreferences("sp", Context.MODE_PRIVATE);

        ratingCount.setText(String.valueOf(pref.getLong("rating", 0)));

        rebootBtn = findViewById(R.id.btn_reboot);
        showDataBtn = findViewById(R.id.btn_data);
        daysGone = findViewById(R.id.tv_days_gone);
        hoursGone = findViewById(R.id.tv_hours_gone);
        minsGone = findViewById(R.id.tv_mins_gone);
        secondsGone = findViewById(R.id.tv_seconds_gone);

        daysGone.setCharacterLists(TickerUtils.provideNumberList());
        hoursGone.setCharacterLists(TickerUtils.provideNumberList());
        minsGone.setCharacterLists(TickerUtils.provideNumberList());
        secondsGone.setCharacterLists(TickerUtils.provideNumberList());


    }


}