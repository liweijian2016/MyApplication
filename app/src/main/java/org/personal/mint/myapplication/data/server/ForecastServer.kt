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

    /**
     * 另一方面，ForecastServer将不会再使用，因为信息总是会被缓存在数据库中。
     * 我们可以在一些奇怪的场景下实现一些代码保护，但是我们在这个例子中没有做任何处理，
     * 所以如果发生它也会只是抛出一个异常
     */
    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}