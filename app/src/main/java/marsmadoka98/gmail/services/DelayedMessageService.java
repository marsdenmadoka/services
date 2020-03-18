package marsmadoka98.gmail.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class DelayedMessageService extends IntentService {
    //A STARTED SERVICE
    public static final String EXTRA_MESSAGE = "message"; //Use a constant to pass a message from the activity to the service.
    // private Handler handler;use this when you want to dislpay a toast
    public static final int NOTIFICATION_ID = 5453;

    public DelayedMessageService() {
        super("DelayedMessageService");//Call the super constructor.
    }
              //use this when you want to dislpay a toast
    //@Override
    //public int onStartCommand(Intent intent, int flags, int startId) {
    //  handler = new Handler();
    //return super.onStartCommand(intent, flags, startId);
    // }

    @Override
    protected void onHandleIntent(Intent intent) { //This method contains the code you want to run when the service receives an intent.
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);//Get the text from the intent
        showText(text);
    }
         //use this when you want to dislpay a toast
    // private void showText(final String text) {
    //   handler.post(new Runnable() {
    //     @Override
    //   public void run() {
    //     Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    //}
    //}); }
    //private void showText ( final String text){
    //  Log.v("DelayedMessageService", "The message is: " + text);
    //}
    private void showText(final String text) {
           //Create an intent activity which will be open when you click the notification
        Intent intent = new Intent(this, MainActivity.class);
       // Use a TaskStackBuilder to make the back button play nicely and These lines make the back button work properly when the activity is started..
        //TaskStackBuilder allows you to access the history of activities used by the back button.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        //Get the pending intent from the TaskStackBuilder
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT); //If a matching pending intent already exists, keep it and replace its extra data with the contents of the new intent.
          //Build the notification
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setContentText(text)
                .build();
           //Display the notification using the Android notification service.
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, notification);
        }


    }
}