package com.fee.thexselector

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px

/**
 * @author feer921
 * 对应于 xx.shape.xml 编写 Shape
 * <shape xmlns:android="http://schemas.android.com/apk/res/android"
android:shape="rectangle"
>
1:
<corners android:radius="9dp"
android:topLeftRadius="9dp"
android:topRightRadius="9dp"
android:bottomLeftRadius="9dp"
android:bottomRightRadius="9dp"
/>
2:
<solid android:color="#fff" />
3:
<stroke android:color="#f00"
android:width="1dp"
android:dashWidth="1dp"
android:dashGap="2dp"
/>
4:
<gradient android:type="linear"
android:angle="80"
android:centerColor="#999"
android:centerX="9"
android:centerY="9"
android:endColor="#f00"
android:gradientRadius="8dp"
android:startColor="#800"
android:useLevel="false"
/>
5:
<padding android:left="1dp"
android:top="2dp"
android:right="5dp"
android:bottom="8dp"
/>
6:
<size android:width="119dp"
android:height="100dp"
/>
</shape>
 */
class ShapeItem : ISelector<Drawable, View, ShapeItem> {

    @ShapeType
    private var shapeType = GradientDrawable.RECTANGLE

    /**
     * 1、对应  <solid android:color="#fff" />属性
     */
    @ColorInt
    private var solidColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                gradientType = null // solid 与 gradient 是互斥的
            }
        }
    /**
     * @param targetShapeType 本 ShapeItem 的 形状类型；注：kotlin不支持 @Indef的注解限制
     */
    fun shape(@ShapeType targetShapeType: Int): ShapeItem{
        shapeType = targetShapeType
        return this
    }

    /**
     * 1、对应 配置 <solid android:color="[solidColor]" /> 属性
     */
    fun solidColor(@ColorInt solidColor: Int?): ShapeItem {
        this.solidColor = solidColor
        return this
    }
    fun solidColor(solidColorStr: String): ShapeItem {
        return solidColor(AbsSelector.parseColorStrToInt(solidColorStr))
    }

    @Px
    private var sizeWidth: Int? = null
    @Px
    private var sizeHeight: Int? = null

    /**
     * 2、对应 配置 <size android:width="[sizeWidth]"
    android:height="[sizeHeight]"
    />
     */
    fun sizeWidth(@Px sizeWidth: Int?,@Px sizeHeight: Int?): ShapeItem{
        this.sizeWidth = sizeWidth
        this.sizeHeight = sizeHeight
        return this
    }
    @Px
    private var paddingLeft: Int? = null

    @Px
    private var paddingTop: Int? = null

    @Px
    private var paddingRight: Int? = null

    @Px
    private var paddingBottom: Int? = null

    /**
     * 3、相当于配置 <padding android:left="[left]"
    android:top="[top]"
    android:right="[right]"
    android:bottom="[bottom]"
    />
     */
    fun padding(@Px left: Int,@Px top: Int,@Px right: Int, @Px bottom: Int): ShapeItem {
        paddingLeft = left
        paddingTop = top
        paddingRight = right
        paddingBottom = bottom
        return this
    }


    @Px
    private var cornerRadius: Float? = null

    @Px
    private var cornerTopLeftRadius: Float? = null

    @Px
    private var cornerTopRightRadius: Float? = null

    @Px
    private var cornerBottomRightRadius: Float? = null

    @Px
    private var cornerBottomLeftRadius: Float? = null

    /**
     * 4、相当于配置 <corners android:radius="[cornerRadius]"
    android:topLeftRadius="[topLeftRadius]"
    android:topRightRadius="[topRightRadius]"
    android:bottomLeftRadius="[bottomLeftRadius]"
    android:bottomRightRadius="[bottomRightRadius]"
    />
     */
    fun corners(
        @Px cornerRadius: Float?, @Px topLeftRadius: Float? = null, @Px topRightRadius: Float? = null,
        @Px bottomLeftRadius: Float? = null, @Px bottomRightRadius: Float? = null
    ): ShapeItem {
        this.cornerRadius = cornerRadius
        cornerTopLeftRadius = topLeftRadius ?: cornerRadius
        cornerTopRightRadius = topRightRadius ?: cornerRadius
        cornerBottomLeftRadius = bottomLeftRadius ?: cornerRadius
        cornerBottomRightRadius = bottomRightRadius ?: cornerRadius
        return this
    }

    // stroke ----------------- @start--------------

    /**
     * 描边 线颜色 可以没有(会为主题默认颜色)
     */
    @ColorInt
    private var strokeColor: Int? = null

    /**
     * 描边线宽度，如果 非大于 0，则描边 无意义
     */
    @Px
    private var strokeWidth: Int? = null

    @Px
    private var strokeDashWidth: Float? = null

    @Px
    private var strokeDashGap: Float? = null

    /**
     * 5、相当于配置： <stroke android:color="[strokeColor]"
    android:width="[strokeWidth]" //只有 >0 描边才有意义
    android:dashWidth="[strokeDashWidth]"
    android:dashGap="[strokeDashGap]"
    />
     */
    fun stroke(
        @ColorInt strokeColor: Int,//描边颜色是可以没有(会为主题默认颜色)
        @Px strokeWidth: Int,// 描边线宽
        @Px strokeDashWidth: Float? = null,
        @Px strokeDashGap: Float? = null
    ): ShapeItem {
        this.strokeColor = strokeColor
        this.strokeWidth = strokeWidth
        this.strokeDashWidth = strokeDashWidth
        this.strokeDashGap = strokeDashGap
        return this
    }
    // stroke ----------------- @end ---------------


    // gradient ----------------- @start ---------------
    /**
     * 渐变 类型
     */
    @GradientType
    private var gradientType: Int? = null
        set(value) {
            field = value
            if (value != null) {
                solidColor = null // solid 与 gradient 是互斥的
            }
        }
    /**
     * 渐变 方向
     */
    private var gradientOrientation: GradientDrawable.Orientation? = null
    /** 渐变角度 **/
