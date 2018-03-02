package org.personal.mint.myapplication.extensions

import android.content.Context
import android.widget.Toast
import java.text.DateFormat
import java.util.*

/**
 * 我创建了另一个扩展函数来转换一个Long对象到一个用于显示的日期字符串。记住我们在adapter中也使用了，所以明确定义它为一个函数是个不错的实践：
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}

fun toast(ctx: Context, message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ctx, message, length).show()
}

fun niceToast(
        ctx: Context,
        message: String,
//        tag: String = javaClass<MainActivity>().getSimpleName(),
        tag: String = "MainActivity",
        length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(ctx, "[$tag] $message", length).show()

