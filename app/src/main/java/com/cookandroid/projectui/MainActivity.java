package com.cookandroid.projectui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button MainLayout_LoginBtn, MainLayout_MembershipBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainLayout_LoginBtn = (Button) findViewById(R.id.MainLayout_LoginBtn);
        MainLayout_MembershipBtn = (Button) findViewById(R.id.MainLayout_MembershipBtn);

        MainLayout_LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        MainLayout_MembershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MembershipActivity.class);
                startActivity(intent);
            }
        });
    }
}