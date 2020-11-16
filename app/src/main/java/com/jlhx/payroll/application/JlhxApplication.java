package com.jlhx.payroll.application;

import android.app.Activity;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.jlhx.payroll.application.utils.Preferences;
import com.lzy.okgo.OkGo;

import java.util.logging.Level;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class JlhxApplication extends MultiDexApplication {
    private static JlhxApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        //网络请求框架
        OkGo.init(this);
        OkGo.getInstance().setConnectTimeout(15 * 1000);   //超时事件15秒
        //OkGo.getInstance().setReadTimeOut(15 * 1000);   //
        OkGo.getInstance().setRetryCount(1);   //重复请求1次
        if (BuildConfig.DEBUG) {
            OkGo.getInstance().debug("OkGo", Level.INFO, true);   //日志打印
//            OkGo.getInstance().setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

//            if(!LeakCanary.isInAnalyzerProcess(this)){
//                LeakCanary.install(this);
//            }
        }

        // 极光推送  true开启测试模式
//        JPushInterface.setDebugMode(BuildConfig.DEBUG); //debug情况开启推送log
//        JPushInterface.init(this);
//        JPushInterface.setLatestNotificationNumber(this, 100);
//        if (GlobalConfigContants.intEnviSwitch == 0) {
//            Set<String> tags = new HashSet();
//            tags.add("test");
//            JPushInterface.setTags(this, 123456, tags);
//        } else if (GlobalConfigContants.intEnviSwitch == 1) {
//            Set<String> tags = new HashSet();
//            tags.add("pre");
//            JPushInterface.setTags(this, 123456, tags);
//        } else if (GlobalConfigContants.intEnviSwitch == 2) {
//            Set<String> tags = new HashSet();
//            tags.add("prod");
//            JPushInterface.setTags(this, 123456, tags);
//        }

        /**
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         bugly初始化
         输出详细的Bugly SDK的Log；
         每一条Crash都会被立即上报；
         自定义日志将会在Logcat中输出。
         建议在测试阶段建议设置成true，发布时设置为false。
         */
//        if (GlobalConfigContants.intEnviSwitch == 0 || GlobalConfigContants.intEnviSwitch == 1) {
//            CrashReport.initCrashReport(getApplicationContext(), "4b15bdeef9", true);//李卫振的
////            CrashReport.initCrashReport(getApplicationContext(), "158a385ee9", true);//产品的
//        } else {
//            CrashReport.initCrashReport(getApplicationContext(), "158a385ee9", false);
//        }

        registerActivityLifecycleCallbacks();
    }

    public static JlhxApplication getApplication() {
        return mApplication;
    }

    private int count = 0;

    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (count == 0) {
                    Preferences.ISBACKGROUND = false;
                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    Preferences.ISBACKGROUND = true;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}