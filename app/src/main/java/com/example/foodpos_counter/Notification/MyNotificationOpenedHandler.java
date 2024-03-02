package com.example.foodpos_counter.Notification;

import android.content.Intent;
import android.util.Log;

import com.example.foodpos_counter.Menu_Activity;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by androidbash on 12/14/2016.
 */

public class MyNotificationOpenedHandler implements OneSignal.OSNotificationOpenedHandler {
    @Override
    public void notificationOpened(OSNotificationOpenedResult result) {

        OSNotificationAction.ActionType actionType = result.getAction().getType();
        JSONObject data = result.getNotification().getAdditionalData();
        String activityToBeOpened;
        String str;
        String str2;
        String id;

        if (data != null) {
            activityToBeOpened = data.optString("activityToBeOpened", null);
            str = data.optString("id", null);

            if (activityToBeOpened != null && activityToBeOpened.equals("New Offer")){
                Log.i("OneSignalExample", "customkey set with value: " + activityToBeOpened);
                Intent intent = new Intent(ApplicationClass.getContext(), Menu_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                ApplicationClass.getContext().startActivity(intent);
            }
            else {
                Intent intent = new Intent(ApplicationClass.getContext(), Menu_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                ApplicationClass.getContext().startActivity(intent);
            }
        }
    }

}


