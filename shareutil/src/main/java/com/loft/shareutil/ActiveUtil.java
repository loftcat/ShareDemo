package com.loft.shareutil;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import java.util.List;

public class ActiveUtil {

    public static boolean isAvilibleBypackageName(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    public static Intent isAvilibleByappName(Context context, String appName) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pManager = context.getPackageManager();
        /**
         * queryIntentActivities通过解析所有应用程序中含有如下Intent-filter的App
         * <intent-filter>
         *      <action android:name="android.intent.action.MAIN" />
         *      <category android:name="android.intent.category.LAUNCHER" />
         * </intent-filter>
         */
        final List<ResolveInfo> infoList = pManager.queryIntentActivities(mainIntent, 0);
        if (infoList != null) {
            for (ResolveInfo info : infoList) {
//                AppInfo app = makeAppInfo(pManager, info);
//                appList.add(app);
                if (appName.equals(info.loadLabel(pManager))) {
                    ComponentName componet = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
                    mainIntent.setComponent(componet);
                    return mainIntent;
                }
            }
        }
        return null;
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }


}
