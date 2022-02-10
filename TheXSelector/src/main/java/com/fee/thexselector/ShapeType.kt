package com.fee.thexselector

import android.graphics.drawable.GradientDrawable
import androidx.annotation.IntDef

/**
 *  注解 [ShapeItem] 的形状类型
 */
@IntDef(GradientDrawable.RECTANGLE, GradientDrawable.LINE, GradientDrawable.OVAL, GradientDrawable.RING)
@Retention(AnnotationRetention.SOURCE)
annotation class ShapeType()

/**
 * 注解 [ShapeItem] 的 shape 渐变类型
 */
@Target(AnnotationTarget.FIELD,AnnotationTarget.VALUE_PARAMETER)
@IntDef(GradientDrawable.LINEAR_GRADIENT,GradientDrawable.RADIAL_GRADIENT,GradientDrawable.SWEEP_GRADIENT)
@Retention(AnnotationRetention.SOURCE)
annotation class GradientType{

}
