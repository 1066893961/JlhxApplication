package com.jlhx.apollp.application.ui.order.fragment;

import android.os.Bundle;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.base.BaseFragment;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class OrderFragment extends BaseFragment{


    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
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
