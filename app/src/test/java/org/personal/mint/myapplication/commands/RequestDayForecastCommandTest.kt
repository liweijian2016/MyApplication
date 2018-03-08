package org.personal.mint.myapplication.commands

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.personal.mint.myapplication.domain.commands.RequestDayForecastCommand
import org.personal.mint.myapplication.domain.datasource.ForecastProvider

class RequestDayForecastCommandTest {

    @Test
    fun testProviderIsCalled() {
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)

        command.execute()

        verify(forecastProvider).requestForecast(2)
    }
}