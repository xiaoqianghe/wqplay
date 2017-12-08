package com.xiaoqianghe.wqplay.ui.Fragment;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.xiaoqianghe.wqplay.di.component.AppComponent;
//
//
//import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
//import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
//import com.xiaoqianghe.wqplay.di.module.AppInfoModule;
import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
import com.xiaoqianghe.wqplay.di.module.AppInfoModule;
import com.xiaoqianghe.wqplay.presenter.AppInfoPresenter;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;

/**
 * Author：Wq
 * Date：2017/6/30 16:17
 * Description：//todo
 */

public class TopListFragment extends BaseAppInfoFragment {
    @Override
    AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).build();
    }

    @Override
    protected int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
//        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);

//        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);

        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);
    }



    /**
     * 银行卡四位加空格
     *
     * @param mEditText
     */
    public static void bankCardNumAddSpace(final EditText mEditText) {
        //6214 8365 5651 3691
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3
                        || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }
                    mEditText.setText(str);
                    Editable etable = mEditText.getText();


                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }
}
