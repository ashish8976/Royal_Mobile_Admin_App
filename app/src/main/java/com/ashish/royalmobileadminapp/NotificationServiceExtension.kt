package com.ashish.royalmobileadminapp

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException
import android.graphics.BitmapFactory
import android.os.Build
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.itextpdf.io.exceptions.IOException
import com.itextpdf.io.exceptions.IoExceptionMessage
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler
import org.json.JSONObject
import java.net.URL

class NotificationServiceExtension : OSRemoteNotificationReceivedHandler {

    @SuppressLint("LongLogTag")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun remoteNotificationReceived(p0: Context?, notificationReceivedEvent: OSNotificationReceivedEvent?) {

        lateinit var data: JSONObject
        val notification = notificationReceivedEvent?.notification

        val mutableNotification = notification?.mutableCopy()
        mutableNotification?.setExtender { builder: NotificationCompat.Builder ->
            data = notification.additionalData
            val mBigPicture: String
            if (!TextUtils.isEmpty(data.optString("big_picture",null))) {
                mBigPicture = data.optString("big_picture",null)
                if (mBigPicture != null) {
                    try {
                        val url = URL(mBigPicture)
                        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        if (image != null) {
                            builder.setStyle(
                                NotificationCompat.BigPictureStyle().bigPicture(image)
                            )
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            builder
        }
        notificationReceivedEvent?.complete(mutableNotification)
    }
}