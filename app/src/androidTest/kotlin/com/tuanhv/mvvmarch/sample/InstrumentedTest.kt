package com.tuanhv.mvvmarch.sample

import androidx.annotation.CallSuper
import com.tuanhv.mvvmarch.sample.util.MoreTestUtils
import org.junit.After
import org.junit.Before
import java.io.File

/**
 * Created by hoang.van.tuan on 9/23/20.
 * InstrumentedTest create coverage folder
 */
abstract class InstrumentedTest {

    @CallSuper
    @Before
    fun setUp() {
        val file = File("/sdcard/tmp/code-coverage/connected")
        if (!file.exists()) file.mkdirs()

        MoreTestUtils.sleep(MoreTestUtils.DURATION_MEDIUM)
    }

    @CallSuper
    @After
    fun tearDown() {
    }

}
