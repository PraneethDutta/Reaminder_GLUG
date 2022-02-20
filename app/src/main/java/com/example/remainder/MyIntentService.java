package com.example.remainder;

import android.app.IntentService;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;

public class MyIntentService extends IntentService {



    public static final int NOTIFICATION_ID=12345;
    public static int time;
    public static String NameOfRemainder;
    public static void setTime(int timer){
        time =timer;
    }


    public static void setNameofRemainder(String nameofRemainder) {
        NameOfRemainder = nameofRemainder;
    }



    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized(this){
            try{
                wait(time);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        String text=intent.getStringExtra(NameOfRemainder);
        showText(text);
    }
    private void showText(final String text){
        //creating a notification builder

        NotificationCompat.Builder builder=new NotificationCompat.Builder(MyIntentService.this,"My NOTIFICATION");
                builder.setSmallIcon(android.R.drawable.sym_def_app_icon);
                builder.setContentTitle("REMAINDER");
                builder.setContentText(NameOfRemainder);
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                builder.setVibrate(new long[]{0,5000});
                builder.setAutoCancel(true);
                //builder.setSound()
        //Issue the notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
                //creating an action for the notification
        Intent actionIntent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(actionPendingIntent);


    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}