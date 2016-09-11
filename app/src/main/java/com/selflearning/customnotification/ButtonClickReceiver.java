package com.selflearning.customnotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ButtonClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //If you click on the notification then it should disappear immediately.
        //So to cancel the notification you need to give notification id.
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //This is the same id that you have passed in your intent.
        manager.cancel(intent.getExtras().getInt("id"));
        Toast.makeText(context, "Notification button clicked.", Toast.LENGTH_SHORT).show();
    }
}
