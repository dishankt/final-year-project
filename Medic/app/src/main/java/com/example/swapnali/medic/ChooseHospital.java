package com.example.swapnali.medic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

public class ChooseHospital extends AppCompatActivity {
    ListView listView;
    ArrayList<JSONObject> jsonObjects;
    ArrayList<String> hospitalNames;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hospital);
        listView = findViewById(R.id.listView);
        jsonObjects = new ArrayList<>();
        hospitalNames = new ArrayList<>();

    }
}
