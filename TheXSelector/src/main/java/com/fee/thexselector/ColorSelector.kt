package com.fee.thexselector

import android.content.res.ColorStateList
import android.widget.TextView

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:50<br>
 * <P>DESC:
 * 可配置各种状态下 显示不同颜色的 选择器
 * 应用于TextView的字体
 * </p>
 * ******************(^_^)***********************
 */
class ColorSelector : WithColorSelector<ColorStateList, TextView, ColorSelector>() {
    private var isColor4Hint = false

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     * @param isNeedReBuild 是否需要重新构建出 [selector] 结果
     */
    override fun into(theEffectedView: TextView, isNeedReBuild: Boolean): ColorSelector {
        build(isNeedReBuild)
        if (stateResult != null) {
            if (isColor4Hint) {
                theEffectedView.setHintTextColor(stateResult)
            } else {
                theEffectedView.setTextColor(stateResult)
            }
        }
        return self()
    }

    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): ColorStateList? {
        /*
        * int[][] states = new int[5][];
        states[0] = new int[]{-android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_pressed};
        states[2] = new int[]{android.R.attr.state_selected};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{};
        * */
        var colorStateList: ColorStateList? = null
        var stateCaseCount = stateMapValue.size
        if (stateCaseCount > 0) {
            val finalStateMapValue =
                if (isAllDefColorTransparent) stateMapValue else stateMapValue.filterValues { colorValue ->
                    colorValue != null
                }
            stateCaseCount = finalStateMapValue.size
            if (stateCaseCount > 0) {
                val stateArray: Array<IntArray?> = Array(stateCaseCount + 1) {
                    intArrayOf(0)
                }
                var arrayIndex = 0
                val stateColors = IntArray(stateCaseCount + 1)
                finalStateMapValue.forEach{
                    stateArray[arrayIndex] = intArrayOf(it.key)
                    stateColors[arrayIndex] = it.value ?: 0
                    arrayIndex++
                }
                //追加最后一个默认的值
                stateArray[arrayIndex] = intArrayOf()
                stateColors[arrayIndex] = defStateValue ?: 0//todo 是否透明合适？？
                colorStateList = ColorStateList(stateArray, stateColors)
            }
        }
        return colorStateList
    }


    fun isColor4HintText(isColor4HintText: Boolean): ColorSelector {
        this.isColor4Hint = isColor4HintText
        return self()
    }

}