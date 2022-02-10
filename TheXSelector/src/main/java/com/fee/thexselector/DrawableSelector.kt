package com.fee.thexselector

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import androidx.annotation.ColorInt

/**
 * 构建[Drawable]
 */
class DrawableSelector : AbsSelector<Drawable, View, Drawable, DrawableSelector>() {


    fun defState(@ColorInt defColor: Int) : DrawableSelector {
        return defState(ColorDrawable(defColor))
    }

    fun defState(defColorStr: String): DrawableSelector {
        return defState(parseColorStrToInt(defColorStr))
    }


    fun focusedState(@ColorInt focusedColor: Int) : DrawableSelector {
        return focusedState(ColorDrawable(focusedColor))
    }

    fun focusedState(focusedColorStr: String): DrawableSelector {
        return focusedState(parseColorStrToInt(focusedColorStr))
    }


    fun pressedState(@ColorInt pressedColor: Int) : DrawableSelector {
        return pressedState(ColorDrawable(pressedColor))
    }

    fun pressedState(pressedColorStr: String): DrawableSelector {
        return pressedState(parseColorStrToInt(pressedColorStr))
    }

    fun enableState(@ColorInt enableColor: Int) : DrawableSelector {
        return enableState(ColorDrawable(enableColor))
    }

    fun enableState(enableColorStr: String): DrawableSelector {
        return enableState(parseColorStrToInt(enableColorStr))
    }


    fun disabledState(@ColorInt disabledColor: Int) : DrawableSelector {
        return disabledState(ColorDrawable(disabledColor))
    }

    fun disabledState(disabledColorStr: String): DrawableSelector {
        return disabledState(parseColorStrToInt(disabledColorStr))
    }

    fun checkedState(@ColorInt checkedColor: Int) : DrawableSelector {
        return checkedState(ColorDrawable(checkedColor))
    }

    fun checkedState(checkedColorStr: String): DrawableSelector {
        return checkedState(parseColorStrToInt(checkedColorStr))
    }

    fun selectedState(@ColorInt selectedColor: Int) : DrawableSelector {
        return selectedState(ColorDrawable(selectedColor))
    }

    fun selectedState(selectedColorStr: String): DrawableSelector {
        return selectedState(parseColorStrToInt(selectedColorStr))
    }

    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): Drawable? {
        val stateListDrawable = StateListDrawable()
        stateMapValue.forEach {entry ->
            stateListDrawable.addState(intArrayOf(entry.key),entry.value)
        }
        defStateValue?.let {
            stateListDrawable.addState(intArrayOf(),it)
        }
        return stateListDrawable
    }

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     * @param isNeedReBuild 是否需要重新构建出 [selector] 结果
     */
    override fun into(theEffectedView: View, isNeedReBuild: Boolean): DrawableSelector {
        build(isNeedReBuild)
        stateResult?.let {
            theEffectedView.background = it
        }
        return self()
    }
}