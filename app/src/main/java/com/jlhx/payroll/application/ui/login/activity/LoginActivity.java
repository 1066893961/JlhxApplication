package com.jlhx.payroll.application.ui.login.activity;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.base.BaseActivity;
import com.jlhx.payroll.application.bean.PushMessageBean;
import com.jlhx.payroll.application.bean.UserBean;
import com.jlhx.payroll.application.constant.GlobalKeyContans;
import com.jlhx.payroll.application.http.HttpHelper;
import com.jlhx.payroll.application.http.JsonCallback;
import com.jlhx.payroll.application.http.LzyResponse;
import com.jlhx.payroll.application.manager.ActivityCollector;
import com.jlhx.payroll.application.manager.ConnectivityManage;
import com.jlhx.payroll.application.ui.MainActivity;
import com.jlhx.payroll.application.ui.login.view.OnButtonClickListener;
import com.jlhx.payroll.application.ui.dialog.DialogCommonForActivity;
import com.jlhx.payroll.application.utils.CheckHelper;
import com.jlhx.payroll.application.utils.Preferences;
import com.jlhx.payroll.application.utils.SoftKeyBoardListener;
import com.jlhx.payroll.application.utils.ToastUtils;
import com.jlhx.payroll.application.utils.dialog.DialogMaker;

import org.json.JSONObject;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements OnButtonClickListener {

    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.input_phone_et)
    EditText inputPhoneEt;
    @BindView(R.id.clear_iv)
    ImageView clearIv;
    @BindView(R.id.vertify_tv)
    TextView vertifyTv;
    @BindView(R.id.vertify_et)
    EditText vertifyEt;
    @BindView(R.id.send_vertify_number_tv)
    TextView sendVertifyNumberTv;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.regist_tv)
    TextView registTv;
    private FragmentManager supportFragmentManager;

    private TimerTask timerTask;
    private Timer timer;
    private int timess;
    protected UserBean userBean;// 登陆信息

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }


    /**
     * @param context
     * @param flag    为空时不弹框，适配锁定操作
     */
    public static void start(Context context, String flag) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("LOGOUT_MESSAGE", flag);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (context instanceof Application) {
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
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    protected void setListener() {
        inputPhoneEt.addTextChangedListener(textWatcher);
        vertifyEt.addTextChangedListener(textWatcher);
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                registTv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void keyBoardHide(int height) {
                registTv.setVisibility(View.VISIBLE);
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            changeState();
        }
    };

    private void changeState() {
        // 登录模式
        boolean isEnable = inputPhoneEt.getText().length() > 0
                && vertifyEt.getText().length() > 0;
        updateLoginBtn(LoginActivity.this, loginTv, isEnable);
    }

    private void updateLoginBtn(Context context, TextView tv_login, boolean isEnable) {
        if (isEnable) {
            tv_login.setBackground(context.getResources().getDrawable(R.drawable.login_btn_click_bg));
        } else {
            tv_login.setBackground(context.getResources().getDrawable(R.drawable.login_btn_bg));
        }
        tv_login.setEnabled(isEnable);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            //点击返回时如果是在短信验证码的页面就需要提示弹框
//            DialogCommonForActivity dialogCommonForActivity = DialogCommonForActivity.newInstance("确定返回？");
//            dialogCommonForActivity.show(supportFragmentManager, "validateCode");
//        } else {
//            super.onBackPressed();
//        }
        super.onBackPressed();
        ActivityCollector.removeAllActivity();
    }

    /**
     * 返回确定的回调
     *
     * @param v
     */
    @Override
    public void onBuutonClick(View v) {
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack();
        }
    }

    @Override
    protected boolean addOnReceive(Context context, Intent intent) {
        return true;
    }


    @OnClick({R.id.clear_iv, R.id.send_vertify_number_tv, R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_iv:
                inputPhoneEt.setText(null);
                stopTimer();
                break;
            case R.id.send_vertify_number_tv:
//                checkPhoneNum();
                MainActivity.start(getApplicationContext());
                finish();
                break;
            case R.id.login_tv:
                loginCient();
                break;
        }
    }

    /**
     * 检测手机号是否注册
     */
    private void checkPhoneNum() {
        if (!CheckHelper.checkPhoneNum(inputPhoneEt)) {
            return;
        }
        showLoadingDialog();
//        HttpHelper.sendMsgCode(TAG, inputPhoneEt.getText().toString().trim(), new JsonCallback<LzyResponse<String>>() {
//            @Override
//            public void onSuccess(LzyResponse<String> lzyResponse, Call call, Response response) {
//                if (lzyResponse == null) {
//                    return;
//                }
//                DialogMaker.dismissProgressDialog();
//                //未注册
//                if (lzyResponse.msg.equals(getString(R.string.phone_not_regist))) {
//                    ToastUtils.showShortToast(getString(R.string.un_register));
//                } else if (lzyResponse.msg.equals(getString(R.string.code_send_sucess))) {
//                    //已注册
//                    ToastUtils.showShortToast(R.string.code_alread_send);
//                    //开始倒计时
//                    startTimer();
//                } else {
//                    ToastUtils.showShortToast(lzyResponse.msg);
//                }
//            }
//
//            @Override
//            public void onError(Call call, Response response, Exception e) {
//                super.onError(call, response, e);
//                DialogMaker.dismissProgressDialog();
//                ToastUtils.showShortToast(e.getMessage());
//            }
//        });
    }

    private void startTimer() {
        sendVertifyNumberTv.setClickable(false);
        timess = GlobalKeyContans.SHORT_MESSAGE_TIME_INTERVAL;
        sendVertifyNumberTv.setText(timess + "s");
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timess--;
                            if (timess <= 0) {
                                stopTimer();
                                return;
                            }
                            sendVertifyNumberTv.setText(timess + "s");
                        }
                    });
                }
            };
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(timerTask, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        sendVertifyNumberTv.setText(getString(R.string.get_again));
        sendVertifyNumberTv.setClickable(true);
    }


    /**
     * 登陆
     * 15010687152  担保机构
     */
    private void loginCient() {
        if (!CheckHelper.checkPhoneNumLogin(inputPhoneEt)) {
            return;
        }
        showLoadingDialog();

//        HttpHelper.login(TAG, inputPhoneEt.getText().toString().trim(), vertifyEt.getText().toString().trim(),
//                new JsonCallback<LzyResponse<Object>>() {
//                    @Override
//                    public void onSuccess(LzyResponse<Object> lzyResponse, Call call, Response response) {
//                        dismissLoadingDialog();
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        dismissLoadingDialog();
//                    }
//                });
    }

    @Override
    protected void onDestroy() {
        stopTimer();
        super.onDestroy();
    }
}
