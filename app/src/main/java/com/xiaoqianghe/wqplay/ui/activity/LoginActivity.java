package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerLoginComponent;
import com.xiaoqianghe.wqplay.di.module.LoginModule;
import com.xiaoqianghe.wqplay.presenter.LoginPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.LoginContract;
import com.xiaoqianghe.wqplay.ui.widget.LoadingButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Author：Wq
 * Date：2017/8/18 11:55
 * Description：//todo
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;

    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;






    @Override
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机号格式不正确");

    }

    @Override
    public void checkoutPhoneSuccess() {
        viewMobiWrapper.setError("");
        viewMobiWrapper.setErrorEnabled(false);

    }

    @Override
    public void loginSuccess(LoginBean mBean) {
        this.finish();
    }

    @Override
    public void showLoading() {

        btnLogin.showLoading();

    }

    @Override
    public void dismissLoading() {
        btnLogin.showButtonText();

    }

    @Override
    public void showError(String str) {
        btnLogin.showButtonText();

    }

    @Override
    protected void init() {

        initView();

    }

    private void initView() {

//
        toolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );



        Observable<CharSequence> obMobi = RxTextView.textChanges(txtMobi);
        Observable<CharSequence> obPassword = RxTextView.textChanges(txtPassword);

        Observable.combineLatest(obMobi, obPassword, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mobi, CharSequence pwd) {
                return isPhoneValid(mobi.toString()) && isPasswordValid(pwd.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {

                RxView.enabled(btnLogin).call(aBoolean);
            }
        });



        RxView.clicks(btnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                mPresenter.login(txtMobi.getText().toString().trim(),txtPassword.getText().toString().trim());


            }
        });



    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this)).build().inject(this);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }




}
