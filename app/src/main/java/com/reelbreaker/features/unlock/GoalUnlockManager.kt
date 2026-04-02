package com.reelbreaker.features.unlock

class GoalUnlockManager {
    fun canUnlock(outcome: String): Boolean = outcome == "DONE"
}
