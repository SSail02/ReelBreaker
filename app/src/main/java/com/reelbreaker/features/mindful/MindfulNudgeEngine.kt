package com.reelbreaker.features.mindful

import com.reelbreaker.data.prefs.PrefsManager
import java.util.Calendar

class MindfulNudgeEngine(private val prefs: PrefsManager) {
    private var prefetched: MindfulTask? = null

    fun nextTask(now: Calendar = Calendar.getInstance()): MindfulTask {
        prefetched?.let { prefetched = null; return it }
        val hour = now.get(Calendar.HOUR_OF_DAY)
        val candidates = TaskLibrary.tasks.filter { task ->
            when {
                hour in 22..23 || hour in 0..5 -> task.category in setOf(TaskCategory.MIND, TaskCategory.BODY, TaskCategory.HOME)
                hour in 18..21 -> task.category != TaskCategory.NATURE
                else -> true
            }
        }
        val lastIndex = prefs.getLastTaskIndex()
        val globalIndex = candidates.indices.randomOrNull()?.let { TaskLibrary.tasks.indexOf(candidates[it]) }
            ?: TaskLibrary.randomIndex(lastIndex)
        val safeIndex = if (globalIndex == lastIndex) TaskLibrary.randomIndex(lastIndex) else globalIndex
        prefs.setLastTaskIndex(safeIndex)
        return TaskLibrary.byIndex(safeIndex)
    }

    fun prefetchNextTask() {
        prefetched = nextTask()
    }
}
