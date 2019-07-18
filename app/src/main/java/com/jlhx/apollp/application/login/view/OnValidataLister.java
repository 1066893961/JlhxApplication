package com.jlhx.apollp.application.login.view;

/**
 * 包含验证成功或者失败的回调接口
 */
public interface OnValidataLister {
    /**
	 * 验证成功
	 * @param s
     */
	void onSuccess(CharSequence s);

	/**
	 * 验证失败
	 * @param s
     */
	void onFails(CharSequence s);
}