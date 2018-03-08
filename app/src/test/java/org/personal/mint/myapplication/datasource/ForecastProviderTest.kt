package org.personal.mint.myapplication.datasource

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.personal.mint.myapplication.domain.datasource.ForecastDataSource
import org.personal.mint.myapplication.domain.datasource.ForecastProvider
import org.personal.mint.myapplication.domain.model.Forecast
import org.personal.mint.myapplication.domain.model.ForecastList

/**
 * @author lwj
 * @date 2018/3/7
 */
class ForecastProviderTest {

    @Test
    fun testDataSourceReturnsValue() {
        val ds = Mockito.mock(ForecastDataSource::class.java)
        Mockito.`when`(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(ds))
        val requestForecast = provider.requestForecast(0)
        println(requestForecast.description)
        println(requestForecast.iconUrl)
        Assert.assertNotNull(provider.requestForecast(0))
    }

    @Test
    fun emptyDatabaseReturnsServerValue() {
        val db = Mockito.mock(ForecastDataSource::class.java)
        val server = Mockito.mock(ForecastDataSource::class.java)
        Mockito.`when`(server.requestForecastByZipCode(Mockito.any(Long::class.java), Mockito.any(Long::class.java)))
                .then { ForecastList(0, "city", "country", listOf()) }

        val provider = ForecastProvider(listOf(db, server))
        Assert.assertNotNull(provider.requestByZipCode(0, 0))
    }
}