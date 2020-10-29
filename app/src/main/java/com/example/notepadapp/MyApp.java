package com.example.notepadapp;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(),"2e6d9caa1f",true);
        CrashReport.initCrashReport(getApplicationContext());
        CrashReport.testJavaCrash();
    }
}
