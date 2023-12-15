package com.cookandroid.projectui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class MypageActivity extends Activity {
    Button Mypage_UpdateUserBtn, Mypage_DeleteUserBtn;
    ImageButton Mypage_ReturnBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Mypage_UpdateUserBtn = (Button) findViewById(R.id.Mypage_UpdateUserBtn);
        Mypage_DeleteUserBtn = (Button) findViewById(R.id.Mypage_DeleteUserBtn);
        Mypage_ReturnBtn = (ImageButton) findViewById(R.id.Mypage_ReturnBtn);

        Mypage_UpdateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateUserActivity.class);
                startActivity(intent);
            }
        });
        Mypage_DeleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DeleteUserActivity.class);
                startActivity(intent);
            }
        });
        Mypage_ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
