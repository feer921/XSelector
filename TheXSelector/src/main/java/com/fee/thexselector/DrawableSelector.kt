package com.fee.thexselector

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import androidx.annotation.ColorInt

/**
 * 构建[Drawable] 作为各状态的  Selector
 */
class DrawableSelector : AbsSelector<Drawable, View, Drawable, DrawableSelector>() {


    fun stateDef(@ColorInt defColor: Int) : DrawableSelector {
        return stateDef(ColorDrawable(defColor))
    }

    fun stateDef(defColorStr: String): DrawableSelector {
        return stateDef(parseColorStrToInt(defColorStr))
    }


    fun stateFocused(@ColorInt focusedColor: Int) : DrawableSelector {
        return stateFocused(ColorDrawable(focusedColor))
    }

    fun stateFocused(focusedColorStr: String): DrawableSelector {
        return stateFocused(parseColorStrToInt(focusedColorStr))
    }


    fun statePressed(@ColorInt pressedColor: Int) : DrawableSelector {
        return statePressed(ColorDrawable(pressedColor))
    }

    fun statePressed(pressedColorStr: String): DrawableSelector {
        return statePressed(parseColorStrToInt(pressedColorStr))
    }

    fun stateEnable(@ColorInt enableColor: Int) : DrawableSelector {
        return stateEnable(ColorDrawable(enableColor))
    }

    fun stateEnable(enableColorStr: String): DrawableSelector {
        return stateEnable(parseColorStrToInt(enableColorStr))
    }


    fun stateDisabled(@ColorInt disabledColor: Int) : DrawableSelector {
        return stateDisabled(ColorDrawable(disabledColor))
    }

    fun stateDisabled(disabledColorStr: String): DrawableSelector {
        return stateDisabled(parseColorStrToInt(disabledColorStr))
    }

    fun stateChecked(@ColorInt checkedColor: Int) : DrawableSelector {
        return stateChecked(ColorDrawable(checkedColor))
    }

    fun stateChecked(checkedColorStr: String): DrawableSelector {
        return stateChecked(parseColorStrToInt(checkedColorStr))
    }

    fun stateSelected(@ColorInt selectedColor: Int) : DrawableSelector {
        return stateSelected(ColorDrawable(selectedColor))
    }

    fun stateSelected(selectedColorStr: String): DrawableSelector {
        return stateSelected(parseColorStrToInt(selectedColorStr))
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