package com.github.fitandroid.demo;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.fitandroid.marshmallow.IPermissionListenerWrap;
import com.github.fitandroid.marshmallow.PermissionsHelper;
import com.github.fitandroid.nougat.FileProvider7;
import com.github.fitandroid.oreo.Permission8;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnPermission;
    private Button mBtnFileProvider;
    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnPermission = findViewById(R.id.btn_permission);
        mBtnFileProvider = findViewById(R.id.btn_file_provider);
        mBtnPermission.setOnClickListener(this);
        mBtnFileProvider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_permission:{
                //安卓8.0之前的权限申请
//                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
                //安卓8.0之后建议按照权限组申请权限，以免漏掉权限
                String[] permissions = Permission8.Group.STORAGE;
                PermissionsHelper helper = new PermissionsHelper(this);
                helper.request(permissions, new IPermissionListenerWrap.IPermissionListener() {
                    @Override
                    public void onAccepted(boolean isGranted) {
                        Toast.makeText(MainActivity.this, "isGranted:"+isGranted, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case R.id.btn_file_provider:{
                String[] permissions = {Manifest.permission.CAMERA};
                PermissionsHelper helper = new PermissionsHelper(this);
                helper.request(permissions, new IPermissionListenerWrap.IPermissionListener() {
                    @Override
                    public void onAccepted(boolean isGranted) {
                        if (isGranted){
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                                        .format(new Date()) + ".png";
                                File file = new File(Environment.getExternalStorageDirectory(), filename);
                                mCurrentPhotoPath = file.getAbsolutePath();
                                // 仅需改变这一行
                                Uri fileUri = FileProvider7.getUriForFile(MainActivity.this, file);

                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "没有拍照权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
            View view = LayoutInflater.from(this).inflate(R.layout.view_photo_preview,null);
            ImageView imageView = view.findViewById(R.id.iv_photo);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setView(view)
                    .setPositiveButton("关闭", null)
                    .create();
            alertDialog.show();
        }
    }
}