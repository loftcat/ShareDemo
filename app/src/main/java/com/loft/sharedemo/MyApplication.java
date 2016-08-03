package com.loft.sharedemo;

import android.app.Application;

import com.loft.shareutil.ShareUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtil.getInstance(getApplicationContext()).initQQ("YOUR APPID").initSina_Weibo("YOUR APPID").initWeixin("YOUR APPID");
    }
}
