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

public class MembershipActivity extends Activity {
    Button MembershipLayout_IDLayout_IDCheckBtn;
    Spinner MembershipLayout_StyleSpinner, MembershipLayout_ColorSpinner;
    Button MembershipLayout_Layout_ReturnBtn, MembershipLayout_Layout_JoinBtn;
    EditText MembershipLayout_IDLayout_IDEdit, MembershipLayout_PWEdit, MembershipLayout_RePWEdit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);

        MembershipLayout_StyleSpinner = (Spinner) findViewById(R.id.MembershipLayout_StyleSpinner);
        MembershipLayout_ColorSpinner = (Spinner) findViewById(R.id.MembershipLayout_ColorSpinner);
        MembershipLayout_Layout_JoinBtn = (Button) findViewById(R.id.MembershipLayout_Layout_JoinBtn);
        MembershipLayout_Layout_ReturnBtn = (Button) findViewById(R.id.MembershipLayout_Layout_ReturnBtn);

        MembershipLayout_IDLayout_IDEdit = (EditText) findViewById(R.id.MembershipLayout_IDLayout_IDEdit);
        MembershipLayout_PWEdit = (EditText) findViewById(R.id.MembershipLayout_PWEdit);
        MembershipLayout_RePWEdit = (EditText) findViewById(R.id.MembershipLayout_RePWEdit);


        //ArrayAdapter 생성하기 : [res]-[values]-strings.xml의 StyleArray와 기본적인 스피너 레이아웃 사용
        ArrayAdapter<CharSequence> styleAdapter = ArrayAdapter.createFromResource( // 스타일 선택
                this,
                R.array.StyleArray,
                android.R.layout.simple_spinner_item
        );
        //선택 항목 리스트가 나타날 때 사용할 레이아웃 지정
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //스피너에 어뎁터 적용
        MembershipLayout_StyleSpinner.setAdapter(styleAdapter);


        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource( // 색상 선택
                this,
                R.array.ColorArray,
                android.R.layout.simple_spinner_item
        );
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MembershipLayout_ColorSpinner.setAdapter(colorAdapter);



        MembershipLayout_Layout_JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = MembershipLayout_IDLayout_IDEdit.getText().length();
                int num2 = MembershipLayout_PWEdit.getText().length();
                String num3 = MembershipLayout_IDLayout_IDEdit.getText().toString();
                String num4 = MembershipLayout_PWEdit.getText().toString();
                String num5 = MembershipLayout_RePWEdit.getText().toString();

                if (num3.equals("") && num4.equals("")&& num5.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();

                } else if (num3.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하십시오", Toast.LENGTH_SHORT).show();
                } else if (num4.equals("") || num5.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();
                } else if (num1 >= 6) {
                    Toast.makeText(getApplicationContext(),
                            "아이디를 6자 이내로 작성하십시오", Toast.LENGTH_SHORT).show();
                } else if (num2 >= 10) {
                    Toast.makeText(getApplicationContext(),
                            "비밀번호를 6자 이내로 작성하십시오", Toast.LENGTH_SHORT).show();
                } else if (!num4.equals(num5)) {
                    Toast.makeText(getApplicationContext(),
                            "입력한 비밀번호를 다시 확인하십시오", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent); //일단 가입 버튼만 눌러도 로그인 화면으로 이동하도록했음
                    // 이 부분은 나중에 회원정보 db 생기면 수정}
                }
            }
        });
        MembershipLayout_Layout_ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }
}
