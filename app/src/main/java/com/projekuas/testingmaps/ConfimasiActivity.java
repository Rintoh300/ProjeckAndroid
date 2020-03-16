package com.projekuas.testingmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ConfimasiActivity extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_confimasi );
        new Handler (  ).postDelayed ( new Runnable ( ) {
            @Override
            public void run() {
                Intent homeIntent = new Intent ( ConfimasiActivity.this,home.class );
                startActivity ( homeIntent );
                finish ();
            }
        },SPLASH_TIME_OUT );
    }
}
