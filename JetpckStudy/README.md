# .

## Gradle 是什么

### gradle 版本与 gradle plugin 版本需要符合对应匹配关系

[详细参考文档](https://developer.android.google.cn/build/releases/gradle-plugin?hl=zh-cn#updating-gradle)

###  

- `compileSdkVersion`

是编译代码所使用的 sdk 版本

- `minSdkVersion`

是对 app 可运行的手机设备最小版本限制

- `targetSdkVersion`

是对 app 要运行的手机设备的目标版本的标识

- `buildToolsVersion`

是独立出来的一个东西，和上面三个都没关系，就是构建代码的工具的版本

**注意** `minSdkVersion` <= `targetSdkVersion` <= `compileSdkVersion`