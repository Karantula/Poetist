package com.slaptrap.poetist;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    String JSON_STRING;

    EditText keywordA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getJSON(View view) {

        new BackgroundTask().execute();
        Log.i("query", keywordA.getText().toString());
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            keywordA = (EditText) findViewById(R.id.keywordA);
            json_url = "http://poetrydb.org/lines/" + keywordA.getText().toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView)findViewById(R.id.textview);
            textView.setText(result);
            JSON_STRING = result;
        }
    }

    public void parseJSON(View view) {

        if(JSON_STRING == null) {
            Toast.makeText(getApplicationContext(),"First Get JSON", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, DisplayListView.class);
            intent.putExtra("json_data", JSON_STRING);
            startActivity(intent);
        }
    }
}







        //task.execute("http://www.stands4.com/services/v2/poetry.php?uid=5581&tokenid=8jiCpWhIq5sXZd8F&term=" + keywordA.getText().toString());


