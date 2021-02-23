package com.example.misgastos.utils

fun String.valueOrNull(): String? {
    return if (this.isEmpty().not()) {
        this
    } else {
        null
    }
}
