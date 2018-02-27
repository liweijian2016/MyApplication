package org.personal.mint.myapplication.domain.commands

import org.personal.mint.myapplication.domain.datasource.ForecastProvider
import org.personal.mint.myapplication.domain.model.ForecastList

/**
 * @author lwj
 * @date 2018/2/6
 */
class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}
//class RequestForecastCommand(private val zipCode: String) :
//        Command<ForecastList> {
//    override fun execute(): ForecastList {
//        val forecastRequest = ForecastRequest(zipCode)
//        return ForecastDataMapper().convertFromDataModel(
//                forecastRequest.execute())
//    }
//}