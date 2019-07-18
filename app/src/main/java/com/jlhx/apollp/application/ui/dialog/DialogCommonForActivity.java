package com.jlhx.apollp.application.ui.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.login.view.OnButtonClickListener;

/**
 * 通用弹框
 */
public class DialogCommonForActivity extends DialogFragment {
    private static final String ARG_CONTENT = "param1";
    private static final String ARG_CONTENT_LEFTSTR = "leftStr";
    private static final String ARG_CONTENT_RIGHTSTR = "rightStr";

    @Deprecated
    private OnButtonClickListener mOnConfirmClickListener;
    private OnConfirmWithTagListener mOnConfirmWithTagListener;
    private OnDialogCancelListener mOnCancelListener;
    private int widthPadding;

    private String mContentStr, mLeftStr, mRightStr;


    public static DialogCommonForActivity newInstance(String content) {
        return newInstance(content, null, null);
    }

    public static DialogCommonForActivity newInstance(String content, String leftStr, String rightStr) {
        DialogCommonForActivity fragment = new DialogCommonForActivity();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        args.putString(ARG_CONTENT_LEFTSTR, leftStr);
        args.putString(ARG_CONTENT_RIGHTSTR, rightStr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity instanceof OnButtonClickListener) {
                mOnConfirmClickListener = (OnButtonClickListener) activity;
            }
            if (activity instanceof OnConfirmWithTagListener) {
                mOnConfirmWithTagListener = (OnConfirmWithTagListener) activity;
            }
            if (activity instanceof OnDialogCancelListener) {
                mOnCancelListener = (OnDialogCancelListener) activity;
            }
        }
        widthPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());

        if (getArguments() != null) {
            mContentStr = getArguments().getString(ARG_CONTENT);
            mLeftStr = getArguments().getString(ARG_CONTENT_LEFTSTR);
            mRightStr = getArguments().getString(ARG_CONTENT_RIGHTSTR);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
//            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
//            params.windowAnimations = R.style.pop_bottom_animation;
//            getDialog().getWindow().setAttributes(params);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.dialog_logout, container);
        TextView textView = (TextView) view.findViewById(R.id.tv_content);
        textView.setText(mContentStr);
        Button cancel = (Button) view.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnCancelListener != null){
                    mOnCancelListener.onCancel(v,getTag());
                }
                dismiss();
            }
        });
        Button confirm = (Button) view.findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnConfirmClickListener != null) {
                    mOnConfirmClickListener.onBuutonClick(v);
                }
                if (mOnConfirmWithTagListener != null) {
                    mOnConfirmWithTagListener.onButtonClick(v, getTag());
                }
            }
        });
        if(!TextUtils.isEmpty(mLeftStr)){
            cancel.setText(mLeftStr);
        }
        if(!TextUtils.isEmpty(mRightStr)){
            confirm.setText(mRightStr);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(getResources().getDisplayMetrics().widthPixels - 2 * widthPadding, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}