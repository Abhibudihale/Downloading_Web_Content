package com.example.downloading_web_content;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class  DownloadTask extends  AsyncTask<String , Void ,String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;

            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){

                    char current  = (char) data;

                    result += current;

                    data=reader.read(); //read move to next item
                }

                return  result;


            } catch (Exception e) {
                e.printStackTrace();
                return "Failed to Read Data ";
            }


        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result="";

        try{
            result=task.execute("https://zappycode.com").get();
            Log.i("Done1:",result);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}