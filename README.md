# BC-NETTYGO

#### 项目介绍
   基于netty的高性能游戏框架，结合spring全家桶的优势，优化简易开发，让开发人员可以更致力于业务开发。

**【backStageServer】**： 
    暂时未开发。


**【core】**  ：
    核心基础库，支持http socket通信处理，路由。以及房间概念，前置处理器，简易安全框架。


**【eurekaServer】** ：
     springcloud的eureka监听启动jar


**【hotload】** ：
    热更新工具启动jar


**【pay】** ：
     各种渠道支付的工具jar。暂时未开发。


**【httpServer】**  ：  core包的http通信使用demo


**【socketServer】**： 
    core包的socket通信的简单demo
	
<br /><br /><br />
#### 软件架构

netty - springboot - spirngCloud - mybatis

集成了redis、rabbitMQ，可直接使用。


<br /><br /><br />
#### 安装教程

1. 使用idea打开项目，core项目下直接maven-install打包生成core基础jar
2. 在需要的项目中导入jar

<br /><br /><br />
#### 使用说明

1. 在项目中复制对应协议demo中的mode包，和RunApp.  mode是nettyGo需要配置的模板类，只需要在上面进行修改就好了。

2. 填写好对应的application-dev.properties配置文件，以及配置好mode包中的init几个类。

3. runAPP启动项目即可（两个demo【httpServer/socketServer】 可直接启动）

4. [详细API参考后续wiki](https://gitee.com/huangxinyu/BC-NETTYGO/wikis)

<br /><br /><br />
#### 推荐使用场景
一般游戏业务以及前后端分离的web项目。

