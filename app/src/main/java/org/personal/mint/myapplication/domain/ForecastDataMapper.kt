package org.personal.mint.myapplication.domain

import org.personal.mint.myapplication.data.server.Forecast
import org.personal.mint.myapplication.data.server.ForecastResult
import org.personal.mint.myapplication.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import org.personal.mint.myapplication.domain.model.Forecast as ModelForecast

/**
 * @author lwj
 * @date 2018/2/6
 */
class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList =
            ForecastList(1, forecast.city.name,
                    forecast.city.country,
                    convertForecastListToDomain(forecast.list))

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> =
            list.map { convertForecastItemToDomain(it) }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast =
            ModelForecast(-1,
                    convertDate(forecast.dt),
                    forecast.weather[0].description,
                    forecast.temp.max.toInt(),
                    forecast.temp.min.toInt(),
                    generateIconUrl(forecast.weather[0].icon))

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}