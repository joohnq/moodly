package com.joohnq.moodapp.model

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.TypedRealmObject
import kotlinx.coroutines.flow.catch

suspend inline fun <reified T : TypedRealmObject> Realm.flowGetAll(
    crossinline onCatch: (Throwable) -> Unit,
    crossinline block: (List<T>) -> Unit
) {
    val result = query<T>().find().asFlow()
    result.catch {
        onCatch(it)
    }.collect { results ->
        val items = results.list.map { it }
        block(items)
    }
}

suspend inline fun <reified T : TypedRealmObject> Realm.flowGetTheOne(): T? {
    val result = query<T>(
        "id == $0",
        "1"
    ).first().find()?.asFlow()

    var r: T? = null

    result?.catch {
        throw Throwable(it.message.toString())
    }?.collect { item ->
        r = item.obj
    }

    return r
}