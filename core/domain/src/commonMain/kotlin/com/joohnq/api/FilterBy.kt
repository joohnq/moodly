package com.joohnq.api

fun <P, S> Map<P, List<S>>.filterBy(condition: (S) -> Boolean): Map<P, List<S>> =
    mapValues { (_, list) -> list.filterNot { item -> condition(item) } }
        .filterValues { itemFilter -> itemFilter.isNotEmpty() }
