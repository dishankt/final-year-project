package com.example.swapnali.medic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categorize extends AppCompatActivity {

    Button liveStreaming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorize);
        liveStreaming = findViewById(R.id.liveStreaming);
        liveStreaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Categorize.this,StreamData.class));
            }
        });
    }
}
