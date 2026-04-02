package com.reelbreaker.features.blocking

import android.view.accessibility.AccessibilityNodeInfo

class ReelsDetector {
    private val textSignals = listOf("Reels", "Short videos", "clips")
    private val idSignals = listOf("reel_viewer", "clips_tab")

    fun isReelsScreen(root: AccessibilityNodeInfo?): Boolean {
        root ?: return false
        return containsTextSignal(root) || containsIdSignal(root)
    }

    private fun containsTextSignal(node: AccessibilityNodeInfo): Boolean {
        val texts = sequenceOf(node.text, node.contentDescription).filterNotNull().map { it.toString() }
        if (texts.any { text -> textSignals.any { s -> text.contains(s, true) } }) return true
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { if (containsTextSignal(it)) return true }
        }
        return false
    }

    private fun containsIdSignal(node: AccessibilityNodeInfo): Boolean {
        val viewId = node.viewIdResourceName.orEmpty()
        if (idSignals.any { viewId.contains(it, true) }) return true
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { if (containsIdSignal(it)) return true }
        }
        return false
    }
}
