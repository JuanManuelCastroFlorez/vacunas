package unipiloto.edu.co.vacunas.ui.services;

import android.app.*;
import android.content.Intent;

import android.os.Build;
import android.support.v4.app.*;

import androidx.core.app.NotificationCompat;

import unipiloto.edu.co.vacunas.R;
import unipiloto.edu.co.vacunas.ui.activities.citaActivity;

public class DelayedMessageService extends IntentService {
    public static final String EXTRA_MESSAGE= "message";
    public static final int NOTIFICATION_ID=5453;
    private static final String CHANNEL_ID = "esto";

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
                wait(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        String text= intent.getStringExtra(EXTRA_MESSAGE);
        createNotificationChannel();
        showText(text);
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showText(final String text) {
        //Create a notification builder'
        NotificationCompat.Builder builder=
                new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setContentTitle(getString(R.string.question))
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setVibrate(new long[]{0,1000})
                        .setAutoCancel(true);

        //Create an action
        Intent actionIntent= new Intent(this, citaActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(actionPendingIntent);

        //Issue the notification
        NotificationManager notificationManager=
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }
}
