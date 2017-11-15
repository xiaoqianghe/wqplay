package com.xiaoqianghe.wqplay.ui.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.widget.LoadingButton;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.layout.simple_list_item_1;

/**
 * Author：Wq
 * Date：2017/9/26 15:34
 * Description：//todo
 */

public class BluetoohActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.btn_togetbluetooh)
    LoadingButton btnTogetbluetooh;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.lv)
    ListView lv;

    @Override
    protected void init() {

        initEvent();

    }

    private void initEvent() {

        btnTogetbluetooh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BluetoothAdapter Ba = BluetoothAdapter.getDefaultAdapter();

                if (!Ba.isEnabled()) {

                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, 0);

                    Toast.makeText(getApplicationContext(), "turn on", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Already on",
                            Toast.LENGTH_LONG).show();
                }

//
//                Set<BluetoothDevice> bluetoothDevicess = Ba.getBondedDevices();
//
//                ArrayList list = new ArrayList();
//                for (BluetoothDevice bd : bluetoothDevicess) {
//                    list.add(bd.getName());
//                }
//
//                final ArrayAdapter adapter = new ArrayAdapter
//                        (this,android.R.layout.simple_list_item_1, list);
//                lv.setAdapter(adapter);

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_buletooh;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
