package com.reelbreaker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MindfulTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: MindfulTaskLogEntity)

    @Query("SELECT COUNT(*) FROM mindful_task_log WHERE outcome = :outcome")
    fun countByOutcome(outcome: String): Flow<Int>

    @Query("SELECT category, COUNT(*) as total FROM mindful_task_log GROUP BY category")
    suspend fun categoryBreakdown(): List<CategoryCount>
}

data class CategoryCount(val category: String, val total: Int)

@Dao
interface BlockEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: BlockEventEntity)

    @Query("SELECT COUNT(*) FROM block_events")
    fun totalBlocked(): Flow<Int>
}

@Dao
interface UsageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: UsageEntity)

    @Query("SELECT * FROM usage_events ORDER BY timestamp DESC LIMIT 30")
    suspend fun latest(): List<UsageEntity>
}
