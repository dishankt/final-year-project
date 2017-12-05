package com.example.swapnali.medic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText,passwordEditText;
    Button loginButton;
    static SharedPreferences sharedPreferences;
    static String username;
    static String password;
    JSONObject result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("com.example.swapnali.medic",MODE_PRIVATE);
        username = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");
        Log.i("user",username);
        Log.i("password",password);
        if(!username.isEmpty() && !password.isEmpty()){
            Log.i("IF","Okay");
            String[] credentials = new String[2];
            credentials[0] = username;
            credentials[1] = password;
            try {
                result = new DoLogin().execute(credentials).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            try {
                checkSuccess(result.getBoolean("success"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please fill out both fields",Toast.LENGTH_SHORT).show();
                }else{
                    String[] credentials = new String[2];
                    credentials[0] = username;
                    credentials[1] = password;
                    try {
                        result = new DoLogin().execute(credentials).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        checkSuccess(result.getBoolean("success"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    public void checkSuccess(boolean success){
        if(success){
            Toast.makeText(getApplicationContext(),"Login was successfull",Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("username",username).apply();
            sharedPreferences.edit().putString("password",password).apply();
            Intent i = new Intent(LoginActivity.this,MainActivity.class );
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
        }
    }

    private class DoLogin extends AsyncTask<String,Void,JSONObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            String link = "http://ckkadam.tk/login.php";
            String username = strings[0];
            String password = strings[1];
            try {
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"iso-8859-1"));
                String result = bufferedReader.readLine();
                Log.i("JSON",result);
                JSONObject jsonResult = new JSONObject(result);
                return jsonResult;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject ajsonObject) {
            super.onPostExecute(ajsonObject);
        }
    }
}
