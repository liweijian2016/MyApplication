package org.personal.mint.myapplication

import org.jetbrains.annotations.Nullable
import org.junit.Test

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
    fun getObject():Any?{
        return ""
    }
}