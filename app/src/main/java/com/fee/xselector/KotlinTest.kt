package com.fee.xselector

import com.fee.thexselector.*

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/18<br>
 * Time: 17:11<br>
 * <P>DESC:
 * </p>
 * ******************(^_^)***********************
 */
fun main() {
    var arr1 = arrayOfNulls<String>(3)
//    arr1.forEach {
//        println(it)
//    }
    val arr2 = emptyArray<String>()
//    arr2[0] = "dd"//这里运行时报错，下标越界
//    arr2.forEach {
//        println(it)
//    }

//    val arr3 = Array(3) {
//        0
//    }
//    arr3[0] = 100
// arr3.forEach { println(it) }

//    val map1: Map<Int, Int?> = mapOf(1 to null, 2 to 333, 3 to null)
//
//    map1.filterValues {
//        it != null
//    }
//        .forEach {
//            println(it.key)
//            println(it.value)
//        }

    val dataSet: MutableSet<Int> = mutableSetOf(1, 2, 3, 5, 5)
    dataSet.toIntArray().forEach {
        println(it)
    }

//    val item = StateItemDrawable()
//    item.statePressed(true)
//        .statePressed(false)

    val unFocused = -AbsSelector.INDEX_FOCUSED_STATE
    println(-unFocused)


}