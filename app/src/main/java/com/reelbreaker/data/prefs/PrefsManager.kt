package com.reelbreaker.data.prefs

import android.content.Context
import androidx.core.content.edit

class PrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences("reelbreaker_prefs", Context.MODE_PRIVATE)

    fun isAllowedNow(now: Long = System.currentTimeMillis()): Boolean = now < prefs.getLong(KEY_ALLOWED_UNTIL, 0L)
    fun isSnoozed(now: Long = System.currentTimeMillis()): Boolean = now < prefs.getLong(KEY_SNOOZE_UNTIL, 0L)
    fun setAllowedUntil(timestamp: Long) = prefs.edit { putLong(KEY_ALLOWED_UNTIL, timestamp) }
    fun setSnoozeUntil(timestamp: Long) = prefs.edit { putLong(KEY_SNOOZE_UNTIL, timestamp) }

    fun getLastTaskIndex(): Int = prefs.getInt(KEY_LAST_TASK_INDEX, -1)
    fun setLastTaskIndex(index: Int) = prefs.edit { putInt(KEY_LAST_TASK_INDEX, index) }

    companion object {
        const val KEY_ALLOWED_UNTIL = "allowed_until"
        const val KEY_SNOOZE_UNTIL = "snooze_until"
        const val KEY_LAST_TASK_INDEX = "last_task_index"
    }
}
