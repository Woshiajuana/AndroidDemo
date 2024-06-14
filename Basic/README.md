## 申请权限

- 6.0 之前

清单文件权限配置

- 6.0 之后

运行时权限管理，需要动态申请

### 步骤

1. 检查 App 是否开启了指定权限，调用 `ContextCompat` 的 `checkSelfPermission` 方法
2. 请求系统弹出，以便用户选择是否开启权限，调用 `ActivityCompat` 的 `requestPermissions` 方法，即可命令系统自动弹出权限申请窗口。
3. 判断用户的权限选择结果，重写权限请求回调方法 `onRequestPermissionsResult` ，在该方法内部处理用户的权限选择结果。

## ContentObserver 监听短信

内容观察期 `ContentObserver` 给目标内容注册一个观察期，目标内容的数据
一旦发生变化，观察期规定好的动作马上触发。



## 广播

- BroadcastReceiver

### 广播的收发过程分为三个步骤

- 发送标准广播
- 定义广播接收器
- 开关广播接收器

### 回到桌面与切到任务列表

- 需要接听系统广播 `Intent.ACTION_CLOSE_SYSTEM_DIALOGS`，从收到的广播意图中获取原因 `reason` 字段，该字段值为 `homekey` 时表示回到桌面，值为 `recentapps` 时表示打开了任务列表。
- 调用 `enterPictureInPictureMode` 方法进入画中画模式
- 重写活动页面的 `onPictureInPictureModeChanged` 方法，补充进入画中画模式或退出画中画模式时的处理逻辑；