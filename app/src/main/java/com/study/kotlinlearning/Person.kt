package com.study.kotlinlearning

import android.util.Log

/**
 * @Author: Created by seven.zhang
 * @Date: 2021/5/25 18:24
 * @Desc:
 */
class Person constructor(var name: String, var age: Int, var weight: Float) {
    val TAG = "Person"
    init {
        logger("\nname: $name \nage: $age \nweight: $weight")
    }

    fun logger(msg: String) {
        Log.e(TAG, msg)
    }

}