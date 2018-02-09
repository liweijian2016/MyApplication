package org.personal.mint.myapplication

import android.util.Log
import java.net.URL

public class Request(val url: String) {

    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.i("forecastJsonStr", forecastJsonStr)
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}