package com.study.kotlinlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.function.IntPredicate

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    //只读list
    private val mList = listOf("ab", "ac", "h", "i", "c", "d", "e", "f", "g")

    //只读map
    private val mMap = mapOf("a" to 1, "b" to 2, "c" to 3)


    //延迟属性
    val mDelayProperty by lazy {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGetMax.setOnClickListener {
            logger("getMax: ${getMax(10, 12)}")
        }
        btnIsString.setOnClickListener {
            isString(0)
            isString("0")
        }
        btnDoFor.setOnClickListener {
            doFor()
        }
        btnDoWhile.setOnClickListener {
            doWhile()
        }
        btnUseWhen.setOnClickListener {
            logger("useWhen: ${useWhen("0")}")
            logger("useWhen: ${useWhen(0L)}")
            logger("useWhen: ${useWhen(0)}")
        }
        btnDoRange.setOnClickListener {
            doRange(2)
            doRange(6)
        }
        btnDoRangeFor.setOnClickListener {
            doRangeFor()
        }
        btnDoListFilter.setOnClickListener {
            doListFilter()
        }
        btnDoForMap.setOnClickListener {
            doForMap()
        }
        btnPerson.setOnClickListener {
            doInitPerson()
        }
        btnPerson.setOnClickListener {
            doInitPerson()
        }
        btnListSizeLess1.setOnClickListener {
            logger("mList.size: ${mList.size}")
            logger("mList.size-1: ${mList.getSizeLess1()}")
        }
        btnSamFunction.setOnClickListener {
            //unuse sam
            testSam(0, object : ISam {
                override fun testSam(i: Int): Boolean {
                    return false
                }
            })
            //use sam
            ISam { i -> i == 0 }
            ISam { it == 0 }
            testSam(10, {
                logger(it.toString())
                it == 10
            })
            View.OnClickListener { view -> }
        }
        btnExtensionFunction.setOnClickListener {
            logger(tvText.getText1())
        }
        btnList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }

    fun testSam(i: Int, iSam: ISam) {
        iSam.testSam(i)
    }

    /**
     * 获取最大的
     */
//    fun getMax(a: Int, b: Int):Int = if (a > b) a else b
    fun getMax(a: Int, b: Int) = if (a > b) a else b

    /**
     * ?代表可为空
     */
    fun isString(param: Any): Any? {
        logger("isString: ${param is String}")
        if (param is String)
            return param.length
        return null
    }

    fun doFor() {
        //拿不到下标,直接拿的item
        for (item in mList) {
            logger("item: ${item}")
        }
        //拿的是下标
        for (index in mList.indices) {
            logger("index: ${index} ---item: ${mList[index]}")
        }
    }

    var mNum = 0
    fun doWhile() {
        while (mNum < mList.size) {
            logger("doWhile: ${mList[mNum]}")
            mNum++
        }
    }

    fun useWhen(param: Any): String {
        return when (param) {
            is String -> "String"
            is Int -> "Int"
            is Long -> "Long"
            else -> "unKnow"
        }
    }

    fun doRange(param: Int) {
        logger("doRange: ${param in 0..5}")
        /**
         * can use !in
         */
        if (param in 0..5) {
            logger("doRange: 在区间内")
        } else {
            logger("doRange: 不在区间内")
        }
    }

    fun doRangeFor() {
        //包含5
        for (index in 0..5) {
            logger("doRangeFor: 0..5->index: ${index}")
        }
        //不包含5
        for (index in 0 until 5) {
            logger("doRangeFor: 0 until 5->index: ${index}")
        }
        //倒叙
        for (index in 5 downTo 1) {
            logger("doRangeFor: 5 downTo 1->index: ${index}")
        }
        //10个10个跳
        for (index in 0..100 step 10) {
            logger("doRangeFor: 0..100 step 10->index: ${index}")
        }
    }

    fun doListFilter() {
        mList.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase(Locale.ROOT) }
            .forEach { logger(it) }
    }

    fun doForMap() {
        for ((k, v) in mMap) {
            logger("k: $k - v: $v")
        }
    }

    fun doInitPerson() {
        val person = Person("张三", 18, 167.5F)
    }

    internal fun testInternal() {

    }

    fun List<String>.getSizeLess1(): Int {
        return this.size - 1
    }

    fun logger(msg: String) {
        Log.e(TAG, msg)
    }

    fun TextView.getText1(): String {
        return "我是扩展函数---${text}"
    }

    /**
     * 指定值在前面
     */
    fun defaultValueFun(i: Int = 0, j: Int) {
        defaultValueFun(j = 10)
    }

}