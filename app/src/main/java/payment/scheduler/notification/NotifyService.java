package payment.scheduler.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.R;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.username.weddingplanning.MainActivity;


/**
 * Created by Sharu Vive on 4/30/2016.
 */
public class NotifyService extends Service {

    public class ServiceBinder extends Binder {
        NotifyService getService() {
            return NotifyService.this;
        }
    }

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "payment.scheduler.notification.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;

    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new ServiceBinder();

    private void showNotification() {
        CharSequence title = "Payment!!";
        int icon = R.drawable.ic_dialog_alert;
        CharSequence text = "You have scheduled payments.";
        long time = System.currentTimeMillis();

        Notification notification = new Notification(icon, text, time);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        notification.setLatestEventInfo(this, title, text, contentIntent);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        mNM.notify(NOTIFICATION, notification);

        stopSelf();
    }
}