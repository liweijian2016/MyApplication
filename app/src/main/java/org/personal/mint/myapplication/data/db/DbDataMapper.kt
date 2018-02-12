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

    fun convertToDomain(cityForecast: CityForecast) = with(cityForecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date.toString(), description, high, low, iconUrl)
    }
}