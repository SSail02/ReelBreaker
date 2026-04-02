package com.reelbreaker.features.tracking

import com.reelbreaker.data.local.UsageEntity

class UsageAnalyzer {
    fun sumAvoided(events: List<UsageEntity>): Int = events.filter { it.metric == "avoided_reels" }.sumOf { it.value }
    fun toDailyBuckets(events: List<UsageEntity>): Map<String, Int> = events.groupBy { java.text.SimpleDateFormat("MM-dd", java.util.Locale.US).format(java.util.Date(it.timestamp)) }.mapValues { e -> e.value.sumOf { it.value } }
}
