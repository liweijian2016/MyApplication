package org.personal.mint.myapplication.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.personal.mint.myapplication.R
import org.personal.mint.myapplication.domain.commands.RequestDayForecastCommand
import org.personal.mint.myapplication.domain.model.Forecast
import org.personal.mint.myapplication.extensions.color
import org.personal.mint.myapplication.extensions.textColor
import org.personal.mint.myapplication.extensions.toDateString
import java.text.DateFormat

class DetailActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        const val ID = "DetailActivity:id"
        const val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        // 获取数据源.
        toolbarTitle = intent.getStringExtra(CITY_NAME)
        val ID = intent.getLongExtra(ID, -1)
        // 开启导航返回icon
        enableHomeAsUp { onBackPressed() }

        @Suppress("EXPERIMENTAL_FEATURE_WARNING")
        async(UI) {
            val result = bg { RequestDayForecastCommand(ID).execute() }
            bindForecast(result.await())
        }
    }

    /**
     * 对四个控件进行数据的绑定.
     */
    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    /**
     * 如果仅仅是绑定数据不需修改字体颜色的话,refactor 为 bindInt 不无不可.
     */
    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_blue_dark
            in 0..15 -> android.R.color.holo_green_light
            else -> android.R.color.holo_red_dark
        })
    }
}