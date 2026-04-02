package com.reelbreaker.features.blocking

import com.reelbreaker.data.prefs.PrefsManager

class SessionManager(private val prefs: PrefsManager) {
    fun isAllowed() = prefs.isAllowedNow()
    fun isSnoozed() = prefs.isSnoozed()
    fun allowForMinutes(minutes: Int) = prefs.setAllowedUntil(System.currentTimeMillis() + minutes * 60_000L)
    fun snoozeForMinutes(minutes: Int) = prefs.setSnoozeUntil(System.currentTimeMillis() + minutes * 60_000L)
}
