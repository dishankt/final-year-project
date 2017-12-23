package com.example.swapnali.medic;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ShowHistory extends AppCompatActivity {
    ListView listView;
    ArrayList<JSONObject> arrayList;
    ArrayList<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        names = new ArrayList<>();
        new GetData().execute(LoginActivity.username);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject chosen = arrayList.get(position);
                try {
                    new AlertDialog.Builder(ShowHistory.this)
                            .setTitle("Details")
                            .setMessage(
                                    "ID: "+chosen.getString("id")+"\n\n"+
                                    "Name: "+chosen.getString("name")+"\n\n"+
                                    "Age: "+chosen.getString("age")+"\n\n"+
                                    "Address: "+chosen.getString("address")+"\n\n"+
                                    "Contact: "+chosen.getString("phone")+"\n\n"+
                                    "Blood Group: "+chosen.getString("blood_group")+"\n\n"+
                                    "Hospital: "+chosen.getString("hospital"))
                            .setNegativeButton("Close",null)
                            .show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class GetData extends AsyncTask<String,Void,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter = new ArrayAdapter<>(ShowHistory.this, android.R.layout.simple_list_item_1,names);
            listView.setAdapter(adapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            String result = "";
            try {
                url = new URL("http://ckkadam.dx.am/show-history.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("medic","UTF-8")+"="+URLEncoder.encode(strings[0],"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();bufferedWriter.close();
                outputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"iso-8859-1"));
                while((result=bufferedReader.readLine())!=null){
                    JSONObject current = new JSONObject(result);
                    arrayList.add(current);
                    names.add( current.getString("id")+ " " +current.getString("name"));
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
