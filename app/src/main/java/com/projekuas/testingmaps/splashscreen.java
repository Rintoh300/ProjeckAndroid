package com.projekuas.testingmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class splashscreen extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        setContentView ( R.layout.activity_splashscreen );
        new Handler (  ).postDelayed ( new Runnable ( ) {
            @Override
            public void run() {
                Intent loginIntent = new Intent ( splashscreen.this,login.class );
                startActivity ( loginIntent );
                finish ();
            }
        },SPLASH_TIME_OUT );

    }
}
