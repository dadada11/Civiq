package com.vivek.civiq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStreamReader;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private TextView something;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        something = (TextView) findViewById(R.id.something);
        callClick();
    }
    public String callClick(){

        Log.d("tagName", "Hello World");
        System.out.println("Helo");

        try {
//            URL url = new URL("https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=889e98d86df24c9bab44803653a2fbb2");
//            URLConnection conn = url.openConnection();
//            InputStream is = conn.getInputStream();
//            something.setText("hello");

            URL url = new URL("http://api.icndb.com/jokes/random");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer json = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            something.setText(line);
            return line;

        } catch (IOException e) {
            something.setText("one thicc bih");
            throw new RuntimeException(e);

        }

    }
}

