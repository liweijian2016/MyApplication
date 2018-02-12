package org.personal.mint.myapplication.domain.datasource

import com.antonioleiva.weatherapp.extensions.firstResult
import org.personal.mint.myapplication.data.db.ForecastDb
import org.personal.mint.myapplication.data.server.ForecastServer
import org.personal.mint.myapplication.domain.model.Forecast
import org.personal.mint.myapplication.domain.model.ForecastList

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }
//    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
//            = sources.firstResult { requestSource(it, days, zipCode) }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}