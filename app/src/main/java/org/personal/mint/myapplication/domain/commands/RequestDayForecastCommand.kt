package org.personal.mint.myapplication.domain.commands

import org.personal.mint.myapplication.domain.datasource.ForecastProvider
import org.personal.mint.myapplication.domain.model.Forecast


class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}