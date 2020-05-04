package com.stripe.android

/**
 * Taken from https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 */
internal open class SingletonHolder<in A, out T : Any>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}