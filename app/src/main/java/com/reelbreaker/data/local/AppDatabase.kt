package com.reelbreaker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MindfulTaskLogEntity::class, BlockEventEntity::class, UsageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mindfulTaskDao(): MindfulTaskDao
    abstract fun blockEventDao(): BlockEventDao
    abstract fun usageDao(): UsageDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "reelbreaker.db"
            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}
