package com.lida.permissiondemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import com.apkfuns.logutils.LogUtils;
import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;

public class MainActivity extends Activity implements View.OnClickListener, OnPermissionCallback{

    PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        permissionHelper = PermissionHelper.getInstance(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                permissionHelper.requestAfterExplanation(Manifest.permission.ACCESS_COARSE_LOCATION);
                break;
            case R.id.button2:
                permissionHelper.requestAfterExplanation(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        LogUtils.e("onPermissionGranted:"+permissionName);
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
        LogUtils.e("onPermissionDeclined:"+permissionName);
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
        LogUtils.e("onPermissionPreGranted:"+permissionsName);
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
        LogUtils.e("onPermissionNeedExplanation:"+permissionName);
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        LogUtils.e("onPermissionReallyDeclined:"+permissionName);
        Toast.makeText(MainActivity.this,"请在权限设置里开启"+permissionName,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoPermissionNeeded() {
        LogUtils.e("onNoPermissionNeeded");
    }
}
