package com.borcofix.mobiltalepyonetim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        Button btnLSSignIn = findViewById(R.id.btnLSSignIn);
        Button btnLSSignUp = findViewById(R.id.btnLSSignUp);

        btnLSSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginScreen.this,SignIn.class);
                startActivity(i);
            }
        });

        btnLSSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i = new Intent(LoginScreen.this,SignUp.class);
                 startActivity(i);
            }
        });

        ImageView imgQuit = (ImageView) findViewById(R.id.imageButtonQuit);
        imgQuit.setClickable(true);
        imgQuit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(v.getContext(),
                        "Uygulamadan başarı ile çıkış yaptınız",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
