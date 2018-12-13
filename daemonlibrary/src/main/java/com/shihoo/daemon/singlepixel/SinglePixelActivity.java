package com.shihoo.daemon.singlepixel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.shihoo.daemon.WatchDogService;

/**
 * Created by shihoo ON 2018/12/12.
 * Email shihu.wang@bodyplus.cc 451082005@qq.com
 *
 * 该Activity的View只要设置为1像素然后设置在Window对象上即可。在Activity的onDestroy周期中进行保活服务的存活判断从而唤醒服务。
 */
public class SinglePixelActivity extends AppCompatActivity {

    private static final String TAG = SinglePixelActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window mWindow = getWindow();
        mWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attrParams = mWindow.getAttributes();
        attrParams.x = 0;
        attrParams.y = 0;
        attrParams.height = 1;
        attrParams.width = 1;
        mWindow.setAttributes(attrParams);
        ScreenManager.getInstance(this).setSingleActivity(this);
    }

    @Override
    protected void onDestroy() {
        Intent intentAlive = new Intent(this, WatchDogService.class);
        startService(intentAlive);
//        if (!SystemUtils.isAppAlive(this, Constant.PACKAGE_NAME)) {
//        }
        super.onDestroy();
    }
}