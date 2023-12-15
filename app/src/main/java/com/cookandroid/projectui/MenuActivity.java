package com.cookandroid.projectui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuActivity extends Activity {
    private DrawerLayout drawerLayout;
    ImageButton test_MenuOpenBtn,test_MenuCloseBtn;
    private View drawView;
    TextView MypageText, RecentFText;
    TextView LogoutText;

    Button RecommendationButton;
    Button ExistingFurnitureBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1);

        drawerLayout = (DrawerLayout) findViewById(R.id.menu1Layout);
        drawView =(View) findViewById(R.id.menu2Layout) ;
        test_MenuOpenBtn = (ImageButton) findViewById(R.id.MenuOpenBtn);
        test_MenuCloseBtn = (ImageButton) findViewById(R.id.MenuCloseBtn);
        MypageText = (TextView) findViewById(R.id.MypageText);
        RecentFText = (TextView) findViewById(R.id.RecentFText);
        LogoutText = (TextView) findViewById(R.id.LogoutText);

        RecommendationButton = (Button) findViewById(R.id.RecommendationButton);
        ExistingFurnitureBtn = (Button) findViewById(R.id.ExistingFurnitureBtn);


        test_MenuOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawView);
            }
        });
        test_MenuCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        MypageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MypageActivity.class);
                startActivity(intent);
            }
        });

        RecentFText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecentFurnitureActivity.class);
                startActivity(intent);
            }
        });

        LogoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent); //일단 가입 버튼만 눌러도 처음 화면으로 이동하도록 했음
                //이 부분은 나중에 회원정보 db 생기면 수정
            }
        });

        RecommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recommendationIntent = new Intent(getApplicationContext(), RecommendationActivity.class);
                startActivity(recommendationIntent);
            }
        });

        ExistingFurnitureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent existingFurnitureIntent = new Intent(getApplicationContext(), ExistingFurnitureActivity.class);
                startActivity(existingFurnitureIntent);
            }
        });
    }

}