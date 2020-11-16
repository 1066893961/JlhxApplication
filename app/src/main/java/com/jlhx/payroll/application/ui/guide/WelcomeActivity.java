package com.jlhx.payroll.application.ui.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.base.BaseActivity;
import com.jlhx.payroll.application.bean.PushMessageBean;
import com.jlhx.payroll.application.ui.MainActivity;
import com.jlhx.payroll.application.ui.login.activity.LoginActivity;

public class WelcomeActivity extends BaseActivity {
    Runnable runnable;
    private PushMessageBean pushMessageBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
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
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        runnable = new Runnable() {
            @Override
            public void run() {
//               GuideActivity.startGuidActivity(mActivity);
                LoginActivity.start(WelcomeActivity.this);
               finish();
            }
        };

        new Handler().postDelayed(runnable, 1000);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
