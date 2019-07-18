package com.jlhx.apollp.application.http;

import com.jlhx.apollp.application.utils.ParamsUtils;
import com.lzy.okgo.OkGo;

import java.util.HashMap;

/**
 * Created by lixinli on 2018/11/28.
 */
public class HttpHelper {

    /**
     * @param tag 取消请求标识
     */
    public static void cancelTag(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }


    /**
     * 登录
     *
     * @param tag      取消请求标识
     * @param username 用户名
     * @param pwd      密码
     * @param smsCode  验证码
     * @param callback 回调
     */
    public static void login(Object tag, String username, String pwd, String smsCode, JsonCallback callback) {
//        {
//            "username": "lxdxzj",
//                "password": "a1234567",
//                "authcode": "179441",
//                "alias":"a123456"
//        }
//        HashMap<String, String> params = ParamsUtils.getParams();
//        params.put("username", username);
//        params.put("password", pwd);
//        params.put("authcode", smsCode);
////        params.put("alias", "a123456");//推送
//        params.put("alias", JPushInterface.getRegistrationID(KDApplication.getApplication()));//推送
//
//        OkGo.post(AddressContants.API_SERVER_LOGIN)
//                .tag(tag)
//                .upJson(ParamsUtils.getJson(params))
//                .execute(callback);
    }

    /**
     * 退出
     *
     * @param tag      取消请求标识
     * @param callback 回调
     */
    public static void logout(Object tag, JsonCallback callback) {
//        long userId = Preferences.getUserId();
//        if (userId == 0) {
//            callback.onError(null, null, new Exception("用户信息异常"));
//            return;
//        }
//        HashMap<String, Object> objParams = ParamsUtils.getObjParams();
//        objParams.put("id", userId);
//        OkGo.post(AddressContants.API_SERVER_LOGOUT)
//                .tag(tag)
//                .upJson(ParamsUtils.getObjJson(objParams))
//                .execute(callback);
    }

    /**
     * 获取用户信息
     *
     * @param tag      取消请求标识
     * @param callback 回调
     */
    public static void getUserInfo(Object tag, JsonCallback callback) {
//        long userId = Preferences.getUserId();
//        if (userId == 0) {
//            callback.onError(null, null, new Exception("用户信息异常"));
//            return;
//        }
//        HashMap<String, Object> params = ParamsUtils.getObjParams();
//        params.put("id", userId);
//        OkGo.post(AddressContants.API_SERVER_GET_USER)
//                .tag(tag)
//                .upJson(ParamsUtils.getObjJson(params))
//                .execute(callback);
    }


    /**
     * 检查用户名密码，用来跳转到获取验证码
     *
     * @param tag
     * @param username
     * @param pwd
     * @param callback
     */
    public static void checkUser(Object tag, String username, String pwd, JsonCallback callback) {
//        HashMap<String, String> params = ParamsUtils.getParams();
//        params.put("username", username);
//        params.put("password", pwd);
//        OkGo.post(AddressContants.API_SERVER_CHECK_USER)
//                .tag(tag)
//                .upJson(ParamsUtils.getJson(params))
//                .execute(callback);
    }


    /**
     * 修改密码
     *
     * @param tag
     * @param oldPwd
     * @param newPwd
     * @param callback
     */
    public static void updatePwd(Object tag, String oldPwd, String newPwd, JsonCallback callback) {
//        long userId = Preferences.getUserId();
//        if (userId == 0) {
//            callback.onError(null, null, new Exception("用户信息异常"));
//            return;
//        }
//        HashMap<String, String> params = ParamsUtils.getParams();
//        params.put("username", Preferences.getUsername());
//        params.put("oldPassword", oldPwd);
//        params.put("password", newPwd);
//        params.put("newPassword", newPwd);
//        OkGo.post(AddressContants.API_SERVER_UPDATE_PWD)
//                .tag(tag)
//                .upJson(ParamsUtils.getJson(params))
//                .execute(callback);
    }

    /**
     * 修改用户信息,个性签名
     *
     * @param tag
     * @param key
     * @param value
     * @param callback
     */
    public static void updateUserData(Object tag, String key, String value, JsonCallback callback) {
//        long userId = Preferences.getUserId();
//        if (userId == 0) {
//            callback.onError(null, null, new Exception("用户信息异常"));
//            return;
//        }
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("id", userId);
//        params.put(key, value);
//        OkGo.post(AddressContants.API_SERVER_UPDATE_USER_DATA)
//                .tag(tag)
//                .upJson(ParamsUtils.getObjJson(params))
//                .execute(callback);
    }

    /**
     * 验证码
     *
     * @param tag
     * @param username 用户名
     * @param type     验证码类型1：短信验证2：语音验证
     * @param callback
     */
    public static void postValidateCode(Object tag, String username, String type, String phone, JsonCallback callback) {
//        HashMap<String, String> params = ParamsUtils.getParams();
//        params.put("type", type);
//        params.put("phone", phone);
//        params.put("username", username);
//
//        OkGo.post(AddressContants.API_SERVER_SMS_SEND)
//                .tag(tag)
//                .upJson(ParamsUtils.getJson(params))
//                .execute(callback);
    }

//    public static void postSmsCode(Object tag, String username, String phone, JsonCallback callback) {
//        postValidateCode(tag, username, "1", phone, callback);
//    }
//
//    public static void postVoiceCode(Object tag, String username, String phone, JsonCallback callback) {
//        postValidateCode(tag, username, "2", phone, callback);
//    }

    public static void getListsData(Object tag, JsonCallback callback) {
        long time = System.currentTimeMillis();
        String str = String.valueOf(time);
        HashMap<String, String> params = ParamsUtils.getParams();
        params.put("pageNum", 1 + "");
        params.put("pageSize", 10 + "");
        params.put("firstStageType", "0" + 1);
        params.put("timePointer", str);

        OkGo.post("http://kuaidaoservice-information-test.kuaidaoapp.com/v1.0/information/app/list")
                .tag(tag)
                .upJson(ParamsUtils.getJson(params))
                .execute(callback);
    }
}
