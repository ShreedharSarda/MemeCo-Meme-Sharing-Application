package com.example.memeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;



import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
   public Button button,button2;
   public ImageView imageView;
   public TextView textView,textView2;
   public String cur_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.bv);
        button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);
        textView= findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String name_me = intent.getStringExtra(MainActivity2.MY_NAME);
        textView2.setText("Hello " + name_me + "!");
     fun();
    }
    public void fun(){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.thecatapi.com/v1/images/search";

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                       Log.d("Hey!","Successfull!");
                         cur_url = null;
                        try {
                            cur_url = response.getString("url");
                            Uri uri = Uri.parse(cur_url);
                            imageView.setImageURI(uri);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Glide.with(MainActivity.this).load(cur_url).into(imageView);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Log.d("Hii!","App didn't work well!");

                    }
                });


// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public void next(View view){
        fun();
    }
    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TEXT, cur_url);
        Intent.createChooser(intent,"Share this cool meme!");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}