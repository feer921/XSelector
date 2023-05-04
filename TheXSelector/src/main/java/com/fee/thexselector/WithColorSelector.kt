package com.fee.thexselector

import android.content.res.Resources
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.NonNull

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/18<br>
 * Time: 14:22<br>
 * <P>DESC:
 * Selector类型之： 依据各种状态时显示不同的颜色
 * </p>
 * ******************(^_^)***********************
 */
abstract class WithColorSelector<SR,V: View, I: WithColorSelector<SR, V, I>> : AbsSelector<SR,V,Int,I> (){

    /**
     * 当设置的颜色为空，或者解析失败时，是否默认使用透明颜色？
     * def: false
     * 使用时可以设置某种状态时的颜色为 null，或者解析失败时，则不添加到最终的 状态集中,这样方便
     */
    protected var isAllDefColorTransparent = false

    /**
     * 默认状态时的颜色
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun defState(@NonNull colorStr: String): I {
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isAllDefColorTransparent) 0 else null
            }
        }
        stateDef(paresedColorInt)
        return self()
    }
    /**
     * 默认状态时的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun defStateWithColorRes(@ColorRes colorRes: Int): I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        stateDef(colorValue ?: if (isAllDefColorTransparent) 0 else null)
        return self()
    }

    /**
     * 获取焦点时的状态的颜色(通过16进制颜色指定)
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun focusedState(colorStr: String) :I{
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isAllDefColorTransparent) 0 else null
            }
        }
        stateFocused(paresedColorInt)
        return self()
    }

    /**
     * 获取焦点时的状态的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun focusedStateWithColorRes(@ColorRes colorRes: Int): I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        stateFocused(colorValue ?: if (isAllDefColorTransparent) 0 else null)
        return self()
    }

    /**
     * 被点击时的状态的颜色(通过16进制颜色指定)
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun pressedState(colorStr: String): I {
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if(isAllDefColorTransparent)0 else null
            }
        }
        statePressed(paresedColorInt)
        return self()
    }

    /**
     * 被点击时的状态的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun pressedStateWithColorRes(@ColorRes colorRes: Int):I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        statePressed(colorValue ?: if(isAllDefColorTransparent)0 else null)
        return self()
    }

    /**
     * 使能时的状态的颜色(通过16进制颜色指定)
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun enableState(colorStr: String): I {
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isAllDefColorTransparent) 0 else null
            }
        }
        stateEnable(paresedColorInt)
        return self()
    }

    /**
     * 使能时的状态的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun enableStateWithColorRes(@ColorRes colorRes: Int):I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        stateEnable(colorValue ?: if(isAllDefColorTransparent)0 else null)
        return self()
    }

    /**
     * 禁用时的状态的颜色(通过16进制颜色指定)
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun disabledState(colorStr: String): I {
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isAllDefColorTransparent) 0 else null
            }
        }
        stateDisabled(paresedColorInt)
        return self()
    }

    /**
     * 禁用时的状态的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun disabledStateWithColorRes(@ColorRes colorRes: Int):I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        stateDisabled(colorValue ?: if(isAllDefColorTransparent)0 else null)
        return self()
    }

    /**
     * 选中[checked]时的状态的颜色(通过16进制颜色指定)
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun checkedState(colorStr: String): I {
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isAllDefColorTransparent) 0 else null
            }
        }
        stateChecked(paresedColorInt)
        return self()
    }

    /**
     * 选中[checked]时的状态的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun checkedStateWithColorRes(@ColorRes colorRes: Int):I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        stateChecked(colorValue ?: if(isAllDefColorTransparent)0 else null)
        return self()
    }
    /**
     * 选中[selected]时的状态的颜色(通过16进制颜色指定)
     * @param colorStr 16进制的颜色；eg.: 0xffff0000
     */
    fun selectedState(colorStr: String): I {
        val paresedColorInt = colorStr.let {
            try {
                if (TextUtils.isEmpty(colorStr)) {
                    return@let null//就不用去解析了
                }
                Color.parseColor(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isAllDefColorTransparent) 0 else null
            }
        }
        stateSelected(paresedColorInt)
        return self()
    }

    /**
     * 选中[selected]时的状态的颜色(通过Color资源指定)
     * @param colorRes Color资源的资源ID；eg.: R.color.white
     */
    fun selectedStateWithColorRes(@ColorRes colorRes: Int): I {
        val colorValue = peekResource()?.let {
            try {
                it.getColor(colorRes)
            } catch (ex: Exception) {
                ex.printStackTrace()
                0
            }
        }
        val finalColor: Int? = colorValue ?: if (isAllDefColorTransparent) 0 else null
        stateSelected(finalColor)
        return self()
    }

    /**
     * 设置让 未设置的状态的 状态值时 是否 需要使用透明 来代替
     */
    fun isAllDefColorTransparent(isAllDefColorTransparent: Boolean): I {
        this.isAllDefColorTransparent = isAllDefColorTransparent
        return self()
    }

    protected fun peekResource() :Resources?{
        return XSelector.me.getContext()?.resources
    }
}