package com.aihook.quick.app;

import android.os.Bundle;

import com.aihook.quick.ui.BaseActivity;
import com.aihook.quick.utils.Toastor;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private Toastor toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = new Toastor();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInitData() {

    }

    @OnClick(R.id.email_sign_in_button)
    void login() {
        toast.showShortToast(String.valueOf(Math.random()));
//        ToastUtil.show(String.valueOf(Math.random()));
    }

}
