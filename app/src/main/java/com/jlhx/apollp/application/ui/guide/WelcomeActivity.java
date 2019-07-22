package com.jlhx.apollp.application.ui.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.base.BaseActivity;
import com.jlhx.apollp.application.bean.PushMessageBean;

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

    public static void startAction(Context context, PushMessageBean pushMessageBean) {
        Intent i = new Intent(context, WelcomeActivity.class);
        i.putExtra("push_message_bean", pushMessageBean);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // 隐藏状态栏,改用theme实现
        pushMessageBean = (PushMessageBean) getIntent().getSerializableExtra("push_message_bean");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        runnable = new Runnable() {
            @Override
            public void run() {
               GuideActivity.startGuidActivity(mActivity);
               finish();
            }
        };

//        new Handler().postDelayed(runnable, 3000);
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
