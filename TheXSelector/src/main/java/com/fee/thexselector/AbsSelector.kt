package com.fee.thexselector

import android.view.View

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:36<br>
 * <P>DESC:
 * 抽象选择器: 但该 selector 只对应 每个<item></item> 仅支持 一个 状态 state 属性
 * </p>
 * ******************(^_^)***********************
 */
abstract class AbsSelector<SR, V : View, VT, I : AbsSelector<SR, V, VT, I>> : ISelector<SR, V> {
    //    val INDEX_FOCUSED_STATE = android.R.attr.state_focused
//    val INDEX_PRESSED_STATE = android.R.attr.state_pressed
//    val INDEX_ENABLED_STATE = android.R.attr.state_enabled
//    val INDEX_DISABLED_STATE = -android.R.attr.state_enabled
//    val INDEX_CHECKED_STATE = android.R.attr.state_checked
//    val INDEX_SELECTED_STATE = android.R.attr.state_selected
    companion object {
        const val INDEX_FOCUSED_STATE = android.R.attr.state_focused
        const val INDEX_PRESSED_STATE = android.R.attr.state_pressed
        const val INDEX_ENABLED_STATE = android.R.attr.state_enabled
        const val INDEX_DISABLED_STATE = -android.R.attr.state_enabled
        const val INDEX_CHECKED_STATE = android.R.attr.state_checked
        const val INDEX_SELECTED_STATE = android.R.attr.state_selected
    }

    protected var stateResult: SR? = null

    protected val stateMapValue = mutableMapOf<Int, VT?>()

    protected var defStateValue: VT? = null


//    abstract override fun into(theEffectedView: V)
//
//    abstract override fun build(needRebuild: Boolean): SR?


    fun defState(defValue: VT?): I {
        defStateValue = defValue
        return self()
    }

    fun focusedState(focusedValue: VT?): I {
        stateMapValue[INDEX_FOCUSED_STATE] = focusedValue
        return self()
    }

    fun pressedState(pressedValue: VT?): I {
        stateMapValue[INDEX_PRESSED_STATE] = pressedValue
        return self()
    }

    fun enableState(enableValue: VT?): I {
        stateMapValue[INDEX_ENABLED_STATE] = enableValue
        return self()
    }

    fun disabledState(disabledValue: VT?): I {
        stateMapValue[INDEX_DISABLED_STATE] = disabledValue
        return self()
    }

    fun checkedState(checkedValue: VT?): I {
        stateMapValue[INDEX_CHECKED_STATE] = checkedValue
        return self()
    }

    fun selectedState(selectedValue: VT?): I {
        stateMapValue[INDEX_SELECTED_STATE] = selectedValue
        return self()
    }

    open fun build(needRebuild: Boolean = false): I {
        if (stateResult == null || needRebuild) {
            stateResult = build()
        }
        return self()
    }
//    fun test() {
//        android.R.attr.state_checked
//    }

    protected fun self() = this as I


}