package com.reelbreaker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.reelbreaker.ReelBreakerApp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as ReelBreakerApp).repository
    val blockedCount = repository.totalBlocked.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)
    val completedCount = repository.completedTasks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)
}
