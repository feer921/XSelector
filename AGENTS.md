# AGENTS.md

## 基本协作约定

- 默认使用中文回答。
- 编写代码时优先考虑内存、性能和低版本 Android 兼容性。
- 新增功能模块时先明确职责边界，避免把 demo App 的实验代码反向耦合进库模块。
- 使用系统自带的 `git` 操作；不要回滚用户已有改动。

## 项目概览

- 这是一个 Groovy Gradle Android 多模块项目。
- `:TheXSelector` 是核心库模块，包名/namespace 为 `com.fee.thexselector`，负责用 Kotlin 链式 API 动态构建 `ColorStateList`、`StateListDrawable` 和 `GradientDrawable`。
- `:app` 是示例 App，包名/namespace 为 `com.fee.xselector`，用于演示 XSelector API，并包含登录模板、`LayoutInflater.Factory2` 拦截和 ViewDecorator 实验代码。
- `maven.properties` 与 `TheXSelector/maven_publish.gradle` 用于发布配置；发布脚本依赖本地 `maven-secret.properties`，不要把密钥写入仓库。

## 构建与验证

- `gradlew` 当前可能没有执行权限，可使用 `bash gradlew ...`，或使用本机已缓存的 Gradle 版本验证。
- 常用验证命令：
  - `bash gradlew :TheXSelector:compileDebugKotlin`
  - `bash gradlew :app:assembleDebug`
  - `bash gradlew :TheXSelector:testDebugUnitTest`
- 当前项目使用 AGP 9 系列写法：模块内使用 `namespace`、`compileSdk = ...`、`minSdk = ...`、`targetSdk = ...`。
- AGP 9 已内置 Kotlin 支持，不要再给模块应用 `kotlin-android` / `org.jetbrains.kotlin.android` 插件。
- `:app` 因依赖组合超过单 dex 64K 且保留 `minSdk 19`，已启用 AndroidX MultiDex，`MyApp` 继承 `MultiDexApplication`。

## 架构边界

- `TheXSelector` 只应依赖 Android SDK、Kotlin 标准库和轻量 AndroidX 注解，不要引入 AppCompat、Material、Glide 等 App/demo 依赖。
- `AbsSelector` 是单状态 selector 抽象，内部以 `stateMapValue` 保存状态到值的映射，子类通过 `build()` 输出最终资源。
- `ColorSelector` 面向 `TextView`/`EditText` 的文本或 hint 颜色，输出 `ColorStateList`。
- `DrawableSelector` 输出 `StateListDrawable` 并设置到 `View.background`。
- `ShapeSelector` 组合多个 `ShapeItem`，把每个状态下的 shape 构建为 `Drawable`。
- `ShapeItem` 是可变 builder，会缓存 `shapeItemResult`；复用同一个实例并修改参数后，调用 `into(view, true)` 或重新 `build()`。
- `StateItem` 支持一个 selector item 同时携带多个状态属性；当前主 selector API 仍以单状态快捷方法为主。

## 兼容性注意

- 保持库模块 `minSdk 19`，除非明确决定发布新主版本。
- 对需要 API 级别保护的调用使用 `Build.VERSION.SDK_INT` 判断，例如 `GradientDrawable.setPadding()` 当前仅在 API 29+ 调用。
- 升级 AndroidX/Material 时必须检查 AAR manifest 的 `uses-sdk`，避免无意抬高 `minSdk`。
- `compileSdk` 可以高于 `targetSdk`；升级 `targetSdk` 会引入新的运行时行为，应单独评估。

## 测试与质量

- 现有单元测试和仪器测试仍是模板用例；修改核心 builder/selector 行为时应补充 focused tests。
- 重点测试场景包括：默认状态追加顺序、空/非法颜色解析、`solidColor` 与 `gradient` 互斥、`needReBuild` 缓存刷新、不同状态 drawable 是否正确落到目标 View。
- 示例 App 中 `LayoutInflaterFactory2` 使用反射 hook，改动时优先做真机构建/运行验证，并避免影响库 API。

## 依赖升级原则

- 优先升级不会改变 `minSdk 19` 约束的稳定版本。
- 对跨主版本依赖保持谨慎，尤其是会增加方法数或带来迁移成本的库。
- 如果升级导致 64K、manifest merger、AAR metadata 或运行时兼容问题，优先用最小范围修复，不把示例 App 的约束传导给库模块。
