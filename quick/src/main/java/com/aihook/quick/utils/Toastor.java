package com.aihook.quick.utils;

import android.content.Context;
import android.widget.Toast;

import com.aihook.quick.MainApp;

/**
 * @author: shun
 * @date: 2016-06-29 16:19
 */
public class Toastor {

    private Toast mToast;
    private Context mContext;

    public Toastor() {
        mContext = MainApp.getContext();
    }

    public Toast getShortToast(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        return mToast;
    }

    public Toast getShortToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        return mToast;
    }

    public Toast getToast(int resId) {
        return Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
    }

    public Toast getToast(String text) {
        return Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
    }

    public Toast getLongToast(int resId) {
        return Toast.makeText(mContext, resId, Toast.LENGTH_LONG);
    }

    public Toast getLongToast(String text) {
        return Toast.makeText(mContext, text, Toast.LENGTH_LONG);
    }

    public void showShortToast(int resId) {
        getShortToast(resId).show();
    }

    public void showShortToast(String text) {
        getShortToast(text).show();
    }

    public void showToast(int resId) {
        getToast(resId).show();
    }

    public void showToast(String text) {
        getToast(text).show();
    }

    public void showLongToast(int resId) {
        getLongToast(resId).show();
    }

    public void showLongToast(String text) {
        getLongToast(text).show();
    }
}
