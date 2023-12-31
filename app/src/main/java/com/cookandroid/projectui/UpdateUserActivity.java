package com.cookandroid.projectui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UpdateUserActivity extends Activity {
    Spinner Mypage_UpdateUser_StyleSpinner, Mypage_UpdateUser_ColorSpinner;
    Button Mypage_UpdateUser_ReturnBtn, Mypage_UpdateUser_UpdateBtn;
    EditText Mypage_UpdateUser_IDEdit, Mypage_UpdateUser_PWEdit, Mypage_UpdateUser_RePWEdit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_update);

        Mypage_UpdateUser_StyleSpinner = (Spinner) findViewById(R.id.Mypage_UpdateUser_StyleSpinner);
        Mypage_UpdateUser_ColorSpinner = (Spinner) findViewById(R.id.Mypage_UpdateUser_ColorSpinner);
        Mypage_UpdateUser_UpdateBtn = (Button) findViewById(R.id.Mypage_UpdateUser_UpdateBtn);
        Mypage_UpdateUser_ReturnBtn = (Button) findViewById(R.id.Mypage_UpdateUser_ReturnBtn);

        Mypage_UpdateUser_IDEdit = (EditText) findViewById(R.id.Mypage_UpdateUser_IDEdit);
        Mypage_UpdateUser_PWEdit = (EditText) findViewById(R.id.Mypage_UpdateUser_PWEdit);
        Mypage_UpdateUser_RePWEdit = (EditText) findViewById(R.id.Mypage_UpdateUser_RePWEdit);


        ArrayAdapter<CharSequence> styleAdapter = ArrayAdapter.createFromResource( // 스타일 선택
                this,
                R.array.StyleArray,
                android.R.layout.simple_spinner_item
        );
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Mypage_UpdateUser_StyleSpinner.setAdapter(styleAdapter);


        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource( // 색상 선택
                this,
                R.array.ColorArray,
                android.R.layout.simple_spinner_item
        );
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Mypage_UpdateUser_ColorSpinner.setAdapter(colorAdapter);


        Mypage_UpdateUser_ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });


        Mypage_UpdateUser_UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int update_id_length = Mypage_UpdateUser_IDEdit.getText().length();
                int update_pw_length = Mypage_UpdateUser_PWEdit.getText().length();
                String update_pw = Mypage_UpdateUser_PWEdit.getText().toString();
                String update_repw = Mypage_UpdateUser_RePWEdit.getText().toString();

                if (update_pw.equals("")&& update_repw.equals("")) {
                    Toast.makeText(UpdateUserActivity.this,
                            "비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();
                } else if (update_pw.equals("") || update_repw.equals("")) {
                    Toast.makeText(UpdateUserActivity.this,
                            "비밀번호를 확인하십시오", Toast.LENGTH_SHORT).show();
                } else if (update_pw_length >= 10) {
                    Toast.makeText(UpdateUserActivity.this,
                            "비밀번호를 10자 이내로 작성하십시오", Toast.LENGTH_SHORT).show();
                } else if (!update_pw.equals(update_repw)) {
                    Toast.makeText(UpdateUserActivity.this,
                            "입력한 비밀번호를 다시 확인하십시오", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateUserActivity.this,
                            "회원 정보를 수정하셨습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                    startActivity(intent); //이 부분은 나중에 회원정보 db 생기면 수정
                }
            }
        });

    }
}
