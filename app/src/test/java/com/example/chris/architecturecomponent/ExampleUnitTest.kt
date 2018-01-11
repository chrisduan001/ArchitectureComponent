package com.example.chris.architecturecomponent

import android.app.Application
import android.app.Instrumentation
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        test({d1: String ->
            val x = 0;

        }, {bool: Boolean ->
            if (bool)
                return@test "xyz"
            else
                return@test "zzz"
        })
    }

    fun fromTimestamp(millis: Long?) : Date? = if (millis == null) null else Date(millis)

    fun test(m1: (d1: String) -> Unit, m2: (bool : Boolean) -> String) {
        m1.invoke("xyz")
        m1("zzz")
        if (m2(true) == "xyz") {

        }
    }
}
