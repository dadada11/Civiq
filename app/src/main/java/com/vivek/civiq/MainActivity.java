package com.vivek.civiq;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.*;

import android.util.Log;
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MainActivity extends AppCompatActivity {
    private TextView something;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "testing");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        something = (TextView) findViewById(R.id.something);
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute("h", "h", "h");
        Log.d("poppin","imma do it right");
    }
    private class AsyncTaskRunner extends AsyncTask<String,String, ArrayList<String>> {
        JSONObject json1;
        String resStr;
        URL url;
        ArrayList<String> newRes = new ArrayList<>();
        StringBuilder result;
        public ArrayList<String> doInBackground(String... params){
            try {
                ArrayList<String> newRes = new ArrayList<>();
                result = new StringBuilder();
                url = new URL("https://newsapi.org/v1/articles?source=" + "die-zeit" + "&sortBy=top&apiKey=5af1a6765ca944788b515339c1b990ea");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                resStr = result.toString();
                newRes.add(resStr);

            } catch (IOException e) {
            }
            ArrayList<String> error = new ArrayList<>();
            return error;
        }
    @Override
    protected void onPostExecute(ArrayList<String> some) {
        JSONParser parser = new JSONParser();
        try {

            JSONObject json = (JSONObject) parser.parse("{\"status\":\"ok\"}");
            json1 = (JSONObject) parser.parse(resStr);




        }
        catch (org.json.simple.parser.ParseException e) {
            Log.d("sdbfsl", "error lmao");
        }
        //getString, getInt JSONObject methods are not working because of json.simple but other stuff isn't working with json package. Not a problem for now.
        Object articles = json1.get("articles");
        JSONArray article = (JSONArray) articles;
        something.setText(article.toString());
    }
    }
}

