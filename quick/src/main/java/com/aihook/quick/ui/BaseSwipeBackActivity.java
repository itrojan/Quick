package com.aihook.quick.ui;

import android.os.Bundle;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author: iTrojan
 * @date: 2016-06-29 15:51
 * @GitHub: https://github.com/itrojan
 */
public abstract class BaseSwipeBackActivity extends SwipeBackActivity {

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
