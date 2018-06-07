package com.example.adaml.androidecommerce;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Typeface;
import android.content.Intent;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {


    FButton btnSignIn, btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SignIn and SignUp Buttons
        btnSignIn = (FButton)findViewById(R.id.btnSignIn);
        btnSignUp = (FButton)findViewById(R.id.btnSignUp);

        //Slogan Text
        txtSlogan = (TextView)findViewById(R.id.txtSlogan);
        //Loading font face
//        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TFF");
//        //setting the font face
//        txtSlogan.setTypeface(tf);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });
    }
}
