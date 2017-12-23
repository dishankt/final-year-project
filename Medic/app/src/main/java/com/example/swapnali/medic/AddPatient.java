package com.example.swapnali.medic;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import static com.example.swapnali.medic.LoginActivity.password;
import static com.example.swapnali.medic.LoginActivity.username;

public class AddPatient extends AppCompatActivity {
    Spinner blood;
    Button submit;
    EditText name,address,age,phone;
    String nameString, addressString, ageString, phoneString, bloodString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        blood = findViewById(R.id.blood);
        String[] groups = {"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,groups);
        blood.setAdapter(adapter);

        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();
                addressString = address.getText().toString();
                ageString = age.getText().toString();
                phoneString = phone.getText().toString();
                bloodString = blood.getSelectedItem().toString();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("medic", username);
                    jsonObject.put("name",nameString);
                    jsonObject.put("age",ageString);
                    jsonObject.put("address",addressString);
                    jsonObject.put("phone",phoneString);
                    jsonObject.put("blood_group",bloodString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("JSON", jsonObject.toString());
                new SendData().execute(jsonObject.toString());
                finish();
            }
        });
    }

    private class SendData extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            String result = "";
            try {
                url = new URL("http://ckkadam.dx.am/add-patient.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("data","UTF-8")+"="+URLEncoder.encode(strings[0],"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();bufferedWriter.close();
                outputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"iso-8859-1"));
                result += bufferedReader.readLine();
                Log.i("Result",result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"There was a problem connecting to server",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }
    }
}
