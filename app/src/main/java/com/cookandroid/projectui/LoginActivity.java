package com.cookandroid.projectui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;

public class LoginActivity extends Activity {
    Button LoginLayout_Layout_ReturnBtn, LoginLayout_Layout_LoginBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        LoginLayout_Layout_LoginBtn = (Button) findViewById(R.id.LoginLayout_Layout_LoginBtn);
        LoginLayout_Layout_ReturnBtn = (Button) findViewById(R.id.LoginLayout_Layout_ReturnBtn);

        LoginLayout_Layout_LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent); //일단 로그인 버튼만 눌러도 메인 화면(가구추천)으로 이동하도록 했음
                //이 부분은 나중에 회원정보 db 생기면 수정
            }
        });
        LoginLayout_Layout_ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}