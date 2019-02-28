package com.borcofix.mobiltalepyonetim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        Button btnCreate =  findViewById(R.id.btnCreate);
        Button btnList =  findViewById(R.id.btnList);
        Button btnState =  findViewById(R.id.btnState);



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Homepage.this,Create.class);
                startActivity(i);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Homepage.this,List1.class);
                startActivity(i);
            }
        });
        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Homepage.this,State.class);
                startActivity(i);
            }
        });
    }

}
