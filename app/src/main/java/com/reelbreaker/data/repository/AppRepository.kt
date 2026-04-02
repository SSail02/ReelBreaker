package com.reelbreaker.data.repository

import com.reelbreaker.data.local.AppDatabase
import com.reelbreaker.data.local.BlockEventEntity
import com.reelbreaker.data.local.MindfulTaskLogEntity
import com.reelbreaker.data.local.UsageEntity
import com.reelbreaker.data.prefs.PrefsManager
import kotlinx.coroutines.flow.Flow

class AppRepository(private val db: AppDatabase, val prefs: PrefsManager) {
    val totalBlocked: Flow<Int> = db.blockEventDao().totalBlocked()
    val completedTasks: Flow<Int> = db.mindfulTaskDao().countByOutcome("DONE")

    suspend fun logTask(task: String, category: String, outcome: String) {
        db.mindfulTaskDao().insert(MindfulTaskLogEntity(timestamp = System.currentTimeMillis(), taskText = task, category = category, outcome = outcome))
    }

    suspend fun logBlock(appName: String, reason: String) {
        db.blockEventDao().insert(BlockEventEntity(timestamp = System.currentTimeMillis(), appName = appName, reason = reason))
    }

    suspend fun logUsage(metric: String, value: Int) {
        db.usageDao().insert(UsageEntity(timestamp = System.currentTimeMillis(), metric = metric, value = value))
    }

    suspend fun categoryBreakdown() = db.mindfulTaskDao().categoryBreakdown()
    suspend fun latestUsage() = db.usageDao().latest()
}
