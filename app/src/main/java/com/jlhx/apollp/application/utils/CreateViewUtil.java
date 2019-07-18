package com.jlhx.apollp.application.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.DrawableRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlhx.apollp.application.R;

/**
 * Created by lixinli on 2018/11/30.
 */
public class CreateViewUtil {

    public static TextView getToolbarRightTextButton(Context context, View.OnClickListener onClickListener){
        return getToolbarRightTextButton(context,"完成",onClickListener);
    }

    public static TextView getToolbarRightTextButton(Context context,CharSequence text, View.OnClickListener onClickListener){
        TextView tv = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics());
        tv.setLayoutParams(layoutParams);
        tv.setPadding(0,0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics()),0);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
//        tv.setTextColor(context.getResources().getColor(R.color.color_blue_text));
//        tv.setDuplicateParentStateEnabled(true);//状态同步但是点击响应不同步，所以放弃这种方法
        setSelectorColor(tv,0xff4b88f9,0xff999999);
        tv.setTextSize(17);
        tv.setOnClickListener(onClickListener);
        return tv;
    }
    public static ImageView getToolbarRightImageButton(Context context,@DrawableRes int res, View.OnClickListener onClickListener){
//        <ImageView
//        android:id="@+id/iv_right"
//        android:layout_width="@dimen/dp_40"
//        android:layout_height="@dimen/dp_40"
//        android:layout_alignParentRight="true"
//        android:layout_centerVertical="true"
//        android:layout_marginRight="5dp"
//        android:scaleType="centerInside"
//        android:src="@mipmap/icon_add_blue" />
        ImageView tv = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DipUtils.dip2px(context,40), ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics());
        tv.setLayoutParams(layoutParams);
        tv.setPadding(0,0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,context.getResources().getDisplayMetrics()),0);
        tv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        tv.setImageResource(res);
        tv.setOnClickListener(onClickListener);
        return tv;
    }

    public static TextView getToolbarLeftTextButton(Context context){
        TextView tv = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics());
        tv.setLayoutParams(layoutParams);
        tv.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics()),0,0,0);
        tv.setGravity(Gravity.CENTER);
        tv.setText("取消");
        tv.setTextColor(context.getResources().getColor(R.color.color_333333));
        tv.setTextSize(17);
        return tv;
    }

    /**
     * 获取占位空数据布局
     * @param context   context
     * @param res       无网络，无数据等图片
     * @param tips      无网络，无数据等对应文字
     * @return
     */
    public static View getEmptyView(Context context, @DrawableRes int res, String tips){
        View view = View.inflate(context, R.layout.layout_empty, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        iv.setImageResource(res);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(tips);

//        LinearLayout view  = new LinearLayout(context);
//        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        view.setGravity(Gravity.CENTER);
//        view.setOrientation(LinearLayout.VERTICAL);
//
//        ImageView iv = new ImageView(context);
//        LinearLayout.LayoutParams ivlayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ivlayoutParams.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,72,context.getResources().getDisplayMetrics());
//        iv.setLayoutParams(ivlayoutParams);
//        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        iv.setImageResource(R.mipmap.rule_bg_nodata);
//        view.addView(iv);
//
//        TextView tv = new TextView(context);
//        LinearLayout.LayoutParams tvlayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        tvlayoutParams.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,context.getResources().getDisplayMetrics());
//        tv.setLayoutParams(tvlayoutParams);
//        tv.setText(tips);
//        view.addView(tv);

        return view ;
    }

    /**
     * 设置enable两种状态的颜色
     * @param tv
     * @param enable
     * @param disable
     */
    public static void setSelectorColor(TextView tv, int enable, int disable){
        int[] colors =new int[] { disable,enable, enable};
        int[][] states =new int[3][];
        states[0] =new int[] { -android.R.attr.state_enabled};  //false
        states[1] =new int[] { android.R.attr.state_enabled};   //true
        states[2] =new int[] {};
        ColorStateList colorStateList =new ColorStateList(states,colors);
        tv.setTextColor(colorStateList);
    }
}
