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
import org.apache.commons.codec.binary.Base64;
import javax.xml.bind.DatatypeConverter;


public class MainActivity extends AppCompatActivity {
    String resStr;
    String resStr1;
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
        JSONObject json;
        URL url;
        ArrayList<String> newRes = new ArrayList<>();
        StringBuilder result;

        JSONObject json1;
        URL url1;
        ArrayList<String> newRes1 = new ArrayList<>();
        StringBuilder result1;
        public ArrayList<String> doInBackground(String... params){
            try {
                ArrayList<String> newRes = new ArrayList<>();

                result = new StringBuilder();
                //url = new URL("https://newsapi.org/v1/articles?source=" + "google-news" + "&sortBy=top&apiKey=5af1a6765ca944788b515339c1b990ea");
                url = new URL("https://api.legiscan.com/?key=aa0bc816278e61763d37fd9ad5afc0b1&op=getBillText&id=1637560");
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


               /* ArrayList<String> newRes1 = new ArrayList<>();
                result1 = new StringBuilder();
                url1 = new URL("https://api.legiscan.com/?key=aa0bc816278e61763d37fd9ad5afc0b1&op=getMasterList&state=WA");
                HttpURLConnection conn1 = (HttpURLConnection) url.openConnection();
                conn1.setRequestMethod("GET");
                BufferedReader rd1 = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
                String line1;

                while ((line1 = rd1.readLine()) != null) {
                    result1.append(line1);
                }
                rd1.close();
                resStr1 = result1.toString();
                System.out.println("result: " + result.toString());

                newRes.add(resStr1);
*/

            } catch (IOException e) {
                System.out.println("there is an error");
            }
            ArrayList<String> error = new ArrayList<>();
            return error;
        }
    @Override
    protected void onPostExecute(ArrayList<String> some) {
        JSONParser parser = new JSONParser();
        JSONParser parser1 = new JSONParser();
        try {

//            JSONObject json = (JSONObject) parser.parse("{\"status\":\"ok\"}");

            json = (JSONObject) parser.parse(resStr);
           // json1 = (JSONObject) parser1.parse(resStr1);

        }
        catch (org.json.simple.parser.ParseException e) {
            Log.d("sdbfs", resStr);
        }
        //getString, getInt JSONObject methods are not working because of json.simple but other stuff isn't working with json package. Not a problem for now.
        Object articles = json.get("articles");
        JSONArray article = (JSONArray) articles;
        Object doc = json.get("text");
        JSONObject docs = (JSONObject) doc;
        Object bill = docs.get("doc");
        String bills = (String) bill;



        try {
            // encode data on your side using BASE64
            byte[]   bytesEncoded = Base64.encodeBase64("hello".getBytes());
            Log.d("printedoutdude", "ecncoded value is " + new String(bytesEncoded ));

// Decode data on other side, by processing encoded data
            byte[] valueDecoded= Base64.decodeBase64(bytesEncoded );
            Log.d("decoded value", "Decoded value is " + new String(valueDecoded));
            //Log.d("sdbfsl", new String(decoded, "UTF-8") + "\n");
        }

        catch (java.lang.NoSuchMethodError i) {
            Log.d("sdbfsl", "error lmao");
        }
        something.setText(bills);
    }
    }
}

