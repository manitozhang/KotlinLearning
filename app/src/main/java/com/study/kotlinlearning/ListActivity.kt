package com.study.kotlinlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity() {
    private val TAG = "ListActivity"

    private val numbersList = mutableListOf("one", "two", null, "four", "five", "six", "seven")
    private val emptySet = mutableSetOf<String>()
    private val numbersSet = setOf("one", "two", "three", "four", "five", "six", "seven")
    private val numberMap = mapOf("key1" to 1, "key2" to 2, "key11" to 11)
    private val emptyList = emptyList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        btnIterator.setOnClickListener {
            val iterator = numbersList.iterator()
            while (iterator.hasNext()) {
                logger(iterator.toString())
            }
            numbersList.forEach {
                if (it != null) {
                    logger(it)
                }
            }
            for (i in numbersList.indices) {
                logger("index---${i}")
            }
            for (i in numbersList) {
                logger("item---${i}")
            }

            for (i in 0..4) {
                logger("0..4---$i")
            }
            for (i in 0..10 step 2) {
                logger("0..10 step 2---$i")
            }
            for (i in 10 downTo 0 step 2) {
                logger("10 downTo 0 step 2---$i")
            }
        }
        btnListConvert.setOnClickListener {
            numbersList.mapIndexed { index, s -> logger("index---$index s---$s") }
            numbersList.filterNotNull().map { logger("mapNotNull---$it") }
            val mapNotNullList = numbersList.mapNotNull {
                logger("mapNotNull---$it")
                it
            }
            mapNotNullList.map {
                logger("mapNotNullList---$it")
            }
            numbersList.mapIndexedNotNull { index, s -> logger("index---$index s---$s") }
        }
        btnMaptConvert.setOnClickListener {
            numberMap.mapKeys { it.key.toUpperCase(Locale.ROOT) }
                .mapValues { logger("key---${it.key}  value---${it.value}") }
        }
        btnListClose.setOnClickListener {
            val colorList = mutableListOf("red", "yellow", "blue", "green")
            val phoneList = mutableListOf("xiaomi", "huawei", "vivo", "oppo")
            logger((colorList zip phoneList).toString())
            val colorList2 = mutableListOf("red", "yellow")
            logger((colorList2 zip phoneList).toString())
        }
        btnFilter.setOnClickListener {
            numberMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
                .map { logger("{${it.key},${it.value}}") }
            val list = listOf(1.5, null, 10, 2.3, "a", 2L, 3L, 0.2f, 2.3, "b")
            list.filterIsInstance<Double>()
                .forEach {
                    logger(it.toString())
                }
        }
        btnPartition.setOnClickListener {
            val list = listOf(1.5, null, 10, 2.3, "a", 2L, 3L, 0.2f, 2.3, "b")
            val (stringList, otherList) = list.partition { (it is String) }
            logger("stringList---$stringList")
            logger("otherList---$otherList")
        }
        btnPlusMinus.setOnClickListener {
            val plusList = numbersList + "nine"
            val minusList = numbersList - listOf("one", "two", "three", "four")
            plusList.map {
                logger("plus$it")
            }
            minusList.map {
                logger("minus$it")
            }
        }
        btnSectionList.setOnClickListener {
            //slice: 使用的下标
            logger("slice${numbersList.slice(1..3)}")
            //take: 保留
            logger("take${numbersList.take(3)}")
            logger("takeLast${numbersList.takeLast(2)}")
            //drop: 去除
            logger("drop${numbersList.drop(2)}")
            logger("dropLast${numbersList.dropLast(3)}")
        }
        btnGetSingleItem.setOnClickListener {
            //顺序存储
            val mLinkedSetOf = linkedSetOf("one", "ona", "three", "nour")
            logger("${mLinkedSetOf}")
            logger("${mLinkedSetOf.elementAt(0)}")
            //升序排序
            val mSortedSetOf = sortedSetOf("one", "ona", "three", "nour")
            logger("${mSortedSetOf}")
            val sortedBy = numbersList.sortedBy { it?.first() }
            val mNumbersListSortedSetOf = sortedSetOf("one", "ona", "three", "nour")
            logger("${sortedBy}")
            logger("${mNumbersListSortedSetOf}")
            logger("${numbersList.filterNotNull().firstOrNull { it.length>4 }}")
            logger(numbersList.filterNotNull().last { it.startsWith("s") })
            logger(numbersList.filterNotNull().random())
        }
        btnSort.setOnClickListener {
            val numbersList = mutableListOf("one", "two", "three", "four", "five", "six", "seven")
            logger("${numbersList.sorted()}")
            logger("${numbersList.sortedDescending()}")
            logger("sortedBy-Length---${numbersList.sortedBy { it.length }}")
            logger("${numbersList.reversed()}")
            logger("${numbersList.shuffled()}")
        }
        btnAggregation.setOnClickListener{
            val numberList = listOf(21,5,12,63,31,18)
            logger("总和---${numberList.sum()}")
            logger("条目数---${numberList.count()}")
            logger("最小数---${numberList.minOrNull()}")
            logger("最大数---${numberList.maxOrNull()}")
            logger("平均数---${numberList.average()}")
        }
    }


    fun logger(msg: String) {
        Log.e(TAG, msg)
    }

}