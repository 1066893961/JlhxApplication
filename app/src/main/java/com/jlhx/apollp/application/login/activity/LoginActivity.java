package com.jlhx.apollp.application.login.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.base.BaseActivity;
import com.jlhx.apollp.application.bean.PushMessageBean;
import com.jlhx.apollp.application.bean.UserBean;
import com.jlhx.apollp.application.login.view.OnButtonClickListener;
import com.jlhx.apollp.application.ui.dialog.DialogCommonForActivity;
import com.jlhx.apollp.application.utils.Preferences;

public class LoginActivity extends BaseActivity implements OnButtonClickListener {

    private FragmentManager supportFragmentManager;


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void start(Context context, PushMessageBean pushMessageBean) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("push_message_bean", pushMessageBean);
        context.startActivity(intent);
    }


    /**
     *
     * @param context
     * @param flag  为空时不弹框，适配锁定操作
     */
    public static void start(Context context, String flag) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("LOGOUT_MESSAGE", flag);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if(context instanceof Application){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    protected String getTitleName() {
        return null;
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //调用Bugly测Java Crash接口。  Logcat的TAG=CrashReportInfo
//        CrashReport.testJavaCrash();
        if (Preferences.isLogin()){
            gotoMain();
        }
        String msg = getIntent().getStringExtra("LOGOUT_MESSAGE");
//        if (!StringUtil.isEmpty(msg)){
//            DialogUtils.getInstance().showLogoutDialog(getResources().getString(R.string.tips_logout), LoginActivity.this, new DialogUtils.OnSureClickListener() {
//                @Override
//                public void onSure() {
//
//                }
//            });
//        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()> 0){
            //点击返回时如果是在短信验证码的页面就需要提示弹框
            DialogCommonForActivity dialogCommonForActivity = DialogCommonForActivity.newInstance("确定返回？");
            dialogCommonForActivity.show(supportFragmentManager,"validateCode");
        }else{
            super.onBackPressed();
        }
    }

    /**
     * 返回确定的回调
     * @param v
     */
    @Override
    public void onBuutonClick(View v) {
        if(supportFragmentManager.getBackStackEntryCount()>0){
            supportFragmentManager.popBackStack();
        }
    }

    private void loginSuccess(UserBean userBean) {
        if(userBean == null){
            return ;
        }

        Preferences.saveUserInfo(userBean);
    }

    @Override
    protected boolean addOnReceive(Context context, Intent intent) {
        return true;
    }

    /**
     * 打开MainActivity
     */
    private void gotoMain() {
//        PushMessageBean pushMessageBean = (PushMessageBean) getIntent().getSerializableExtra("push_message_bean");
//        if (pushMessageBean == null) {
//            startActivity(new Intent(mActivity, MainActivity.class));
//        }else {
//            Intent intent = new Intent(mActivity, MainActivity.class);
//            intent.putExtra("push_message_bean", pushMessageBean);
//            startActivity(intent);
//        }
        finish();
    }
}
