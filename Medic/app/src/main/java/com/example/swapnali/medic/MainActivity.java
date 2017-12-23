package com.example.swapnali.medic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button add,show;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LoginActivity.sharedPreferences.edit().remove("username").remove("password").apply();
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"Successfully logged out", Toast.LENGTH_SHORT).show();
        finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText( getApplicationContext(),"Welcome "+ LoginActivity.username , Toast.LENGTH_SHORT).show();
        add = findViewById(R.id.add);
        show = findViewById(R.id.show);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddPatient.class);
                startActivity(i);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ShowHistory.class);
                startActivity(i);
            }
        });
    }
}
