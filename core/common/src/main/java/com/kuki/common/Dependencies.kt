package com.kuki.common

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

interface Dependencies

interface HasDependencies {
    val dependenciesMap: DependenciesMap
}

typealias DependenciesMap = Map<Class<out Dependencies>, @JvmSuppressWildcards Dependencies>

@Composable
inline fun <reified T : Dependencies> findDependencies(): T {
    val dependenciesClass = T::class.java
    val context = LocalContext.current
    return context
        .parents()
        .firstNotNullOfOrNull { it.dependenciesMap[dependenciesClass] } as T?
        ?: throw IllegalStateException(
            "No Dependencies $dependenciesClass in ${
                context.allParents().joinToString()
            }"
        )
}

inline fun <reified T : Dependencies> findDependenciesTest(context: Context): T {
    val dependenciesClass = T::class.java
    return context
        .parents()
        .firstNotNullOfOrNull { it.dependenciesMap[dependenciesClass] } as T?
        ?: throw IllegalStateException(
            "No Dependencies $dependenciesClass in ${
                context.allParents().joinToString()
            }"
        )
}

fun Context.parents(): Iterable<HasDependencies> {
    return allParents().mapNotNull { it as? HasDependencies }
}

fun Context.allParents(): Iterable<Any> {
    return object : Iterable<Any> {
        override fun iterator(): Iterator<Any> = object : Iterator<Any> {
            private var parentActivity = this@allParents as? Activity
            private var parentApp = parentActivity?.applicationContext

            override fun hasNext(): Boolean {
                return parentActivity != null || parentApp != null
            }

            override fun next(): Any {
                parentActivity?.let { parent ->
                    parentActivity = null
                    return parent
                }

                parentApp?.let { parent ->
                    parentApp = null
                    return parent
                }

                throw NoSuchElementException("Can't find parent")
            }
        }
    }
}