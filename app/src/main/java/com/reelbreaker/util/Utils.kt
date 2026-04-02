package com.reelbreaker.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    const val ALLOW_MINUTES = 5
    const val SNOOZE_MINUTES = 10
}

object PermissionUtils {
    fun openAccessibilitySettings(activity: Activity) {
        activity.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
    }
}

object TimeUtils {
    private val formatter = SimpleDateFormat("MMM dd, HH:mm", Locale.US)
    fun format(ts: Long): String = formatter.format(Date(ts))
}

object Logger {
    fun d(tag: String, msg: String) = Log.d(tag, msg)
}

object UIHelper {
    fun toast(context: Context, msg: String) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
