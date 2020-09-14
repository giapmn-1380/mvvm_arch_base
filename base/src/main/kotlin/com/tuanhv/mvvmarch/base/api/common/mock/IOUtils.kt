package com.tuanhv.mvvmarch.base.api.common.mock

import android.content.res.AssetManager
import java.io.FileInputStream
import java.io.IOException

/**
 * Created by hoang.van.tuan on 9/14/20.
 */
object IOUtils {

    fun getJsonStringFromFile(assets: AssetManager, fileName: String): String {
        var json = ""
        try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

}
