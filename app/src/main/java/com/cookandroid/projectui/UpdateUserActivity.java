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


        //ArrayAdapter 생성하기 : [res]-[values]-strings.xml의 StyleArray와 기본적인 스피너 레이아웃 사용
        ArrayAdapter<CharSequence> styleAdapter = ArrayAdapter.createFromResource( // 스타일 선택
                this,
                R.array.StyleArray,
                android.R.layout.simple_spinner_item
        );
        //선택 항목 리스트가 나타날 때 사용할 레이아웃 지정
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //스피너에 어뎁터 적용
        Mypage_UpdateUser_StyleSpinner.setAdapter(styleAdapter);


        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource( // 색상 선택
                this,
                R.array.ColorArray,
                android.R.layout.simple_spinner_item
        );
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Mypage_UpdateUser_ColorSpinner.setAdapter(colorAdapter);



        Mypage_UpdateUser_UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num6 = Mypage_UpdateUser_IDEdit.getText().length();
                int num7 = Mypage_UpdateUser_PWEdit.getText().length();
                String num9 = Mypage_UpdateUser_PWEdit.getText().toString();
                String num10 = Mypage_UpdateUser_RePWEdit.getText().toString();

                if (num9.equals("")&& num10.equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();

                } else if (num9.equals("") || num10.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();
                } else if (num7>= 10) {
                    Toast.makeText(getApplicationContext(),
                            "비밀번호를 6자 이내로 작성하십시오", Toast.LENGTH_SHORT).show();
                } else if (!num9.equals(num10)) {
                    Toast.makeText(getApplicationContext(),
                            "입력한 비밀번호를 다시 확인하십시오", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "회원 정보를 수정하셨습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                    startActivity(intent); //이 부분은 나중에 회원정보 db 생기면 수정
                }
            }
        });
        Mypage_UpdateUser_ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }
}
