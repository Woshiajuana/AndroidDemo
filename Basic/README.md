## 申请权限

- 6.0 之前

清单文件权限配置

- 6.0 之后

运行时权限管理，需要动态申请

### 步骤

1. 检查 App 是否开启了指定权限，调用 `ContextCompat` 的 `checkSelfPermission` 方法
2. 请求系统弹出，以便用户选择是否开启权限，调用 `ActivityCompat` 的 `requestPermissions` 方法，即可命令系统自动弹出权限申请窗口。
3. 判断用户的权限选择结果，重写权限请求回调方法 `onRequestPermissionsResult` ，在该方法内部处理用户的权限选择结果。