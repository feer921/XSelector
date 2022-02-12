package com.fee.thexselector

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 对应 Selector 中 每个 item 使用 <shape> 的场景，即：
 * <selector xmlns:android="http://schemas.android.com/apk/res/android" >

        <item android:state_pressed="true">
             <shape android:shape="rectangle" >

             </shape>
        </item>
<!--  最后一个表示 默认状态下的 值   -->
        <item >
            <shape android:shape="rectangle">

            </shape>
        </item>
</selector>

 * </p>
 * ******************(^_^)***********************
 */
class ShapeSelector : AbsSelector<Drawable, View, ShapeItem, ShapeSelector>() {

    fun stateDef(defShapeItemConfig: ShapeItem.() -> Unit) : ShapeSelector {
        ShapeItem().run {
            defShapeItemConfig()
            stateDef(this)
        }
        return this
    }


    fun stateFocused(focusedItemConfig: ShapeItem.() -> Unit) : ShapeSelector {
        ShapeItem().run {
            focusedItemConfig()
            stateFocused(this)
        }
        return this
    }


    fun statePressed(pressedItemConfig: ShapeItem.() -> Unit): ShapeSelector {
        ShapeItem().run {
            this.pressedItemConfig()
            statePressed(this)
        }
        return this
    }

    fun stateEnable(enableItemConfig: ShapeItem.() -> Unit) : ShapeSelector {
        ShapeItem().run {
            this.enableItemConfig()
            stateEnable(this)
        }
        return this
    }


    fun stateDisabled(disabledItemConfig: ShapeItem.() -> Unit) :ShapeSelector {
        ShapeItem().run {
            this.disabledItemConfig()
            stateDisabled(this)
        }
        return this
    }

    fun stateChecked(checkedItemConfig: ShapeItem.() -> Unit): ShapeSelector {
        ShapeItem().run {
            this.checkedItemConfig()
            stateChecked(this)
        }
        return this
    }

    fun stateSelected(selectedItemConfig: ShapeItem.() -> Unit):ShapeSelector {
        ShapeItem().run {
            this.selectedItemConfig()
            stateSelected(this)
        }
        return this
    }

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     * @param isNeedReBuild 是否需要重新构建出 [selector] 结果
     */
    override fun into(theEffectedView: View, isNeedReBuild: Boolean): ShapeSelector {
        build(isNeedReBuild)
        stateResult?.let {
            theEffectedView.background = it
        }
        return self()
    }

    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): Drawable? {
        var resultDrawable: StateListDrawable? = null
        stateMapValue.forEach { entry ->
            entry.value?.build()?.let { shapeItemDrawable -> //每个 ShapeItem构建的最终资源
                if (resultDrawable == null) {
                    resultDrawable = StateListDrawable()
                }
                resultDrawable?.addState(intArrayOf(entry.key),shapeItemDrawable)
            }
        }
        //最后加入默认的 item
        defStateValue?.build()?.let {
            if (resultDrawable == null) {
                resultDrawable = StateListDrawable()
            }
            resultDrawable?.addState(intArrayOf(),it)
        }
        return resultDrawable
    }
}