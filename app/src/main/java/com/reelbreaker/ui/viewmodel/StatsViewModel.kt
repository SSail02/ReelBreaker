package com.reelbreaker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.reelbreaker.ReelBreakerApp
import com.reelbreaker.features.tracking.UsageAnalyzer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StatsViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as ReelBreakerApp).repository
    val totalBlocked = repo.totalBlocked.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)
    val completedTasks = repo.completedTasks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)
    private val _categoryBreakdown = MutableStateFlow<Map<String, Int>>(emptyMap())
    val categoryBreakdown = _categoryBreakdown.asStateFlow()
    private val _dailyUsage = MutableStateFlow<Map<String, Int>>(emptyMap())
    val dailyUsage = _dailyUsage.asStateFlow()

    fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        _categoryBreakdown.value = repo.categoryBreakdown().associate { it.category to it.total }
        _dailyUsage.value = UsageAnalyzer().toDailyBuckets(repo.latestUsage())
    }
}
