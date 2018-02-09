package org.personal.mint.myapplication

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

        val person = Person()
        person.name = "abc"
        person.sex = "woman"

        println(person.name)
        println(person.sex)
    }

}