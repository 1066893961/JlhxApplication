package com.jlhx.payroll.application.base;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.utils.LogUtil;

import butterknife.BindView;

/**
 * Created by leo.li on 2017/5/10.
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView Webview;
    private String title;
    private String url;
//    private H5JsInterface h5CallBack;
    private boolean isRecFlag = false;
    private boolean isCollect = false;//是否收藏
    private long recId;
    private TextView textView;
//    private RecDetailInfoBean mRecDetailInfoBean;
    //收藏用的id
    private long collectId;

    public static void startActivity(Context context, String title, String url, boolean isRecFlag, long id) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("isRecFlag", isRecFlag);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String title, String url) {
        startActivity(context, title, url, false, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return title;
    }

    @Override
    protected View getRightView() {
//        if (isRecFlag) {
//            textView = ViewUtils.getRightTextViewWithImg(mActivity, "收藏");
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    setCollect(isCollect);
//                }
//            });
//            return textView;
//        } else {
            return null;
//        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
            url = intent.getStringExtra("url");
            isRecFlag = intent.getBooleanExtra("isRecFlag", false);
            recId = intent.getLongExtra("id", 0);
        }
        LogUtil.d(TAG, "URL:" + url);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings webSettings = Webview.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        Webview.setWebChromeClient(new WebChromeClient());
//        h5CallBack = new H5JsInterface(this);
//        Webview.addJavascriptInterface(h5CallBack, "AndroidJs");

    }

    @Override
    protected void setListener() {
        Webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && Webview.canGoBack()) {
                        Webview.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        Webview.loadUrl(url);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
