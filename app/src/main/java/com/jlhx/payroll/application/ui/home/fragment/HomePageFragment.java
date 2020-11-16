package com.jlhx.payroll.application.ui.home.fragment;

import android.os.Bundle;

import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.base.BaseFragment;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class HomePageFragment extends BaseFragment {


    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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
}
