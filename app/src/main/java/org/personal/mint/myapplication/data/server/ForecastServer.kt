package org.personal.mint.myapplication.data.server

import org.personal.mint.myapplication.data.db.ForecastDb
import org.personal.mint.myapplication.domain.datasource.ForecastDataSource
import org.personal.mint.myapplication.domain.model.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        /*
         * 转换为数据库对应的数据类型 DbClasses 中的具体类型,并保存.
         */
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        /*
        *存储后再从数据库中取出并返回.
        */
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}