package com.jlhx.apollp.application.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlhx.apollp.application.R;
import com.jlhx.apollp.application.utils.EditTextUtil;


/**
 * 用户名输入框
 * add by lixinli 2017/01/20
 * 这个是以前项目的控件，好多功能都没有用了，但是没有完全注释掉，这里用浪费了
 */
public class TelInputLayout extends LinearLayout {
	private static final int MAX_LENGTH = 13;	//内容最大长度,,,,和xml文件里面设置一起改
	private EditText editText;

	/**
	 * Button that clears the EditText contents
	 */
//	private ImageView showpasswordButton;
	private ImageView button_clear;
	private boolean  isShowClearButton;

	private TextView tvLable;
	private View line;


	public EditText getEditText() {
		return editText;
	}

	public TelInputLayout(Context context) {
		super(context);
		init(null);
	}

	public TelInputLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);

	}

	public TelInputLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	/**
	 * Initialize view
	 * 
	 * @param attrs
	 */
	private void init(AttributeSet attrs) {

		// inflate layout
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_tel_input_layout, this, true);

		line = findViewById(R.id.v_bottonline);
		tvLable = (TextView)findViewById(R.id.tv_label);
		editText = (EditText) findViewById(R.id.edittext);
		if (attrs != null) {
			TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginEdittext);
//			editText.setInputType(attrsArray.getInt(R.styleable.LoginEdittext_android_inputType, InputType.TYPE_CLASS_TEXT));

			//hint和text大小相同
//			SpannableString s = new SpannableString(getResources().getString(R.string.login_tel_hint));
			//缩小的hist
//			SpannableString s = new SpannableString(attrsArray.getString(R.styleable.LoginEdittext_android_hint));
//			AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(19,true);
//			s.setSpan(textSize,0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			editText.setHint(s);
			String hint = attrsArray.getString(R.styleable.LoginEdittext_android_hint);
			editText.setHint(hint);
			String text = attrsArray.getString(R.styleable.LoginEdittext_android_text);
			editText.setText(text);
			String label = attrsArray.getString(R.styleable.LoginEdittext_labelText);
			if(!TextUtils.isEmpty(label)){
				tvLable.setVisibility(View.VISIBLE);
				tvLable.setText(label);
			}
//			editText.setHint(attrsArray.getString(R.styleable.LoginEdittext_android_hint));

			isShowClearButton = attrsArray.getBoolean(R.styleable.LoginEdittext_showClearButton,false);

			Drawable drawable = attrsArray.getDrawable(R.styleable.LoginEdittext_android_drawableLeft);
			if (drawable != null) {
				editText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
			}
			int anInt = attrsArray.getInt(R.styleable.LoginEdittext_android_imeOptions, EditorInfo.IME_NULL);
			editText.setImeOptions(anInt);
			addTextChangedListener();
			attrsArray.recycle();
		}
		EditTextUtil.stopCopy(editText);

		// build clear button
		button_clear = (ImageView) findViewById(R.id.iv_clear);
		button_clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setText("");
				if(onClearButtonClickListener != null){
					onClearButtonClickListener.onBuutonClick(v);
				}
			}
		});


		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(isShowClearButton){
					String s = editText.getText().toString();
					button_clear.setVisibility((hasFocus && !TextUtils.isEmpty(s))?View.VISIBLE:View.INVISIBLE);
				}
				line.setSelected(hasFocus);
				if(onTextFocusChangeListener != null){
					onTextFocusChangeListener.onTextFocusChange(v,hasFocus);
				}
			}
		});
	}

	private void addTextChangedListener() {
//		removeTextChangedListener();
		editText.addTextChangedListener(mWatcher);
	}
//	private void removeTextChangedListener() {
//		editText.removeTextChangedListener(mWatcher);
//	}

	public void setTextGravityCenter(){
		editText.setGravity(Gravity.CENTER);
	}

	/**
	 * 获取电话号码
	 * 
	 * @return 去掉空格之后的电话号码
	 */
	public String getTel() {
		Editable text = editText.getText();
		String s = text.toString().trim();
		return s.replace(" ","");
	}

    /**
     * get Text value
	 * @return
     */
	public Editable getText() {
		return editText.getText();
	}

	/**
	 * Set value
	 * 
	 * @param text
	 */
	public void setText(CharSequence text) {
		editText.setText(text);
	}

	/**
	 * 是否符合输入要求
	 * @return
     */
	public boolean validata(){
		//text.length() >= MAX_LENGTH使用大于等于最大长度是因为当字符位数达到最大长度时输入字符会通过代码控制减去多的部分，但是TextWatcher的回调还是会走完原始字符长度的回调
		String text  = getText().toString().trim();
		if(!TextUtils.isEmpty(text) ){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * EditText数值变化监听
	 */
	private TextWatcher mWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
//			if (TextUtils.isEmpty(s)) {
//				return ;
//			}
			if (s.length() > 0){
				if(isShowClearButton && hasFocus()){
					button_clear.setVisibility(View.VISIBLE);
				}
//				EditTextUtil.maxInput(MAX_LENGTH,editText);
			}else{
				button_clear.setVisibility(View.INVISIBLE);
			}

			if(onValidataLister != null){
//				String text = getTel();
//				if(PhoneFormatCheckUtil.isPhoneLegal(text)){
//					onValidataLister.onSuccess(text);
//
//				}else{
//					onValidataLister.onFails(text);
//
//				}
				String text  = s.toString().trim();
				if(validata()){
					onValidataLister.onSuccess(text);
				}else{
					onValidataLister.onFails(text);
				}
			}

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};


	private OnValidataLister onValidataLister;

	public void setOnValidataLister(OnValidataLister onValidataLister) {
		this.onValidataLister = onValidataLister;
	}

	private OnTextFocusChangeListener onTextFocusChangeListener;

	public void setOnTextFocusChangeListener(OnTextFocusChangeListener onTextFocusChangeListener) {
		this.onTextFocusChangeListener = onTextFocusChangeListener;
	}

	public OnButtonClickListener onClearButtonClickListener;

	public void setOnClearButtonClickListener(OnButtonClickListener onClearButtonClickListener) {
		this.onClearButtonClickListener = onClearButtonClickListener;
	}
}