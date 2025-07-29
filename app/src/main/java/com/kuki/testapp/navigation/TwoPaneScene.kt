package com.kuki.testapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.kuki.testapp.navigation.TwoPaneScene.Companion.TWO_PANE_DETAIL_KEY
import com.kuki.testapp.navigation.TwoPaneScene.Companion.TWO_PANE_LIST_KEY

class TwoPaneScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>
) : Scene<T> {

    override val entries: List<NavEntry<T>>
        get() = listOf(firstEntry, secondEntry)

    override val content: @Composable (() -> Unit)
        get() = {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f)
                ) {
                    firstEntry.Content()
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.7f)
                ) {
                    secondEntry.Content()
                }
            }
        }

    companion object {
        internal const val TWO_PANE_LIST_KEY = "TwoListPane"
        internal const val TWO_PANE_DETAIL_KEY = "TwoDetailPane"

        fun twoListPane() = mapOf(TWO_PANE_LIST_KEY to true)
        fun twoDetailPane() = mapOf(TWO_PANE_DETAIL_KEY to true)
    }
}

class TwoPaneStrategy<T : Any> : SceneStrategy<T> {
    @Composable
    override fun calculateScene(
        entries: List<NavEntry<T>>,
        onBack: (Int) -> Unit
    ): Scene<T>? {
        val windowsSizeClass = currentWindowAdaptiveInfo().windowSizeClass

        if (!windowsSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND) || entries.size == 1) {
            return null
        }

        val listEntry = entries.find { it.metadata.containsKey(TWO_PANE_LIST_KEY) } ?: return null
        val detailEntry =
            entries.findLast { it.metadata.containsKey(TWO_PANE_DETAIL_KEY) } ?: return null

        return TwoPaneScene(
            key = listEntry.contentKey to detailEntry.contentKey,
            previousEntries = entries.dropLast(1),
            firstEntry = listEntry,
            secondEntry = detailEntry
        )

    }
}