package org.personal.mint.myapplication.useless

import org.jetbrains.annotations.Nullable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.personal.mint.myapplication.extensions.toDateString
import java.text.DateFormat

/**
 * @author lwj
 * @date 2018/2/6
 */
class KotlinUnitTest {

    public class Person {
        var name: String = ""
            get() = field.toUpperCase()
            set(value) {
                field = "Name: $value"
            }
        var sex: String = "man"
            get() = field.toLowerCase()
            set(value) {
                field = "Sex: $value"
            }
    }

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
    }

    //    @Test
    fun test() {

//        val person = Person()
//        person.name = "abc"
//        person.sex = "woman"

//        println(person.name)
//        println(person.sex)

//        val test = NullTest()
//        val myObject: Any = test.getObject()!!

        for (i in 0..10 step 3) println(i)
        funWhen(3)
        for (i in 10 downTo 0 step 3) println(i)

//        for (i in 0 until 4) println(i)

    }

    private fun funWhen(a: Int) {
        when (a) {
            1 -> println("a")
            2 -> println("b")
            else -> println("else")
        }
    }

}

class NullTest {

    @Nullable
    fun getObject(): Any? {
        return ""
    }
}