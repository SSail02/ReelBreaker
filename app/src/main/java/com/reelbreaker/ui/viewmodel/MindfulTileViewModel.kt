package com.reelbreaker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.reelbreaker.ReelBreakerApp
import com.reelbreaker.features.blocking.SessionManager
import com.reelbreaker.features.mindful.MindfulNudgeEngine
import com.reelbreaker.features.mindful.MindfulTask
import com.reelbreaker.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MindfulTileViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as ReelBreakerApp).repository
    private val sessionManager = SessionManager(repo.prefs)
    private val nudgeEngine = MindfulNudgeEngine(repo.prefs)

    fun currentTask(): MindfulTask = nudgeEngine.nextTask()

    fun complete(task: MindfulTask) = viewModelScope.launch(Dispatchers.IO) {
        sessionManager.allowForMinutes(Constants.ALLOW_MINUTES)
        repo.logTask(task.taskText, task.category.name, "DONE")
    }

    fun snooze(task: MindfulTask) = viewModelScope.launch(Dispatchers.IO) {
        sessionManager.snoozeForMinutes(Constants.SNOOZE_MINUTES)
        repo.logTask(task.taskText, task.category.name, "SNOOZE")
    }

    fun skip(task: MindfulTask) = viewModelScope.launch(Dispatchers.IO) {
        repo.logTask(task.taskText, task.category.name, "SKIP")
    }
}
