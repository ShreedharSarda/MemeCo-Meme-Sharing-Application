package com.example.memeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView2;
    Button bv;
    TextView textView3;
    EditText edit1;
    public static final String MY_NAME = "Hello bhai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView2 = findViewById(R.id.imageView2);
        textView3 = findViewById(R.id.textView3);
        bv = findViewById(R.id.bv);
        edit1 = findViewById(R.id.edit1);

        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                String name = edit1.getText().toString();
                intent.putExtra(MY_NAME,name);
                startActivity(intent);
            }
        });
    }
}