//@file:Suppress("MemberVisibilityCanPrivate")

package org.personal.mint.myapplication.domain

import com.google.gson.Gson
import org.personal.mint.myapplication.data.server.ForecastResult
import java.net.URL

/**
 * @author lwj
 * @date 2018/2/6
 */
 class ForecastRequest(val zipCode: String) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}