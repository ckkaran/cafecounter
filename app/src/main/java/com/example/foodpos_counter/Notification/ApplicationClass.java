package com.example.foodpos_counter.Notification;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

public class ApplicationClass extends Application {

    private static final String ONESIGNAL_APP_ID = "a4d74957-c8ce-4911-8974-f02df7903ab9";

    private static ApplicationClass mInstance;
    private static Context context;
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = getApplicationContext();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.setNotificationOpenedHandler(new MyNotificationOpenedHandler());
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

    public static synchronized ApplicationClass getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
