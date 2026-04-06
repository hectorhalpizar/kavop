@file:JvmName("CollectionsUtil")
package me.hectorhalpizar.kavop.util.collections

internal val <E> Set<E>?.toSafeMutableSet: MutableSet<E>
    get() = this?.toMutableSet() ?: mutableSetOf()
