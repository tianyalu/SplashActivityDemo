package com.sty.splash;

import android.app.Application;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/**
 * Created by Steven.T on 2017/10/17/0017.
 */

public class App extends Application {

    private Handler handler;
    private ExecutorService backgroundExecutor;
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        //Don't do this! this is just so cold launches take some time
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));

        handler = new Handler();
        backgroundExecutor = Executors.newFixedThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable, "Background executor service");
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    public static App getApp() {
        return mApp;
    }

    public Future<?> runInBackground(final Runnable runnable) {
        return backgroundExecutor.submit(runnable);
    }

    public void runOnUiThread(final Runnable runnable) {
        handler.post(runnable);
    }

    public void runOnUiThreadDelay(final Runnable runnable, long delayMillis) {
        handler.postDelayed(runnable, delayMillis);
    }
}
