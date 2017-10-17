package com.sty.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 源码参考：https://www.bignerdranch.com/blog/splash-screens-the-right-way/
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
