package com.example.swapnali.medic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        logout = findViewById(R.id.logout);
        textView.setText("Welcome, "+LoginActivity.username);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.sharedPreferences.edit().remove("username").remove("password").apply();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Successfully logged out", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
