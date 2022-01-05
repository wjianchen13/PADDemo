# Play Asset Delivery 

# 步骤
1.创建一个工程
2.在同级目录新建一个名为install_time_asset_pack的Modle目录，Asset Pack 名称规范、必须以字母开头，并且只能包含字母、数字和下划线。
3.install-time-asset_pack添加build.gradle文件并添加以下代码。需要指定 Asset Pack 的名称，并且只能指定一种分发类型。
```Java
// In the asset pack’s build.gradle file:
plugins {
    id 'com.android.asset-pack'
}

assetPack {
    packName = "install_time_asset_pack" // Directory name for the asset pack
    dynamicDelivery {
        deliveryType = "install-time"
    }
}
```
4.然后app/settings.gradle加上如下配置
include ':install_time_asset_pack' 
5.接着在app级别的build.gradle android字段内配置依赖属性
```Java
    //指定了install-time模式，其他两种模式大同小异
    //命名要和packName一致
    assetPacks = [":install_time_asset_pack"]
```
6.最后app/build.gradle 添加远程依赖库
```Java
    implementation 'com.google.android.play:core:1.10.0'
```

7. 生成aab并安装
生成aab
gradlew bundleRelease

合并apks
java -jar bundletool-all-1.7.0.jar build-apks --bundle=app-debug.aab --output=test1.apks
java -jar bundletool-all-1.7.0.jar build-apks --bundle=app-release.aab --output=test1.apks

安装测试aab
java -jar bundletool-all-1.7.0.jar  install-apks --apks=test1.apks

8.通过上述方式安装到手机的apks可以直接assets是否正常，正常点击安装是没法显示assets目录下的文件的。 

# 遇到问题
1.新建android studio项目，编译提示
com.android.tools.r8.a: Invoke-customs are only supported starting with Android O (--min-api 26)
在app build.gradle的android里面添加下面代码
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}

# 参考文档
https://blog.csdn.net/baidu_31156101/article/details/118980969
https://developer.android.google.cn/guide/playcore/asset-delivery/integrate-native