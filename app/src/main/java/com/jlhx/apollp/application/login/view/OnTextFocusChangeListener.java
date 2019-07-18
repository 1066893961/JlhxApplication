package com.jlhx.apollp.application.login.view;

import android.view.View;

/**
 * textview焦点变化回调
 */
public interface OnTextFocusChangeListener {
    /**
     * textview焦点变化回调
     * @param v
     * @param hasFocus
     */
    void onTextFocusChange(View v, boolean hasFocus);
}