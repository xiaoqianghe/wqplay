package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoqianghe.wqplay.R;

/**
 * Author：Wq
 * Date：2017/6/9 16:46
 * Description：//todo
 */

public class GuiderFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_ranking,container,false);
        return view;
    }
}
