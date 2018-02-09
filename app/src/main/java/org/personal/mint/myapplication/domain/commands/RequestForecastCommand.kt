package org.personal.mint.myapplication.domain.commands

import org.personal.mint.myapplication.domain.ForecastDataMapper
import org.personal.mint.myapplication.domain.ForecastRequest
import org.personal.mint.myapplication.domain.model.ForecastList

/**
 * @author lwj
 * @date 2018/2/6
 */
class RequestForecastCommand(private val zipCode: String) :
        Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute())
    }
}