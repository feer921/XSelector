# XSelector
给不同的View设置不同状态下背景等资源,减少 xx_shape.xml定义

# 依赖

```groovy
implementation 'io.github.feer921:XSelector:1.1' //注意该版本需要项目工程使用的　Kotlin 版本在　1.8.x及以上
```

如果项目工程的所使用的　Kotlin　版本在　1.8 以下，则可以依赖下面的版本

```groovy
implementation 'io.github.feer921:XSelector:1.1-kotlin16'
```



#　使用

	## 给**TextView** 设置文字颜色相关的状态切换

- 对应于　TextView　的　setTextColor() 方法

```kotlin
val tvName: TextView
val tvName2: TextView
XSelector.me.colorSelector() //选择颜色相关的　Selector
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
XSelector.me.colorSelector() //选择颜色相关的　Selector
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

