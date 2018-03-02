package org.personal.mint.myapplication.data.db

import org.personal.mint.myapplication.domain.model.Forecast
import org.personal.mint.myapplication.domain.model.ForecastList


class DbDataMapper {
    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) =
            with(forecast) {
                DayForecast(date.toLong(), description, high, low, iconUrl, cityId)
            }

    /**
     * with接收一个对象和一个函数，这个函数会作为这个对象的扩展函数执行。
     * 这表示我们根据推断可以在函数内使用this。
     * inline fun <T, R> with(receiver: T, f: T.() -> R): R = receiver.f()
     * 泛型在这里也是以相同的方式运行：T代表接收类型，R代表结果。
     * 如你所见，函数通过f: T.() -> R声明被定义成了扩展函数。这就是为什么我们可以调用receiver.f()。
     */
    fun convertToDomain(cityForecast: CityForecast) = with(cityForecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }
}