package com.projekuas.testingmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    TextView map;
    TextView maps;
    TextView mapss;
    TextView logout;
    TextView RotiBakar;
    TextView upnormal;
    TextView foodpedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );
        map = findViewById ( R.id.map );
        map.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            //Membuka CardViewActivity
            public void onClick(View view) {
                openMapsActivity();
            }
        } );

        maps = findViewById ( R.id.maps);
        maps.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            //Membuka CardViewActivity
            public void onClick(View view) {
                openabout();
            }
        } );

        mapss = findViewById ( R.id.mapss);
        mapss.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            //Membuka CardViewActivity
            public void onClick(View view) {
                openletseat();
            }
        } );

        upnormal = findViewById ( R.id.upnormal );
        upnormal.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openUpnornalActivity();
            }
        } );


        RotiBakar = findViewById ( R.id.RotiBakar );
        RotiBakar.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openRotiBakarActivity();
            }
        } );

        foodpedia = findViewById ( R.id.FoodPedia );
        foodpedia.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openFoodActivity();
            }
        } );


        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(home.this, login.class);
                startActivity(intToMain);
            }
        });



    }

    private void openFoodActivity() {
        Intent intent = new Intent ( this,FoodActivity.class );
        startActivity ( intent );
    }

    private void openUpnornalActivity() {
        Intent intent = new Intent ( this,UpnornalActivity.class );
        startActivity ( intent );
    }

    private void openRotiBakarActivity() {
        Intent intent = new Intent ( this,RotiBakarActivity.class );
        startActivity ( intent );
    }

    private void openabout() {
        Intent intent = new Intent ( this,about.class );
        startActivity ( intent );
    }

    private void openMapsActivity() {
        Intent intent = new Intent ( this,MapsActivity.class );
        startActivity ( intent );
    }

    private void openletseat() {
        Intent intent = new Intent ( this,letseat.class );
        startActivity ( intent );
    }
}
