package com.cookandroid.projectui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;


public class ExistingFurnitureActivity extends AppCompatActivity implements LifecycleOwner {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    PreviewView previewView;
    EditText WidthEdit, LengthEdit, HeightEdit; //가로, 세로, 높이 값
    Button SpaceLengthBtn; //공간 길이 측정

    private static final int REQUEST_CAMERA_PERMISSION = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_furniture);

        previewView = findViewById(R.id.PreviewView);
        SpaceLengthBtn = findViewById(R.id.SpaceLengthBtn);


        /* 권한 요청 : https://stackoverflow.com/questions/38552144/how-get-permission-for-camera-in-android-specifically-marshmallow */
        /* 참고 부분 : if (Build.VERSION.SDK_INT...) {...}, onRequestPermissionsResult */

        // android.Manifest.permission.CAMERA <-- android.Manifest.permission 쓰면 자동으로 Manifest.permission로 바뀜
        // if(checkSelfPermission ...) {...} 얘만 쓰면 오류 발생 --> 빨간색 느낌표 클릭 --> surround 뭐시기 SDK 클릭
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
            else { //권한이 이미 허용되었을 경우에도 카메라 설정을 호출
                cameraSetting();
            }
        }

        SpaceLengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),SpaceLengthActivity.class);
//                startActivity(intent);
            }
        });
    } //onCreate 끝


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA_PERMISSION) { //권한 요청 응답 처리
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraSetting(); //권한 허용 --> 카메라 설정
            }
            else { //권한 거부 --> AlertDialog 메시지
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("카메라 권한 허용을 안하시면 앱 기능 사용이 불가합니다. 카메라 권한을 설정 하십시오.")
                        .setTitle("카메라 권한 허용")
                        .setCancelable(false)
                        .setNegativeButton(" 취소", ((dialogInterface, i) -> dialogInterface.dismiss() ))
                        .setPositiveButton("설정하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);

                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
            }
        }
    }


    /* CameraX 미리보기 구현 : https://developer.android.com/training/camerax/preview?hl=ko#java */
    /* 참고 부분 : cameraSetting(), bindCameraPreview() */
    private void cameraSetting() { //카메라 설정
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                //예외 처리
            }
        }, ContextCompat.getMainExecutor(this));
    }


    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK) //후면 카메라 사용
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);
    }

}
