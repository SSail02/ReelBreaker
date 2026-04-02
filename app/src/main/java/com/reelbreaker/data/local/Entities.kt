package com.reelbreaker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mindful_task_log")
data class MindfulTaskLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val taskText: String,
    val category: String,
    val outcome: String
)

@Entity(tableName = "block_events")
data class BlockEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val appName: String,
    val reason: String
)

@Entity(tableName = "usage_events")
data class UsageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val metric: String,
    val value: Int
)
