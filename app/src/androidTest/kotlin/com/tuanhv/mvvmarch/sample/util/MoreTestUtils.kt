package com.tuanhv.mvvmarch.sample.util

/**
 * Created by hoang.van.tuan on 9/23/20.
 */
class MoreTestUtils {

    companion object {

        const val DURATION_SHORT = 3
        const val DURATION_MEDIUM = 6
        const val DURATION_LONG = 12

        fun sleep(seconds: Int) {
            try {
                Thread.sleep((seconds * 1000).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

    }

}
