package github.hellocsl.ucmainpager;

import android.app.Application;
import android.content.Context;

/**
 * Created by chensuilun on 16/7/24.
 */
public class DemoApplication extends Application {
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext=this;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
