package com.example.master

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.support.v7.app.NotificationCompat


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //you might want to check what's inside the Intent
        if (intent.getStringExtra("myAction") != null && intent.getStringExtra("myAction").equals("mDoNotify")) {
            val mNotifyMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    //example for large icon
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("my title")
                    .setContentText("my message")
                    .setOngoing(false)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
            // example for blinking LED
            mBuilder.setLights(-0x48e3e4, 1000, 2000)
            mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
            mNotifyMgr.notify(12345, mBuilder.build())
        }

    }
}