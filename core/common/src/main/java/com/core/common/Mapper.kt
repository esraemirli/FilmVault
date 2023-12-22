package com.core.common

interface Mapper<T, K> {
    fun map(from: T): K
}