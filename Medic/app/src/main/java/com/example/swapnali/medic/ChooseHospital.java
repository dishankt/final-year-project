package com.example.swapnali.medic;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
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
        new GetData().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ChooseHospital.this,Categorize.class);
                startActivity(i);
                finish();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject chosen = jsonObjects.get(position);
                try {
                    new AlertDialog.Builder(ChooseHospital.this)
                            .setTitle("Details")
                            .setMessage(
                                    "ID: "+chosen.getString("id")+"\n\n"+
                                            "Name: "+chosen.getString("name")+"\n\n"+
                                            "Contact: "+chosen.getString("phone")+"\n\n"+
                                            "Email: "+chosen.getString("email")+"\n\n"+
                                            "Address: "+chosen.getString("address")+"\n\n")
                            .setNegativeButton("Close",null)
                            .show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
    private class GetData extends AsyncTask<String,Void,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter = new ArrayAdapter<>(ChooseHospital.this, android.R.layout.simple_list_item_1,hospitalNames);
            listView.setAdapter(adapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            String result = "";
            try {
                url = new URL("http://ckkadam.dx.am/show-hospital.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"iso-8859-1"));
                while((result=bufferedReader.readLine())!=null){
                    JSONObject current = new JSONObject(result);
                    jsonObjects.add(current);
                    hospitalNames.add(current.getString("name"));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"There was a problem connecting to server",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
