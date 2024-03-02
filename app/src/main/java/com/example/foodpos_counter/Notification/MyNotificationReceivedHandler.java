package com.example.foodpos_counter.Notification;

import android.content.Context;
import android.util.Log;

import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MyNotificationReceivedHandler implements OneSignal.OSRemoteNotificationReceivedHandler {

    @Override
    public void remoteNotificationReceived(Context context, OSNotificationReceivedEvent notification) {
        JSONObject data = notification.getNotification().getAdditionalData();
        String customKey;

        if (data != null) {
            //While sending a Push notification from OneSignal dashboard
            // you can send an addtional data named "customkey" and retrieve the value of it and do necessary operation
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }
    }
}
