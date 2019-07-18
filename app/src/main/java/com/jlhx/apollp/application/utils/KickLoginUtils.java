package com.jlhx.apollp.application.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.jlhx.apollp.application.constant.GlobalKeyContans;
import com.jlhx.apollp.application.login.activity.LoginActivity;

/**
 * Created by lixinli on 2019/1/23.
 */
public class KickLoginUtils {

    public static void kickLogin(Context context,String message){
        if(context == null){
            return ;
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(GlobalKeyContans.MESSAGE_FINISH_ACTION));//关闭当前活动的activity
        Preferences.cleanUserInfo();
        if(Preferences.ISBACKGROUND){
//                Process.killProcess(Process.myPid());//不能使用这中方式，app有崩溃重启机制，还是会启动
        }else {
            LoginActivity.start(context,message);
        }
    }
}
