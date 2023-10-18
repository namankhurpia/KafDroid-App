package com.namankhurpia.apachekafka.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.namankhurpia.apachekafka.R;

public class HomeActivity extends AppCompatActivity {

    Button viewproducers;
    Button viewconsumers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewconsumers=(Button)findViewById(R.id.viewconsumers);
        viewproducers  = (Button)findViewById(R.id.viewproducers);

        viewproducers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewProducers.class));
            }
        });

        viewconsumers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ViewConsumers.class));
            }
        });




    }
}