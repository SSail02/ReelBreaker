package com.reelbreaker

import android.app.Application
import com.reelbreaker.data.local.AppDatabase
import com.reelbreaker.data.prefs.PrefsManager
import com.reelbreaker.data.repository.AppRepository

class ReelBreakerApp : Application() {
    val prefs by lazy { PrefsManager(this) }
    val database by lazy { AppDatabase.get(this) }
    val repository by lazy { AppRepository(database, prefs) }
}
