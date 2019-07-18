package com.jlhx.apollp.application.ui.single.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.base.BaseFragment;
import com.jlhx.apollp.application.base.BaseFragmentAdapter;
import com.jlhx.apollp.application.ui.order.fragment.OrderFragment;
import com.jlhx.apollp.application.ui.person.fragment.PersonInfoFragment;
import com.jlhx.apollp.application.views.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class GrabSingleFragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;

    private BaseFragmentAdapter mFragmentAdapter;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private String[] mTitles = {"融资申请", "票据贴现"};
    private int mPosiotion = 0;

    public static GrabSingleFragment newInstance() {
        GrabSingleFragment fragment = new GrabSingleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grab_single;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BaseFragment fragment1 = OrderFragment.newInstance();
        BaseFragment fragment2 = PersonInfoFragment.newInstance();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);

        mFragmentAdapter = new BaseFragmentAdapter(mActivity.getSupportFragmentManager(), mFragmentList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabs.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(mPosiotion);

        for (int i = 0; i < 2; i++) {
            //获得每一个tab        //推送消息跳转到本类时，这句获取的tab可能为null，引起崩溃，逻辑分析有可能是用户角色获取失败导致的fragments初始化为空，进而导致mTabs设置tab也为空,,所以推送跳转的时候增加了用户是否登录的检查
            TabLayout.Tab tab = mTabs.getTabAt(i);
            //给每一个tab设置view    // Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'android.support.design.widget.TabLayout$Tab android.support.design.widget.TabLayout$Tab.setCustomView(int)' on a null object reference //            at com.kd.huiju.ui.resource.activity.NoAllocatedResourceActivity.initView(NoAllocatedResourceActivity.java:149)149
            tab.setCustomView(R.layout.tab_item);

            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
            //设置tab上的文字
            textView.setText(mTitles[i]);
        }

        if (mPosiotion == 0) {
            ((TextView) mTabs.getTabAt(mPosiotion).getCustomView().findViewById(R.id.tab_text)).setTextColor(getResources().getColor(R.color.color_FFFFFF));
            ((TextView) mTabs.getTabAt(mPosiotion).getCustomView().findViewById(R.id.tab_text)).setBackground(getResources().getDrawable(R.drawable.tab_left_select_bg));
        } else {
            ((TextView) mTabs.getTabAt(mPosiotion).getCustomView().findViewById(R.id.tab_text)).setTextColor(getResources().getColor(R.color.color_FFFFFF));
            ((TextView) mTabs.getTabAt(mPosiotion).getCustomView().findViewById(R.id.tab_text)).setBackground(getResources().getDrawable(R.drawable.tab_right_select_bg));
        }

        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tabTv = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                tabTv.setSelected(true);
                if (tab.getPosition() == 0) {
                    tabTv.setBackground(getResources().getDrawable(R.drawable.tab_left_select_bg));
                } else {
                    tabTv.setBackground(getResources().getDrawable(R.drawable.tab_right_select_bg));
                }
                tabTv.setTextColor(getResources().getColor(R.color.color_FFFFFF));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tabTv = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                tabTv.setSelected(false);
                if (tab.getPosition() == 0) {
                    tabTv.setBackground(getResources().getDrawable(R.drawable.tab_left_normal_bg));
                } else {
                    tabTv.setBackground(getResources().getDrawable(R.drawable.tab_right_normal_bg));
                }
                tabTv.setTextColor(getResources().getColor(R.color.color_406BE1));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }
}
