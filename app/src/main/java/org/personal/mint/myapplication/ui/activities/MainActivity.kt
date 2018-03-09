package org.personal.mint.myapplication.ui.activities

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.personal.mint.myapplication.R
import org.personal.mint.myapplication.domain.commands.RequestForecastCommand
import org.personal.mint.myapplication.extensions.DelegatesExt
import org.personal.mint.myapplication.ui.adapters.ForecastListAdapter
import java.net.URL

class MainActivity : AppCompatActivity(), ToolbarManager {
    private val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
//        supportsLollipop { window.statusBarColor = Color.BLACK }
//        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
//        val forecastList: RecyclerView = find(R.id.forecastList)
//        val url: String = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22"
        forecastList.layoutManager = LinearLayoutManager(this)
        /**
         * 跟随滚动
         */
        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() {
        doAsync {
            val result = RequestForecastCommand(94043).execute()
            Log.i("dailyForecast", "dailyForecast---->" + result.dailyForecast.size);
            uiThread {
                toast("Request performed")
                //                forecastList.adapter = ForecastListAdapter(result, { forecast -> toast(forecast.date) })
                forecastList.adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(
                            DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city
                    )
                }
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
    }

    public class Request(val url: String) {
        public fun run() {
            val forecastJsonStr = URL(url).readText()
            Log.i("forecastJsonStr", forecastJsonStr)
            Log.d(javaClass.simpleName, forecastJsonStr)
        }
    }

    inline fun supportsLollipop(code: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            code()
        }
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHER STATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

}


