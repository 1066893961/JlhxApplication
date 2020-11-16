package com.jlhx.payroll.application.utils;

import android.graphics.Paint;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lixinli on 2017/1/22.
 */
public class EditTextUtil {

    /**
     * 最大输入长度限制
     *
     * @param maxLength
     * @param editText
     */
    public static void maxInput(int maxLength, EditText editText) {
        if (editText == null || maxLength < 1) {
            return;
        }

        Editable editable = editText.getText();
        int length = editable.length();//原字符串长度
        if (length > maxLength) {//如果原字符串长度大于最大长度
            int selectEndIndex = Selection.getSelectionEnd(editable);//getSelectionEnd：获取光标结束的索引值
            String str = editable.toString();//旧字符串
            String newStr = str.substring(0, maxLength);//截取新字符串
            editText.setText(newStr);
            editable = editText.getText();
            int newLength = editable.length();//新字符串长度
            if (selectEndIndex > newLength) {//如果光标结束的索引值超过新字符串长度
                selectEndIndex = editable.length();
//				Toast.makeText(context, "最多只能输入" + selectEndIndex + "个字哦", Toast.LENGTH_SHORT).show();
            }
            Selection.setSelection(editable, selectEndIndex);//设置新光标所在的位置
        }
    }

    /**
     * 屏蔽复制、粘贴功能
     * @param mInputEditTxt
     */
    public static void stopCopy(EditText mInputEditTxt) {

        mInputEditTxt.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                return false;

            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {

                return false;

            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                return false;

            }

            @Override

            public void onDestroyActionMode(ActionMode mode) {

            }

        });

        mInputEditTxt.setLongClickable(false);

    }

    /**
     * 设置字体加粗，但是比较纤细的那种粗 ^_^
     * @param tv
     */
    public static void setBoldStyle(TextView tv){
        if(tv == null){
            return ;
        }
        //设置title字体加粗，但是比较纤细的那种粗 ^_^
        tv.setPaintFlags(Paint.ANTI_ALIAS_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        tv.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        tv.getPaint().setFakeBoldText(true);
    }

    /**
     * 2位小数
     * 以0开头时第二位是“.”,如果不是舍弃0
     * @param s
     * @param editText
     * @return  如果字符过长被剪短了就返回true
     */
    public static boolean amountFormat(CharSequence s, EditText editText){
        return amountFormat(s,editText,2);
    }

    /**
     *
     * @param s
     * @param editText
     * @param decimals  指定小数点后几位
     * @return
     */
    public static boolean amountFormat(CharSequence s, EditText editText,int decimals){
        if(editText == null || TextUtils.isEmpty(s)){
            return false;
        }
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > decimals) {
                s = s.toString().subSequence(0,s.toString().indexOf(".") + decimals+1);
                editText.setText(s);
                editText.setSelection(s.length());
                return true;
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (s.toString().charAt(1) != '.') {
//                editText.setText(s.subSequence(0, 1));
//            }else{
                editText.setText(s.subSequence(1, s.length()));
                Editable text = editText.getText();
                editText.setSelection(text.length());
            }
        }
        return false;
    }

}
