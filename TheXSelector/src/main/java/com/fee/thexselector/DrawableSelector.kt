package com.fee.thexselector

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View

/**
 *
 */
class DrawableSelector : AbsSelector<Drawable, View, Drawable, DrawableSelector>() {

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     */
    override fun into(theEffectedView: View) {
        build(false)
        stateResult?.let {
            theEffectedView.background = it
        }
    }

    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): Drawable? {
        val stateListDrawable = StateListDrawable()
//        stateListDrawable.addState()
        stateMapValue.forEach {entry ->
            stateListDrawable.addState(intArrayOf(entry.key),entry.value)
        }
        defStateValue?.let {
            stateListDrawable.addState(intArrayOf(),it)
        }
        return stateListDrawable
    }




}