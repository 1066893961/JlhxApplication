package com.jlhx.apollp.application.ui.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.ui.MainActivity;
import com.jlhx.apollp.application.views.bgabanner.BGABanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwz on 2017/3/29.
 */

public class GuideActivity extends Activity {

    private BGABanner mContentBanner;

    public static void startGuidActivity(Context context) {
        Intent it = new Intent(context, GuideActivity.class);
        context.startActivity(it);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        processLogic();
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        mContentBanner = (BGABanner) findViewById(R.id.banner_guide_content);
    }

    private void setListener() {
        mContentBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
//                new SPUtils(GlobalKeyContans.SP_NAME).put(GlobalKeyContans.SP_KEY_IS_FIRST_START + UpdateUtil.getVersionName(KDApplication.getContext()), false);
//                boolean isFirstRequest = new SPUtils(GlobalKeyContans.SP_NAME).getBoolean(GlobalKeyContans.SP_KEY_IS_FIRST_REQUEST, true);
//                if (isFirstRequest) {
//                    NewGuideAgeSettingActivity.start(GuideActivity.this);
//                } else {
//                    MainActivity.start(GuideActivity.this);
//                }

                MainActivity.start(GuideActivity.this);
                finish();

            }
        });
    }

    private void processLogic() {
        List<View> views = new ArrayList<>();
        views.add(View.inflate(this, R.layout.item_guide_01, null));
        views.add(View.inflate(this, R.layout.item_guide_02, null));
        views.add(View.inflate(this, R.layout.item_guide_03, null));
        mContentBanner.setData(views);
    }

}