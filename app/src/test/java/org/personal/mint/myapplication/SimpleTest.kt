package org.personal.mint.myapplication

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.personal.mint.myapplication.extensions.toDateString
import java.text.DateFormat

/**
 * @author lwj
 * @date 2018/3/7
 */
class SimpleTest {
    @Test
    fun unitTestingWorks() {
        assertTrue(true)
    }

    @Test
    fun testLongToDateString() {
        assertEquals("2015-10-20", 1445275635000L.toDateString())
//        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test
    fun testDateStringFullFormat() {
        assertEquals("2015年10月20日 星期二",
                1445275635000L.toDateString(DateFormat.FULL))
//        assertEquals("Monday, October 19, 2015",
//                1445275635000L.toDateString(DateFormat.FULL))
    }

    class Outer {
        private val bar: Int = 1

        inner class Nested {
            fun foo() = bar
        }
    }

    @Test
    fun testInnerClass() {
//        val demo = Outer.Nested().foo() // == 2
        val demo = Outer().Nested().foo()
        println(demo)
    }

    enum class Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }

    enum class Icon(val res: Int) {
        UP(R.mipmap.ic_launcher),
        SEARCH(R.mipmap.ic_launcher),
        CAST(R.mipmap.ic_launcher_round)
    }

    @Test
    fun testEnum() {
        val res = Icon.SEARCH.res
        println(res)
//        枚举可以通过String匹配名字来获取，我们也可以获取包含所有枚举的Array，所以我们可以遍历它。
        val search: Icon = Icon.valueOf("SEARCH")
        println(search.res)
        val iconList: Array<Icon> = Icon.values()
        for (icon in iconList) {
//            println(icon.res)
//        而且每一个枚举都有一些函数来获取它的名字、声明的位置：
            println(icon.name)
            println(icon.ordinal)
        }
//        枚举根据它的顺序实现了 Comparable接口，所以可以很方便地把它们进行排序
    }

    sealed class Option<out T> {
        class Some<out T> : Option<T>()
        object None : Option<Nothing>()
    }

    @Test
    fun testSealedClass() {
//        val result = when (Option()) {
//            is Option.Some<*> -> "Contains a value"
//            is Option.None -> "Empty"
//            else -> {
//                ""
//            }
//        }
    }

    @Test
    fun testException() {
        println(judgeType(3))
        println(judgeType("abc"))
//        println(judgeType(100L))
        val s = try {
            3 as String
        } catch (e: ClassCastException) {
            null
        }
        println(s)
    }

    private fun judgeType(x: Any): String {
        return when (x) {
            is Int -> "Int instance"
            is String -> "String instance"
            else -> throw UnsupportedOperationException("Not valid type")
        }
    }


}