//    private var gradientAngle: Int? = null
    private var gradientUseLevel: Boolean? = null
    private var gradientCenterX: Float? = null
    private var gradientCenterY: Float? = null
    /**
     * 渐变 半径
     */
    @Px
    private var gradientRadius: Float? = null
    @ColorInt
    private var gradientStartColor: Int? = null

    @ColorInt
    private var gradientCenterColor: Int? = null

    @ColorInt
    private var gradientEndColor: Int? = null

    /**
     * @param gradientType [GradientDrawable.LINEAR_GRADIENT] 线性渐变
     *                     [GradientDrawable.RADIAL_GRADIENT]  辐射渐变 (由中心向外扩展发射)
     *                  [GradientDrawable.SWEEP_GRADIENT]  扫描渐变(类似雷达的顺时/逆时 方向)扫描
     *
     * @param gradientOrientation 渐变(线性渐变)方向，当当前为 线性渐变时有效 <br/>
     *                   <ul>
     *                       <i> [GradientDrawable.Orientation.BOTTOM_TOP]</i>
     *                   </ul>
     * @param gradientRadius 渐变(辐射)半径，当当前为 辐射渐变时有效
     * 6、相当于配置 <gradient android:type="[gradientType]"
     *                     android:startColor="[startColor]"
    android:centerColor="[centerColor]"
    android:endColor="[endColor]"
    android:gradientRadius="[gradientRadius]"
    android:centerX="[centerX]"
    android:centerY="[centerY]"
    android:useLevel="[useLevel]"
    />


     */
    fun gradient(
        @GradientType gradientType: Int? = null,
        @ColorInt startColor: Int? = null,
        @ColorInt centerColor: Int? = null,
        @ColorInt endColor: Int? = null,
        gradientOrientation: GradientDrawable.Orientation? = null,
        @Px gradientRadius: Float? = null,
        centerX: Float? = null,
        centerY: Float? = null,
        useLevel: Boolean? = null
    ): ShapeItem {
        this.gradientType = gradientType
        gradientStartColor = startColor
        gradientCenterColor = centerColor
        gradientEndColor = endColor
        this.gradientOrientation = gradientOrientation
        this.gradientRadius = gradientRadius
        gradientCenterX = centerX
        gradientCenterY = centerY
        gradientUseLevel = useLevel
        return this
    }

    /**
     * 简便的 渐变(线性渐变类型): 只常用、有效属性：颜色、渐变方向
     */
    fun gradientLinear(@ColorInt startColor: Int? = null,
                       @ColorInt centerColor: Int? = null,
                       @ColorInt endColor: Int? = null,
                       gradientOrientation: GradientDrawable.Orientation? = null) : ShapeItem{
        return gradient(
            GradientDrawable.LINEAR_GRADIENT, startColor, centerColor, endColor,
            gradientOrientation
        )
    }

    /**
     * 简便的 渐变(辐射渐变类型): 只常用、有效属性：颜色、辐射半径、中心点 X、Y坐标
     */
    fun gradientRadial(@ColorInt startColor: Int? = null,
                       @ColorInt centerColor: Int? = null,
                       @ColorInt endColor: Int? = null,
                       @Px gradientRadius: Float? = null,
                       centerX: Float? = null,
                       centerY: Float? = null) : ShapeItem {
        return gradient(
            GradientDrawable.RADIAL_GRADIENT, startColor, centerColor, endColor, null,
            gradientRadius, centerX, centerY
        )
    }

    /**
     * 简便 渐变(扫描渐变)类型：只常用、有效属性：颜色、
     */
    fun gradientSweep(@ColorInt startColor: Int? = null,
                      @ColorInt centerColor: Int? = null,
                      @ColorInt endColor: Int? = null,
                      centerX: Float? = null,
                      centerY: Float? = null) : ShapeItem{
        return gradient(GradientDrawable.SWEEP_GRADIENT,startColor,centerColor,endColor,null,
            null,centerX,centerY)
    }
    // gradient ----------------- @end ---------------

    private var shapeItemResult: Drawable? = null
    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): Drawable? {
        val drawable = GradientDrawable()
        drawable.shape = shapeType
        //1: corner
        cornerRadius?.let {
            if (it >= 0) {
                drawable.cornerRadius = it
            }
        }
        val radius = cornerRadius ?: 0f
        var eightCornerRadius: FloatArray? = null
        if (cornerTopLeftRadius != null || cornerTopRightRadius != null || cornerBottomLeftRadius != null || cornerBottomRightRadius != null) {
            eightCornerRadius = FloatArray(8){index ->
                when (index) {//顺时针方向
                    0,1 -> {//左上角
                        cornerTopLeftRadius ?: radius
                    }
                    2,3 -> {//右上角
                        cornerTopRightRadius ?: radius
                    }
                    4,5 -> {//右下角
                        cornerBottomRightRadius ?: radius
                    }
                    6,7 -> {//左下角
                        cornerBottomLeftRadius ?: radius
                    }
//                    4,5 -> {
//                        cornerBottomLeftRadius ?: radius
//                    }
//                    6,7 -> {
//                        cornerBottomRightRadius ?: radius
//                    }
                    else -> {radius}
                }
            }
        }
        eightCornerRadius?.let {
            drawable.cornerRadii = it
        }
        //2、solid
        solidColor?.let { argb ->
            drawable.setColor(argb)
        }
        //3、stroke
        strokeWidth?.let { strokeW ->
            if (strokeW > 0) {//只有 描边宽 大于 0 才有意义
                drawable.setStroke(
                    strokeW,
                    strokeColor ?: 0,
                    strokeDashWidth ?: 0f,
                    strokeDashGap ?: 0f
                )
            }
        }
        //4、size
        if (sizeWidth != null && sizeWidth!! >0 && sizeHeight != null && sizeHeight!! > 0 ) {
            drawable.setSize(sizeWidth!!,sizeHeight!!)
        }
        //5、padding API >= 29
        if (Build.VERSION.SDK_INT >= 29) {
            if (paddingLeft != null || paddingTop != null || paddingRight != null || paddingBottom != null) {
                drawable.setPadding(
                    paddingLeft ?: 0,
                    paddingTop ?: 0,
                    paddingRight ?: 0,
                    paddingBottom ?: 0
                )
            }
        }
        //6、gradient
        val isConfigedGradient = when (gradientType) {
            GradientDrawable.LINEAR_GRADIENT -> {//设置了 线性渐变
                gradientOrientation?.let {
                    drawable.orientation = it
                }
                true
            }
            //<gradient  android:type="radial"
            // android:angle="80" 无效
            //android:useLevel="true" 时，会让渐变无效
            GradientDrawable.RADIAL_GRADIENT ->{// 设置了 辐射类型 渐变,
                gradientRadius?.let {
                    drawable.gradientRadius = it
                }
                true
            }
            GradientDrawable.SWEEP_GRADIENT -> {//设置了 扫描类型 渐变
                true
            }
            else -> { false }
        }
        if (!isConfigedGradient) {
            return drawable
        }
        gradientType?.let {
            drawable.gradientType = it
        }
        if (gradientCenterX != null && gradientCenterY != null) {
            drawable.setGradientCenter(gradientCenterX!!, gradientCenterY!!)
        }
        if (gradientStartColor != null || gradientCenterColor != null || gradientEndColor != null) {
            val gradientColors = intArrayOf(
                gradientStartColor ?: -1, gradientCenterColor ?: -1,
                gradientEndColor ?: -1
            ).filter {
                it != -1
            }.toIntArray()
            drawable.colors = gradientColors
        }
        gradientUseLevel?.let {
            drawable.useLevel = it
        }
        return drawable
    }

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     * @param isNeedReBuild 是否需要重新构建出 [selector] 结果
     */
    override fun into(theEffectedView: View, isNeedReBuild: Boolean): ShapeItem {
        if (ownerSelector != null) {
            ownerSelector?.into(theEffectedView,isNeedReBuild)
            return this
        }
        if (shapeItemResult == null || isNeedReBuild) {
            shapeItemResult = build()
        }
        shapeItemResult?.let {
            theEffectedView.background = it
        }
        return this
    }

    private var ownerSelector: DrawableSelector? = null

    fun attachSelector(ownerSelector: DrawableSelector?) : ShapeItem{
        this.ownerSelector = ownerSelector
        return this
    }

    /**
     * 1、本 [ShapeItem] 作为 [Selector]的 默认状态 item 项
     */
    fun asStateDefOfSelector() : ShapeItem {
        ownerSelector?.stateDef(build())
        return this
    }

    fun asStateSelectedOfSelector() : ShapeItem {
        ownerSelector?.stateSelected(build())
        return this
    }

    fun asStateCheckedOfSelector() : ShapeItem {
        ownerSelector?.stateChecked(build())
        return this
    }

    fun asStateEnableOfSelector() : ShapeItem {
        ownerSelector?.stateEnable(build())
        return this
    }

    fun asStateDisableOfSelector() : ShapeItem {
        ownerSelector?.stateDisabled(build())
        return this
    }

    fun asStatePressedOfSelector(): ShapeItem  {
        ownerSelector?.statePressed(build())
        return this
    }

    fun asStateFocusedOfSelector(): ShapeItem{
        ownerSelector?.stateFocused(build())
        return this
    }
}