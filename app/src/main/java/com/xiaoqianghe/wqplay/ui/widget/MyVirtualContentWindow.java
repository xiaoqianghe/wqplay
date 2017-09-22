package com.xiaoqianghe.wqplay.ui.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiaoqianghe.wqplay.R;



/**
 * @包名: com.lnyp.pswkeyboard.widget
 * @类名: MyVirtualContentWindow
 * @创建人: xiaoqianghe
 * @创建时间 : 2016/12/28 14:48
 * @描述 : TODO
 */

public class MyVirtualContentWindow extends PopupWindow {
    private String TAG="MyVirtualContentWindow";
    private EditText textAmount;
    private Context mConetext;

    private Animation enterAnim;
    private Animation exitAnim;
    private PopupWindow mPopWindow;
    private int width,height;
    private LinearLayout linear_pass;

    private String inputContent;
    private TextView tvContent;

    public MyVirtualContentWindow(Context context, EditText et) {
        super(context);
        this.textAmount=et;
        this.mConetext=context;
        initPubWindow();
    }

    public MyVirtualContentWindow(Context context) {
        super(context);
        this.mConetext=context;
        initPubWindow();
    }

    private void initPubWindow() {
        View view = View.inflate(mConetext, R.layout.wiidght_content, null);
        if (width == 0 || height == 0) {
            mPopWindow = new PopupWindow(view);
        } else {
            mPopWindow = new PopupWindow(view, width, height, true);
        }
        tvContent= (TextView) view.findViewById(R.id.tv_cntent);
        inputContent=textAmount.getText().toString().trim();
        tvContent.setText(spaceAt4(inputContent));

    }
    public static String spaceAt4(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i += 4) {
            if (length - i <= 8) {      //防止ArrayIndexOutOfBoundsException
                sb.append(str.substring(i, i + 4)).append(" ");
                sb.append(str.substring(i + 4));
                break;
            }
            sb.append(str.substring(i, i + 4)).append(" ");
        }
        return sb.toString();
    }


    protected void updataData(EditText  et){
        inputContent=et.getText().toString().trim();
        tvContent.setText(spaceAt4(inputContent));
    }





}
