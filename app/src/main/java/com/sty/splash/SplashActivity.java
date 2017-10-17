package com.sty.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Steven.T on 2017/10/17/0017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1、方法1
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

        //2、方法2
        //该方法可能可以解决从SplashActivity到MainActivity过渡时的黑屏现象
        App.getApp().runOnUiThreadDelay(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                App.getApp().runOnUiThreadDelay(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
            }
        }, 1000);
    }
}
