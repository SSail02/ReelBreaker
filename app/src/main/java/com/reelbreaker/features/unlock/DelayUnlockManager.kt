package com.reelbreaker.features.unlock

import kotlinx.coroutines.delay

class DelayUnlockManager {
    suspend fun waitIntentionalDelay(seconds: Int = (5..10).random()) = delay(seconds * 1000L)
}
