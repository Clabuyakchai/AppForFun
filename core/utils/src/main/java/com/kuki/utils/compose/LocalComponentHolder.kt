package com.kuki.utils.compose

import androidx.compose.runtime.compositionLocalOf

val LocalComponentHolder = compositionLocalOf<ComponentHolder> {
    error("No object provided!")
}

interface ComponentHolder