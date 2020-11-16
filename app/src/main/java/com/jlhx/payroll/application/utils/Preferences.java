package com.jlhx.payroll.application.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jlhx.payroll.application.JlhxApplication;
import com.jlhx.payroll.application.bean.UserBean;
import com.jlhx.payroll.application.constant.GlobalKeyContans;

import java.util.Calendar;

/**
 * 全局常量数据吧
 */
public class Preferences {
    private static final String KEY_USER_ID = "kd_user_id"; //用户id
    private static final String KEY_USERNAME = "kd_username"; //用户姓名
    private static final String KEY_USER_TOKEN = "kd_token"; //用户token
    private static final String KEY_USER_ROLE = "kd_role"; //用户role
    private static final String KEY_USER_ORGID = "kd_org"; //用户orgId
    private static final String KEY_RANDOM_IMEI = "random_imei";//随机生成imei DeviceId
    private static final String KEY_COUNT_DOWN_TIME = "kd_countdowntime";//保存当前倒计时失效时间

    private static final String SP_NAME = "kd_huiju";
    private static final String KEY_ADVISER = "key_adviser";
//    private static final String KEY_CUSTOMER_STATUS = "key_customer_status";
    private static final String KEY_PROVINCE = "key_province";
    private static final String KEY_TELE_MARKETING_TEAM_LIST = "key_teleSaleTeamList";
    private static final String KEY_ALL_DICTIONARY = "key_all_dictionary";

    private static long userId;     //用户id
    private static String username; //用户名
    private static String userToken; //用户token
    private static String roleCode; //用户role
    private static long orgId;    //用户组织机构id
    public static boolean ISBACKGROUND = true;  //是否后台标识

    /**
     * 保存随机imei
     * @param imei
     */
    public static void saveRandomImei(String imei) {
        saveString(KEY_RANDOM_IMEI, imei);
    }

    /**
     * 获取随机imei
     * @return
     */
    public static String getRandomImei() {
        return getString(KEY_RANDOM_IMEI);
    }

    /**
     * 获取用户id
     * @return
     */
    public static long getUserId() {
        if(userId == 0){
            userId = getSharedPreferences().getLong(KEY_USER_ID,0);
        }
        return userId;
    }

    /**
     * 获取username
     * @return
     */
    public static String getUsername() {
        if(TextUtils.isEmpty(username)){
            username = getString(KEY_USERNAME);
        }
        return username;
    }

    public static String getUserToken() {
        if(TextUtils.isEmpty(userToken)){
            userToken = getString(KEY_USER_TOKEN);
        }
        return userToken;
    }
    public static String getRoleCode() {
        if(TextUtils.isEmpty(roleCode)){
            roleCode = getString(KEY_USER_ROLE);
        }
        return roleCode;
    }
    public static long getOrgId() {
        if(orgId == 0){
            orgId = getLong(KEY_USER_ORGID);
        }
        return orgId;
    }

    /**
     * 保存用户信息（登录成功之后调用）
     * @param bean
     */
    public static void saveUserInfo(@NonNull UserBean bean) {
        Preferences.userId = bean.getId();
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(KEY_USER_ID, bean.getId());
        userId = bean.getId();
        editor.putString(KEY_USERNAME, bean.getUsername());
        username = bean.getUsername();
        editor.putString(KEY_USER_TOKEN, bean.getToken());
        userToken = bean.getToken();
        editor.putString(KEY_USER_ROLE, bean.getRolecode());
        roleCode = bean.getRolecode();
        editor.putLong(KEY_USER_ORGID, bean.getOrgId());
        orgId = bean.getOrgId();
        editor.commit();
    }

    /**
     * 检查给定角色和当前角色是否相等
     * @param role
     * @return
     */
    public static boolean checkRole(@GlobalKeyContans.Role String role){
        return role.equals(getRoleCode());
    }

    /**
     * 清除用户信息（退出成功调用）
     */
    public static void cleanUserInfo() {
        saveUserInfo(new UserBean());
    }

    /**
     * 是否登录
     * @return
     */
    public static boolean isLogin() {
        return getUserId() != 0/* && !TextUtils.isEmpty(getUserToken()) && !TextUtils.isEmpty(getRoleCode())*/;
    }

    /**
     * 获取短信验证码的限制时间
     * @return
     */
    public static int getCountDownTime(String username) {
        long aLong = getSharedPreferences().getLong(KEY_COUNT_DOWN_TIME+username, 0);
        if (aLong == 0){
            return -1;
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if(aLong>timeInMillis){
            return (int) ((aLong -timeInMillis)/1000);
        }
        return -1;
    }

    /**
     * 设置短信获取的限制时间
     */
    public static void saveCountDownTime(String username) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(KEY_COUNT_DOWN_TIME+username, Calendar.getInstance().getTimeInMillis()+ GlobalKeyContans.SHORT_MESSAGE_TIME_INTERVAL * 1000);
        editor.commit();

    }

    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static long getLong(String key) {
        return getSharedPreferences().getLong(key, 0);
    }
    private static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    static SharedPreferences getSharedPreferences() {
        return JlhxApplication.getApplication().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

}
