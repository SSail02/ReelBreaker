package com.reelbreaker.features.tracking

import com.reelbreaker.data.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DopamineTracker(private val repository: AppRepository) {
    fun trackAvoidedReel(count: Int = 1) {
        CoroutineScope(Dispatchers.IO).launch { repository.logUsage("avoided_reels", count) }
    }
}
