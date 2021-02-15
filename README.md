# OpenDIY 开源的游戏王卡片制作工具

纯属无聊写的，顺便验证一下我之前写的声明式 swing UI 框架是否正常工作（当然该框架也是无聊时写的，但是谁让我正好有业务需要呢），其中的卡模均来自于网络，我也忘了是从哪里下载到的了，它们在我的硬盘里存在了好多年，平时也会拿出来做个卡啥的。

这个工具由纯粹的 Kotlin 开发，用以下方法编译即可（我没有将依赖库上传到 maven，而是以编译 jar 包形式提供，因为自娱自乐的东西没有必要上传）。

首先编译核心库 jar 包：

```shell
$ cd <path to project>
$ gradle clean jar
```

此时将得到一个名为 ```OpenDIY-0.0.1.jar``` 的文件，将其拷贝到 ```client/lib``` 内，然后编译可视化界面:

```shell
$ cd <path to project>/client
$ gradle clean shadowJar
```

此时将得到一个名为 ```Client.jar``` 的文件，使用以下命令运行之即可:

```shell
$ java -jar ./Client.jar
```

此时你将看到一个可视化的界面用于编辑卡片信息，同时也可以对所编辑的内容作出保存等操作。

- - -

### API 说明

```OpenDIY-0.0.1.jar``` 内提供了对于编辑卡片必要的 API，因此你可以完全不需要可视化界面（这个只是图个方便随手写的），而是完全利用 API 来制作卡片。说明如下：

|类名|描述||
|:--|:--|:--|
| com.rarnu.opendiy.YGOCard | 卡片信息类，对于一张卡片，所有的信息均存放于此 | |
| com.rarnu.opendiy.YGOProfile | 绘制卡片的配置类，它指出了卡片上的元素应该出现在哪里，以什么字体等绘制 | |
| com.rarnu.opendiy.YGOEnvironment | 环境检查类，用于对安装字体等进行必要的检查 | |

| 类名 | 方法名 | 描述 |
|:--|:--|:--|
| YGOCard | loadCard(file) | 从 file 读入一个卡片的信息 |
| YGOCard | saveCard(file) | 将卡片信息保存到 file |
| YGOCard | drawCard(profile, file) | 按 profile 绘制卡片，并输出到 file |
| Profile | load(file) | 从 file 读入一个配置信息 |
| Profile | save(file) | 将配置信息保存到 file |

- - -

截图

![](https://github.com/rarnu/kydiy/blob/main/screenshot/screen.png)