package com.cookandroid.projectui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DeleteUserActivity extends Activity {
    CheckBox Mypage_DeleteUser_Check;
    Button Mypage_DeleteUser_ReturnBtn, Mypage_DeleteUser_DeleteBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_delete);

        Mypage_DeleteUser_Check = (CheckBox) findViewById(R.id.Mypage_DeleteUser_Check);
        Mypage_DeleteUser_DeleteBtn = (Button) findViewById(R.id.Mypage_DeleteUser_DeleteBtn);
        Mypage_DeleteUser_ReturnBtn = (Button) findViewById(R.id.Mypage_DeleteUser_ReturnBtn);


        if(Mypage_DeleteUser_Check.isChecked() == true) {

        }

        Mypage_DeleteUser_DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Mypage_DeleteUser_Check.isChecked() == true) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent); //일단 수정 버튼만 눌러도 마이페이지 화면으로 이동하도록 했음
                    //이 부분은 나중에 회원정보 db 생기면 수정
                }
                else {
                    Toast.makeText(getApplicationContext(), "내용확인 버튼을 클릭하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Mypage_DeleteUser_ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }
}
