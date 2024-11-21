package com.joohnq.moodapp.mappers

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable

fun <K, V> Map<out K, V>.forEachMap(action: (key: K, value: V) -> Unit) {
    for ((key, value) in this) {
        action(key, value)
    }
}

@Composable
fun <K, V> Map<out K, V>.forEachMapComposable(action: @Composable (key: K, value: V) -> Unit) {
    for ((key, value) in this) {
        action(key, value)
    }
}

inline fun <K, T> LazyGridScope.items(
    items: Map<K, T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = { items.keys.toList()[it] as Any },
) {
    itemContent(items.entries.toList()[it].value)
}
