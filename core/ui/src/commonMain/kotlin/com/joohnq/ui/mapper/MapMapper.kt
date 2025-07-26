package com.joohnq.ui.mapper

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = { items.keys.toList()[it].toString() }
) {
    itemContent(items.entries.toList()[it].value)
}

fun <K, T> LazyListScope.items(
    items: Map<K, List<T>>,
    title: @Composable LazyItemScope.(key: K) -> Unit,
    empty: @Composable LazyItemScope.() -> Unit,
    content: @Composable LazyItemScope.(item: T) -> Unit,
) {
    for ((key, value) in items) {
        if (items.isEmpty()) {
            item {
                empty()
            }
        } else {
            item {
                title(key)
            }
            items(items = value) { item ->
                content(item)
            }
        }
    }
}

fun <K, T> LazyListScope.itemsIndexed(
    items: Map<K, List<T>>,
    title: @Composable LazyItemScope.(key: K) -> Unit,
    empty: @Composable LazyItemScope.() -> Unit,
    content: @Composable LazyItemScope.(index: Int, lastIndex: Int, item: T) -> Unit,
) {
    for ((key, value) in items) {
        if (items.isEmpty()) {
            item {
                empty()
            }
        } else {
            item {
                title(key)
            }
            itemsIndexed(items = value) { index, item ->
                content(index, value.lastIndex, item)
            }
        }
    }
}
