package com.jlhx.payroll.application.ui.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.utils.EditTextUtil;


/**
 * 密码输入框
 * add by lixinli 2017/01/20
 */
public class ShowPasswordEdittext extends LinearLayout {
    public static String regexPsw = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$";//"^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$"
	private static final int MAX_LENGTH = 30;	//内容最大长度
	private static final int MIN_LENGTH = 6;	//内容最小长度
	private boolean mIsShowingPassword;
	/**
	 * EditText component
	 */
	private EditText editText;
	private TextView tvLable;

	public EditText getEditText() {
		return editText;
	}

	/**
	 * Button that clears the EditText contents
	 */
	private ImageView showpasswordButton;
	private ImageView button_clear;
	private boolean  isShowClearButton,isShowEyeButton;	//显示密码时为一直显示，不隐藏

//	private View line;

	public ShowPasswordEdittext(Context context) {
		super(context);
		init(null);
	}

	public ShowPasswordEdittext(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);

	}

	public ShowPasswordEdittext(Context context, AttributeSet attrs, int defStyle) {
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
		inflater.inflate(R.layout.view_showpassword_edittext, this, true);

//		line = findViewById(R.id.v_bottonline);
		tvLable = (TextView)findViewById(R.id.tv_label);
		editText = (EditText) findViewById(R.id.et_showpassword);
		if (attrs != null) {
			TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginEdittext);
			editText.setInputType(attrsArray.getInt(R.styleable.LoginEdittext_android_inputType, InputType.TYPE_CLASS_TEXT));
			String text = attrsArray.getString(R.styleable.LoginEdittext_android_text);
			editText.setText(text);
			String label = attrsArray.getString(R.styleable.LoginEdittext_labelText);
			if(!TextUtils.isEmpty(label)){
				tvLable.setVisibility(View.VISIBLE);
				tvLable.setText(label);
			}
			//hint和text大小相同
//			SpannableString s = new SpannableString(getResources().getString(R.string.login_tel_hint));
			//缩小的hist
//			SpannableString s = new SpannableString(attrsArray.getString(R.styleable.LoginEdittext_android_hint));
//			AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(19,true);
//			s.setSpan(textSize,0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			editText.setHint(s);

			boolean enabled = attrsArray.getBoolean(R.styleable.LoginEdittext_android_enabled, true);
			isShowClearButton = attrsArray.getBoolean(R.styleable.LoginEdittext_showClearButton,false);
			isShowEyeButton = attrsArray.getBoolean(R.styleable.LoginEdittext_showEyeButton,true);
			String hint = attrsArray.getString(R.styleable.LoginEdittext_android_hint);
			editText.setHint(hint);
			Drawable drawable = attrsArray.getDrawable(R.styleable.LoginEdittext_android_drawableLeft);
			if (drawable != null) {
				editText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
			}
			attrsArray.recycle();
			if(!enabled){
				editText.setEnabled(false);
			}

		}
		addTextChangedListener();
		EditTextUtil.stopCopy(editText);
		// build clear button
		showpasswordButton = (ImageView) findViewById(R.id.button_show_pwd);
		showpasswordButton.setOnTouchListener(mOnTouchListener);
		//显示密码按钮效果为总是显示
		showpasswordButton.setVisibility(isShowEyeButton?View.VISIBLE:View.GONE);

		button_clear = (ImageView) findViewById(R.id.button_clear);
		button_clear.setVisibility(View.INVISIBLE);
		button_clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setText("");
			}
		});

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
//				if(isShowEyeButton){
//					showpasswordButton.setVisibility(hasFocus ? View.VISIBLE:View.INVISIBLE);
//				}
				if(isShowClearButton){
					String s = editText.getText().toString();
					button_clear.setVisibility((hasFocus && !TextUtils.isEmpty(s))?View.VISIBLE:View.INVISIBLE);
				}
//				line.setSelected(hasFocus);
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

	public void setOnTextEditorActionListener(TextView.OnEditorActionListener l){
		editText.setOnEditorActionListener(l);
	}

	/**
	 * Get value
	 * 
	 * @return text
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

	// my part
	OnTouchListener mOnTouchListener = new OnTouchListener() {

		private int mPreviousInputType;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			final int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				// mPreviousInputType = editText.getInputType();
				if (!mIsShowingPassword) {
					mIsShowingPassword = true;
					showpasswordButton.setImageResource(R.mipmap.icon_eye_open);
					setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD, true);
				} else {
					mIsShowingPassword = false;
					showpasswordButton.setImageResource(R.mipmap.icon_eye_close);
					setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD, true);
				}
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				// mIsShowingPassword = false;
				// setInputType(mPreviousInputType, true);
				// mPreviousInputType = -1;
				break;
			}

			return false;
		}
	};

	private void setInputType(int inputType, boolean keepState) {
		int selectionStart = -1;
		int selectionEnd = -1;
		if (keepState) {
			selectionStart = editText.getSelectionStart();
			selectionEnd = editText.getSelectionEnd();
		}
		editText.setInputType(inputType);
		if (keepState) {
			editText.setSelection(selectionStart, selectionEnd);
		}
	}

	/**
	 * EditText数值变化监听
	 */
	private TextWatcher mWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (s == null) {
				return ;
			}
//			gone和visible会引起UI的重新计算，invisible和visible不会
			if (s.length() > 0){
				if(isShowClearButton && hasFocus()){
					button_clear.setVisibility(View.VISIBLE);
				}
//				EditTextUtil.maxInput(MAX_LENGTH,editText);//通过maxLength限制最长20个字符
			}else{
				button_clear.setVisibility(View.INVISIBLE);
			}

			if(onTextChangeLister != null){
				onTextChangeLister.onTextChange(s);
			}
			if(onValidataLister != null){
				if(validata()){
					onValidataLister.onSuccess(s);
				}else{
					onValidataLister.onFails(s);
				}
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
//			LogUtil.d("inputText","ShowPasswordEdittext.afterTextChanged::"+s.toString());
		}
	};

	public interface OnTextChangeLister{
		void onTextChange(CharSequence s);
	}

	private OnTextChangeLister onTextChangeLister;

	public void setOnTextChangeLister(OnTextChangeLister onTextChangeLister) {
		this.onTextChangeLister = onTextChangeLister;
	}

	private OnValidataLister onValidataLister;

	public void setOnValidataLister(OnValidataLister onValidataLister) {
		this.onValidataLister = onValidataLister;
	}


	private OnTextFocusChangeListener onTextFocusChangeListener;

	public void setOnTextFocusChangeListener(OnTextFocusChangeListener onTextFocusChangeListener) {
		this.onTextFocusChangeListener = onTextFocusChangeListener;
	}

	/**
	 * 是否符合输入要求
	 * @return
	 */
	public boolean validata(){
		/*if(s != null && s.length() >= MIN_LENGTH){*///不判断是否小于最大长度是因为前面EditTextUtil.maxInput（）已经对最大长度进行了限制，字符不会超过MAX_LENGTH
		String text  = getText().toString().trim();
		if(!TextUtils.isEmpty(text) /*&& text.length() <= MAX_LENGTH && text.length() >= MIN_LENGTH*/){
			return true;
		}else{
			return false;
		}
	}
}