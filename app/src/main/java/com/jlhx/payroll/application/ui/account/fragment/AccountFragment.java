package com.jlhx.payroll.application.ui.account.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.base.BaseFragment;
import com.jlhx.payroll.application.http.HttpHelper;
import com.jlhx.payroll.application.http.JsonCallback;
import com.jlhx.payroll.application.http.LzyResponse;
import com.jlhx.payroll.application.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class AccountFragment extends BaseFragment{

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text2)
    TextView text2;

    private String content;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
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


    @OnClick({R.id.btn, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                getIsCollect();
                break;
            case R.id.btn2:
//                WebViewActivity.startActivity(getApplicationContext(), "", url);


                String url = "alipays://platformapi/startapp?appId=60000157&appClearTop=false&startMultApp=YES&sign_params=" + content;

                boolean visit = checkAliPayInstalled(getContext());
                if (visit) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }

                break;
        }
    }


    //判断是否安装支付宝app
    public static boolean checkAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }


    /**
     * 获取签约url
     */
    private void getIsCollect() {
        HttpHelper.getIsCollect(TAG, new JsonCallback<LzyResponse<Object>>() {
            @Override
            public void onSuccess(LzyResponse<Object> listLzyResponse, Call call, Response response) {
//                url = listLzyResponse.data.toString();
                content = listLzyResponse.data.toString();
                text.setText(content);
//                content = url.substring(url.indexOf("?")+1, url.length());
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                ToastUtils.showLongToast(e.getMessage());
            }
        });
    }
}
