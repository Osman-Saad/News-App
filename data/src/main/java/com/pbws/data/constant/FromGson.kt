package com.pbws.data.constant

import com.google.gson.Gson

fun <T> Any.fromGson(clazz: Class<T>): T {
    val json = Gson().toJson(this)
    return Gson().fromJson(json, clazz)
}