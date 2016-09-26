package com.aihook.quick.ui;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * @author: shun
 * @date: 2016-06-29 15:51
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        onInitView();
        onInitData();
    }

    protected abstract int getLayoutResource();
    protected abstract void onInitView();
    protected abstract void onInitData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
