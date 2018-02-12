package org.personal.mint.myapplication.domain.datasource

import org.personal.mint.myapplication.domain.model.Forecast
import org.personal.mint.myapplication.domain.model.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}