package com.cookandroid.projectui;

/* 회원가입 참고 : https://bj-turtle.tistory.com/49 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MembershipActivity extends Activity {
    private static String IP_ADDRESS = "10.0.2.2:88"; //서버 IP 주소
    private static String PHP_FILE = "/sample/android_insert.php"; //PHP 파일 경로

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

        /*
        (1) ArrayAdapter 생성하기 : [res]-[values]-strings.xml의 StyleArray와 기본적인 스피너 레이아웃 사용
        (2) 선택 항목 리스트가 나타날 때 사용할 레이아웃 지정
        (3) 스피너에 어뎁터 적용
         */
        // 스타일 선택
        ArrayAdapter<CharSequence> styleAdapter = ArrayAdapter.createFromResource( 
                this,
                R.array.StyleArray,
                android.R.layout.simple_spinner_item
        );
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //(2)
        MembershipLayout_StyleSpinner.setAdapter(styleAdapter); //(3)

        // 색상 선택
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource( 
                this,
                R.array.ColorArray,
                android.R.layout.simple_spinner_item
        );
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MembershipLayout_ColorSpinner.setAdapter(colorAdapter);


        // 취소 버튼
        MembershipLayout_Layout_ReturnBtn.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View view) { finish(); }
        });
        
        // 회원가입 버튼
        MembershipLayout_Layout_JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_length = MembershipLayout_IDLayout_IDEdit.getText().length();
                int pw_length = MembershipLayout_PWEdit.getText().length();
                String id = MembershipLayout_IDLayout_IDEdit.getText().toString();
                String pw = MembershipLayout_PWEdit.getText().toString();
                String repw = MembershipLayout_RePWEdit.getText().toString();
                int styleValue = MembershipLayout_StyleSpinner.getSelectedItemPosition();
                int colorValue = MembershipLayout_ColorSpinner.getSelectedItemPosition();
                //getSelectedItemPosition() : 선택된 항목의 위치(인덱스)를 반환

                if (id.equals("") && pw.equals("")&& repw.equals("")) {
                    Toast.makeText(MembershipActivity.this,
                            "아이디와 비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();
                } else if (id.equals("")) {
                    Toast.makeText(MembershipActivity.this,
                            "아이디를 입력하십시오", Toast.LENGTH_SHORT).show();
                } else if (pw.equals("") || repw.equals("")) {
                    Toast.makeText(MembershipActivity.this,
                            "비밀번호를 확인하십시오", Toast.LENGTH_SHORT).show();
                } else if (id_length >= 6) {
                    Toast.makeText(MembershipActivity.this,
                            "아이디를 6자 이내로 작성하십시오", Toast.LENGTH_SHORT).show();
                } else if (pw_length >= 10) {
                    Toast.makeText(MembershipActivity.this,
                            "비밀번호를 10자 이내로 작성하십시오", Toast.LENGTH_SHORT).show();
                } else if (!pw.equals(repw)) {
                    Toast.makeText(MembershipActivity.this,
                            "입력한 비밀번호를 다시 확인하십시오", Toast.LENGTH_SHORT).show();
                } else {
                    InsertData task = new InsertData(); //InsertData 클래스의 task 객체
                    //접속할 PHP 주소와 보낼 데이터
                    task.execute("http://"+IP_ADDRESS+PHP_FILE, id, pw,
                            String.valueOf(styleValue), String.valueOf(colorValue));
                    Toast.makeText(MembershipActivity.this,
                            "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    class InsertData extends AsyncTask<String,Void,String> { //PHP 통신을 위한 InsertData
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() { //AsyncTask가 백그라운드 작업을 시작하기 전에 호출
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MembershipActivity.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) { //AsyncTask의 백그라운드 작업이 완료된 후에 호출
            super.onPostExecute(result);
            progressDialog.dismiss(); //onPostExecute()에 오게되면 진행 다이얼로그 취소
        }

        @Override
        protected String doInBackground(String... params) { //AsyncTask의 백그라운드 작업
            /*
            task.execute()에서 매개변수로 준 값이 차례대로 들어가 값을 받아오는 개념. (배열처럼)
             */
            String serverURL = (String) params[0]; //PHP 주소
            String userID = (String)params[1]; //전송할 데이터들
            String password = (String)params[2];
            String style = (String)params[3];
            String color = (String)params[4];

            /*
            postData : 전송할 데이터
            전송할 데이터는 "이름=값" 형식이며, 여러 개를 보내야 할 경우 항목 사이에 "&" 추가.
            여기에 적은 이름들은 나중에 PHP에서 사용하여 값을 얻게 됨.
             */
            String postData ="userID="+userID+"&password="+ password
                    +"&style="+style+"&color="+color;

            try{ // HttpURLConnection 클래스를 사용하여 POST 방식으로 데이터를 전송한다.
                URL url = new URL(serverURL); //주소 저장
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000); //5초 안에 응답 안 오면 예외 발생
                httpURLConnection.setConnectTimeout(5000); //5초 안에 연결 안 되면 예외 발생
                httpURLConnection.setRequestMethod("POST"); //요청 방식을 POST로
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream(); // 데이터 전송
                outputStream.write(postData.getBytes("UTF-8"));
                outputStream.flush(); //현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼 비우기
                outputStream.close(); //자원 반납 (객체 닫기)

                
                int responseStatusCode = httpURLConnection.getResponseCode(); // 응답 읽기
                
                InputStream inputStream;

                if(responseStatusCode == httpURLConnection.HTTP_OK){ //정상적인 응답 데이터라면?
                    inputStream = httpURLConnection.getInputStream();
                } else { //에러가 발생한다면?
                    inputStream = httpURLConnection.getErrorStream();
                }

                // StringBuilder를 사용하여 수신되는 데이터 저장
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) !=null ) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString(); //저장된 데이터를 String으로 변환하여 리턴값으로 받음
            }
            catch (Exception e) {
                return new String("Error " + e.getMessage());
            }

        } //doInBackground 메서드 끝
    } //InsertData 클래스 끝

} //MembershipActivity 끝
