package idagio.amine.testapp.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import idagio.amine.testapp.MainActivity
import idagio.amine.testapp.R


class AlwaysRunningService : Service() {
    private val LOG_TAG = "AlwaysRunningService"

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when {
            intent.action == Constants.STARTFOREGROUND_ACTION -> handleStartService()
            intent.action == Constants.STOPFOREGROUND_ACTION  -> handleStopService()
        }
        return Service.START_STICKY
    }

    private fun handleStartService() {
        Log.i(LOG_TAG, "Received Start Foreground Intent ")

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.action = Constants.MAIN_ACTION
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = createNotification(pendingIntent)

        startForeground(Constants.FOREGROUND_SERVICE, notification)
    }

    private fun handleStopService() {
        Log.i(LOG_TAG, "Received Stop Foreground Intent")

        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(pendingIntent: PendingIntent?): Notification? {
        return NotificationCompat.Builder(this, "channel_simple_notifications")
            .setContentTitle("Notification Service")
            .setTicker("Notification Service")
            .setContentText("I am running")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LOG_TAG, "In onDestroy")
    }

    override fun onBind(intent: Intent): IBinder? {
        // Used only in case of bound services.
        return null
    }
}
