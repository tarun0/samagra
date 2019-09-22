package com.tarun.assignment;

import android.app.Application;
import android.content.Context;

import com.tarun.assignment.di.AppComponent;
import com.tarun.assignment.di.AppModule;
import com.tarun.assignment.di.DaggerAppComponent;
import com.tarun.assignment.di.UtilsModule;

public class App extends Application {
    AppComponent appComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

}
