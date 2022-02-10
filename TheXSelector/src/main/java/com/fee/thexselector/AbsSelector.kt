package com.fee.thexselector

import android.graphics.Color
import android.view.View

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:36<br>
 * <P>DESC:
 * 抽象选择器: 但该 selector 只对应 每个<item></item> 仅支持 一个 状态 state 属性
 *           即仅支持类 xxx.xml 的如此：<selector>
 *                                      <item android:state_checked="true" android:drawable="@color/colorPrimary" />
 *                                   </selector> 里的只有一个状态属性
 * 类泛型参数：[SR]最终本 Selector 构建出的结果类型；[V]将结果运用到的 视图类型；[VT]: ValueType即 单个<item>的结果类型
 *           [I] 本 selector 自身，方便 级联调用
 * </p>
 * ******************(^_^)***********************
 */
abstract class AbsSelector<SR, V : View, VT, I : AbsSelector<SR, V, VT, I>> : ISelector<SR, V,I> {
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

        fun parseColorStrToInt(colorStr: String?): Int {
            if (colorStr.isNullOrBlank()) {
                return 0 //透明
            }
            kotlin.runCatching {
                Color.parseColor(colorStr)
            }.onSuccess {
                return it
            }
            return 0
        }
    }

    protected var stateResult: SR? = null

    protected val stateMapValue = mutableMapOf<Int, VT?>()

    protected var defStateValue: VT? = null

    /**
     * 1、默认的状态: <item android:drawable="[defValue]" />
     */
    open fun defState(defValue: VT?): I {
        defStateValue = defValue
        return self()
    }

    /**
     * 2、处于焦点状态: <item android:state_focused="true" android:drawable="[focusedValue]" />
     */
    open fun focusedState(focusedValue: VT?): I {
        stateMapValue[INDEX_FOCUSED_STATE] = focusedValue
        return self()
    }

    /**
     * 3、处于点击状态: <item android:state_pressed="true" android:drawable="[pressedValue]" />
     */
    open fun pressedState(pressedValue: VT?): I {
        stateMapValue[INDEX_PRESSED_STATE] = pressedValue
        return self()
    }

    /**
     * 4、处于使能 enable 状态: <item android:state_enabled="true" android:drawable="[enableValue]" />
     */
    open fun enableState(enableValue: VT?): I {
        stateMapValue[INDEX_ENABLED_STATE] = enableValue
        return self()
    }

    /**
     * 5、处于使能 disabled 状态: <item android:state_enabled="false" android:drawable="[disabledValue]" />
     */
    open fun disabledState(disabledValue: VT?): I {
        stateMapValue[INDEX_DISABLED_STATE] = disabledValue
        return self()
    }

    /**
     * 6、处于选中 checked 状态: <item android:state_checked="true" android:drawable="[checkedValue]" />
     */
    open fun checkedState(checkedValue: VT?): I {
        stateMapValue[INDEX_CHECKED_STATE] = checkedValue
        return self()
    }

    /**
     * 7、处于选中 selected 状态: <item android:state_selected="true" android:drawable="[selectedValue]" />
     */
    open fun selectedState(selectedValue: VT?): I {
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