# XSelector
给不同的View设置不同状态下背景等资源,减少 **Drawable** 文件夹下　xx_shape.xml ,xx_selector.xml 文件定义

# 前言

我们在做 **Android**　项目时，UI设计师经常会给界面上的相关控件元素设计各种的颜色、背景、点击效果，通常我们会使用　**Android　** 的 shape/selector xml来定义这些各种状态下的资源，如下图所示：

![新建shape/selector xml资源文件](https://github.com/feer921/XSelector/blob/main/arts/img_1.png)

但是随着项目的开发进程，我们发现使用这种方式所定义的资源文件越来越多，尤其是项目组各开发人员在未沟通的情况下，可能对于相同的效果的背景资源在不知他人已经有定义过　xx.shape.xml/xx.selector.xml　的情况下，又会重复新建定义资源文件!!!

几个痛点：

- 背景(shape),各状态效果(selector) 的资源文件随着项目的进程越来越多
- 每当设计那边更改一个UI元素的颜色、背景、如果直接改资源文件的情况下有可能影响到其他也应用了的控件，如果不改只能又新建资源文件
- UI设计所设计的效果大体相同的情况下，如果光一个属性的不同(如：圆角1,2,3,4,5,n...像素)，则使用资源文件的情况下即要定义对应个数的资源文件，相当繁琐
- 所建的　xx.shape.xml / xx.selector.xml 不方便命名(程序员脑壳疼)

总之一句话，使用资源文件( xx.shape.xml/xx.selector.xml )的方案会造成　**drawable资源文件夹** 下文件越来越多，定义繁琐、杂乱无章，重复、命名困难、查找费劲,哎...

多到密集恐怖：

![冰山一角](https://github.com/feer921/XSelector/blob/main/arts/img_2.png)

解决：程序员面对UI设计需求的无限变更和差异化，除了沟通协定设计遵循多使用、设计主体效果，以避免太多差异化，但五彩斑斓的黑总是产品和设计师所孜孜追求的效果，因而使用代码/程序　的方式动态变更来应对仿佛是最优解，以变制变。

本框架原理即为使用代码的方式　高度抽象 解构　我们在定义　xx_shape.xml / xx_selector.xml 的过程，比如框架中　AbsSelector 类即为　xx_selector.xml 定义文件的基础抽象　解构

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >　//对应　AbsSelector
    <item android:state_checked="true"　
        android:state_enabled="true"
        android:state_selected="true"
        android:state_pressed="true"
        android:state_focused="false"
        android:drawable="@color/colorPrimary" />

    <item android:state_pressed="true">
        <shape android:shape="rectangle" >

        </shape>
    </item>
    <!--  最后一个表示 默认状态下的 值   -->
    <item android:drawable="@color/colorAccent" />
</selector>
```

上面的具体对应　实现类：ShapeSelector。

再比如框架中　ShapeItem 类即为　xx_shape.xml 定义文件的具体解构抽象：

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--
 android:shape="rectangle"
 android:shape="line"
 android:shape="oval"
 android:shape="ring"
-->
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle"
    >

<!--    <solid android:color="#fff"-->
<!--        />-->

    <stroke
        android:color="#f00"
        android:width="1dp"
        android:dashWidth="1dp"
        android:dashGap="2dp"
        />

<!--  有 层级先后  -->
    <gradient
        android:type="radial"
        android:centerColor="#999"
        android:endColor="#f00"
        android:startColor="#800"
        android:gradientRadius="80dp"
        />

    <padding android:left="1dp"
        android:top="2dp"
        android:right="5dp"
        android:bottom="8dp"
        />

    <corners android:radius="9dp"
        android:topLeftRadius="9dp"
        android:topRightRadius="9dp"
        android:bottomLeftRadius="9dp"
        android:bottomRightRadius="9dp"
        />

    <size android:width="119dp"
        android:height="100dp"
        />

</shape>
```



只不过框架使用精巧的封装让程序员在使用时更丝滑顺手,短短数行代码即完成等效资源文件定义效果。

# 依赖

```groovy
implementation 'io.github.feer921:XSelector:1.2' //注意该版本需要项目工程使用的　Kotlin 版本在　1.8.x及以上
```

如果项目工程的所使用的　Kotlin　版本在　1.8 以下，则可以依赖下面的版本

```groovy
implementation 'io.github.feer921:XSelector:1.2-kotlin16'
```

## 效果图

![简单效果](https://github.com/feer921/XSelector/blob/main/arts/img.png)

效果图所对应的示例代码：

```kotlin
			 //颜色　Selector
        mViewBinding.tv1.isClickable = true
        XSelector.colorSelector()
            .stateDef(Color.BLACK)
            .statePressed(Color.RED)
            .into(mViewBinding.tv1)

        //背景
        XSelector.colorSelector()
            .stateDef(Color.CYAN)
            .statePressed(Color.RED)
            .into(mViewBinding.tv2)
            .withShapeItem()
            .solidColor(Color.BLUE)
//            .corners(20f)
            .into(mViewBinding.tv2)

        //各种状态
        mViewBinding.tv3.isClickable = true
        XSelector.shapeSelector()
            .stateDef {
                solidColor(Color.YELLOW)
                corners(30f)
                stroke(Color.BLACK,1)

            }
            .statePressed {
                solidColor(Color.GREEN)
                corners(30f)
            }
            .into(mViewBinding.tv3)

        //圆形
        XSelector.shapeItem()
            .shape(GradientDrawable.OVAL)
            .solidColor(Color.GRAY)
            .into(mViewBinding.tv4)
```

# 用法：

## 给**TextView** 设置文字颜色相关的状态切换

- 对应于　TextView　的　setTextColor() 方法

```kotlin
val tvName: TextView
val tvName2: TextView
XSelector.colorSelector() //选择颜色相关的　Selector
            .stateDef(Color.YELLOW) //配置默认的文本颜色
            .statePressed(Color.RED)//配置点击　TextView　时的颜色为红包
            .stateDisabled(Color.GRAY)//配置　TextView　不使能时的颜色为灰色
						.into(tvName)　//配置进　TextView　控件
						.into(tvName2)　//可以一直配置给其他的　TextView，达到复用
```

## 给**EditText** 设置提示性文字的颜色相关的状态切换

- 对应于　TextView 中的　setHintTextColor()方法

```kotlin
val tvName: EditText
val tvName2: EditText
XSelector.colorSelector() //选择颜色相关的　Selector
            .stateDef(Color.YELLOW) //配置默认的文本颜色
            .statePressed(Color.RED)//配置点击　TextView　时的颜色为红包
            .stateDisabled(Color.GRAY)//配置　TextView　不使能时的颜色为灰色
						.isColor4HintText(true) //此种场景下，主要是加上这一行配置一下当前的文字颜色是配置给　提示文字的
						.into(tvName)　//配置进　TextView　控件
						.into(tvName2)　//可以一直配置给其他的　TextView，达到复用
```



## 给 **View** 设置背景状态切换

- 对应于　View　的 setBackground(Drawable background)  ||　setBackgroundDrawable(Drawable background) 方法

```kotlin
val tvName: TextView
val view1: View
val view2: View
XSelector.drawableSelector()
            .statePressed(Color.GREEN) //配置点击时背景为　颜色
            .stateDef(Color.GRAY)　//配置默认的背景　为灰色
            .into(tvName)　// 配置进　控件
						.into(view1) //也可以一直配置给其他的　View
						.statePressed(null) //还可以继续改变某个状态值后
						.into(view2,true) //再配置给其他的　View,这里　true参数表示　需要重新构建后再配置给　view2控件
```

## 给　View　设置只需要默认状态

```kotlin
ShapeItem()//XSelector.shapeItem().
            .solidColor(Color.RED)//默认填充颜色
            .corners(20f) //下面其他状态下：复用了 圆角属性
//            .corners(null,20f,20f,20f,5f)
//            .gradientRadial(Color.GRAY,Color.CYAN,Color.RED,160f)
            .stroke(Color.BLACK,8,3f,3f)//边框
            .attachSelector(DrawableSelector())//依附一下　Selector类型，则可以构建出各状态下的资源
            .asStateDefOfSelector()//上面的属性参数构建Selector的默认状态资源
            .solidColor(Color.YELLOW)//变更了填充颜色
            .stroke(Color.BLUE,9,3f,3f)
            .asStatePressedOfSelector()//上面的属性构建Selector的点击状态下的资源
            .into(login)//设置给控件
```

# TODO

1. 增加自定义属性，方便在编写布局　xml　中控件(但可能需要使用本框架提供的继承自系统原生控件的控件)时即可时时预览效果　
