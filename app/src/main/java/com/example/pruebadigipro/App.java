package com.example.pruebadigipro;

import android.app.Application;

import com.digipro.fesdkcore.FeSdkCore;


public class App extends Application {

    @Override
    public void onCreate() {
        try {
            new FeSdkCore().init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate();

    }

}
