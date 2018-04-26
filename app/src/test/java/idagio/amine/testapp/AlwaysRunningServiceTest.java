package idagio.amine.testapp;

import android.app.Notification;
import android.app.NotificationManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowNotificationManager;
import org.robolectric.shadows.ShadowService;

import idagio.amine.testapp.service.AlwaysRunningService;
import idagio.amine.testapp.service.Constants;

import static android.content.Context.NOTIFICATION_SERVICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class AlwaysRunningServiceTest {
    private AlwaysRunningService service;
    private ShadowService shadowService;
    private Notification.Builder notificationBuilder;

    private final ShadowNotificationManager shadowNotificationManager = shadowOf((NotificationManager) RuntimeEnvironment.application.getSystemService(NOTIFICATION_SERVICE));

    @Before
    public void setup() {
        service = Robolectric.setupService(AlwaysRunningService.class);
        shadowService = shadowOf(service);
        notificationBuilder = new Notification.Builder(service)
                .setSmallIcon(1)
                .setContentTitle("Test")
                .setContentText("Hi there");
    }

    //we are testing if we get the same notification as the one we started from a service
    @Test
    public void startForeground() {
        Notification notification = notificationBuilder.build();
        service.startForeground(Constants.FOREGROUND_SERVICE, notification);
        assertThat(shadowService.getLastForegroundNotification()).isSameAs(notification);
        assertThat(shadowService.getLastForegroundNotificationId()).isEqualTo(Constants.FOREGROUND_SERVICE);
        assertThat(shadowNotificationManager.getNotification(Constants.FOREGROUND_SERVICE)).isSameAs(notification);
        assertThat(notification.flags & Notification.FLAG_FOREGROUND_SERVICE).isNotZero();
    }

    //we are testing if we successfully stopped the service
    @Test
    public void stopForeground() {
        service.stopForeground(true);
        assertThat(shadowService.isForegroundStopped()).isTrue();
    }

    //we are testing if we delete the notification when stopping the service
    @Test
    public void onStopForegroundRemovesNotification() {
        Notification notification = notificationBuilder.build();
        service.startForeground(Constants.FOREGROUND_SERVICE, notification);
        service.stopForeground(true);
        assertThat(shadowNotificationManager.getNotification(Constants.FOREGROUND_SERVICE)).isNull();
    }

}
