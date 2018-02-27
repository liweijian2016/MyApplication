package org.personal.mint.myapplication.data.db

import android.database.sqlite.SQLiteDatabase
import org.personal.mint.myapplication.extensions.byId
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.personal.mint.myapplication.domain.datasource.ForecastDataSource
import org.personal.mint.myapplication.domain.model.ForecastList

/**
 * @author lwj
 * @date 2018/2/9
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {
    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME)
                .byId(id)
                .parseOpt { DayForecast(HashMap(it)) }
        forecast?.let { dataMapper.convertDayToDomain(it) }
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " +
                "AND ${DayForecastTable.DATE} >= ?"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
    /**
     * 以下函数隶属于 CollectionsExtensions
     */
    fun <K, V : Any> MutableMap<K, V?>.toVarargArray():
            Array<out Pair<K, V>> = map({ Pair(it.key, it.value!!) }).toTypedArray()

    /**
     * 以下三个函数隶属于 DatabaseExtensions
     */
    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }

    fun <T : Any> SelectQueryBuilder.parseList(
            parser: (Map<String, Any?>) -> T): List<T> =
            parseList(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T {
                    return parser(columns)
                }
            })

    fun <T : Any> SelectQueryBuilder.parseOpt(
            parser: (Map<String, Any?>) -> T): T? =
            parseOpt(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T {
                    return parser(columns)
                }
            })
}