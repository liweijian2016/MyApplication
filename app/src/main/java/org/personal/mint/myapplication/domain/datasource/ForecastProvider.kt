package org.personal.mint.myapplication.domain.datasource

import org.personal.mint.myapplication.data.db.ForecastDb
import org.personal.mint.myapplication.data.server.ForecastServer
import org.personal.mint.myapplication.domain.model.Forecast
import org.personal.mint.myapplication.domain.model.ForecastList
import org.personal.mint.myapplication.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }
//    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
//            = sources.firstResult { requestSource(it, days, zipCode) }

    // 以下这两个方法,对应于 DataSource 列表中的条目 ForecastDataSource接口实现类,
    // 请求的两个方法:
    // requestByZipCode ===>    requestForecastByZipCode
    // requestForecast  ===>    requestDayForecast
    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        // 无论哪个数据源,返回的都是 DomainClasses 中的数据类型.
        // 即最终使用的数据类型. ForecastList
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        // 无论哪个数据源,返回的都是 DomainClasses 中的数据类型.
        // 即最终使用的数据类型. Forecast
        it.requestDayForecast(id)
    }

    /**
     * 可以对比两个时间戳是否是同一天.
     */
    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    /**
     * 这个函数使用一个非null类型作为范型。它会接收一个函数，并返回一个可null的对象。
     * 其中这个接收的函数 接收一个 ForecastDataSource，并返回一个可null范型的对象。
     */
    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}