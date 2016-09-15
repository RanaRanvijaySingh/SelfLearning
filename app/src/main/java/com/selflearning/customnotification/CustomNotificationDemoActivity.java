package com.selflearning.customnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.selflearning.MainActivity;
import com.selflearning.R;

public class CustomNotificationDemoActivity extends AppCompatActivity {

    private static final String INTENT_TO_LISTEN = "button_click";
    private static final String NOTIFICATION_ID = "id";
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notificationId;
    private RemoteViews remoteViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_notification_demo);
        initializeNotificationComponents();
    }

    private void initializeNotificationComponents() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        //To change image view
        remoteViews.setImageViewResource(R.id.ivImage, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.tvTitle, "Custom title");
        remoteViews.setTextViewText(R.id.tvDescription, "Custom description");

        //For progress bar
//        remoteViews.setProgressBar(R.id.pb, 100, 50, true);

        //For button you cannot directly get he click event
        //You will get it in a broadcast receiver class using your own pending intent.
        Intent btnIntent = new Intent(INTENT_TO_LISTEN);
        notificationId = (int) System.currentTimeMillis();
        btnIntent.putExtra(NOTIFICATION_ID, notificationId);

        //Now you have the intent ready.
        //You need to broadcast this intent so that your receiver can listen to it.
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, btnIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btnLaunchScreen, pendingIntent);
    }

    /**
     * Function to trigger a notification
     *
     * @param view View
     */
    public void onClickTriggerNotificationButton(View view) {
        Intent notification_intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notification_intent, 0);

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setCustomBigContentView(remoteViews)
                .setContentIntent(pendingIntent);
        notificationManager.notify(notificationId, builder.build());
    }
}
