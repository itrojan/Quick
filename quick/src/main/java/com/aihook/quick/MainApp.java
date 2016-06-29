package com.aihook.quick;

import android.app.Application;
import android.content.Context;

/**
 * @author: iTrojan
 * @date: 2016-06-29 15:51
 * @GitHub: https://github.com/itrojan
 */
public class MainApp extends Application {

    private static MainApp ourInstance = new MainApp();
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        mContext = this.getApplicationContext();
    }

    public static MainApp getInstance() {
        return ourInstance;
    }

    public static Context getContext(){
        return mContext;
    }
}
