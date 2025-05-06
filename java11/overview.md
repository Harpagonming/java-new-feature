## Java 11新特性

### 基于嵌套的访问控制

引入嵌套类访问控制，该特性解决了内部类与外部类之间访问控制的问题。

### 新增String API

|    方法名    | 描述                |
|:---------:|:------------------|
| isBlank() | 检查字符串是否为空或仅包含空白字符 |
|  lines()  | 分割获取字符串流（Stream）  |
|  strip()  | 去除字符串首尾的空白字符      |
| repeat(n) | 复制字符串             |

### 全新的 HTTP 客户端 API

新的HTTP客户端API（HttpClient）被标准化并正式成为Java标准库的一部分。

### 局部变量类型推断的升级

可以使用var关键字来声明Lambda表达式输入参数的类型，允许在Lambda参数上使用注解或者省略类型。

### Epsilon—低开销垃圾回收器

Epsilon
垃圾回收器的目标是开发一个控制内存分配，但是不执行任何实际的垃圾回收工作。它提供一个完全消极的GC实现，分配有限的内存资源，最大限度的降低内存占用和内存吞吐延迟时间。
> 摘自：https://pdai.tech/md/java/java8up/java11.html

### ZGC：可伸缩低延迟垃圾收集器

ZGC即Z Garbage Collector（垃圾收集器或垃圾回收器），这应该是Java 11中最为瞩目的特性，没有之一。ZGC是一个可伸缩的、低延迟的垃圾收集器，主要为了满足如下目标进行设计：

* GC停顿时间不超过10ms
* 即能处理几百MB的小堆，也能处理几个TB的大堆
* 应用吞吐能力不会下降超过15%（与G1回收算法相比）
* 方便在此基础上引入新的GC特性和利用colord
* 针以及Load barriers优化奠定基础
* 当前只支持Linux/x64位平台停顿时间在10ms以下，10ms其实是一个很保守的数据，即便是10ms这个数据，也是GC调优几乎达不到的极值。根据SPECjbb
  2015的基准测试，128G的大堆下最大停顿时间才1.68ms，远低于10ms，和G1算法相比，改进非常明显。
  不过目前 ZGC 还处于实验阶段，目前只在 Linux/x64 上可用，如果有足够的需求，将来可能会增加对其他平台的支持。同时作为实验性功能的
  ZGC 将不会出现在 JDK 构建中，除非在编译时使用 configure 参数： --with-jvm-features=zgc 显式启用。

在实验阶段，编译完成之后，已经迫不及待的想试试ZGC，需要配置以下JVM参数，才能使用ZGC，具体启动ZGC参数如下：

~~~
-XX：+ UnlockExperimentalVMOptions -XX：+ UseZGC -Xmx10g
~~~

其中参数：-Xmx是ZGC收集器中最重要的调优选项，大大解决了程序员在JVM参数调优上的困扰。ZGC是一个并发收集器，必须要设置一个最大堆的大小，应用需要多大的堆，主要有下面几个考量：

* 对象的分配速率，要保证在GC的时候，堆中有足够的内存分配新对象。
* 一般来说，给ZGC的内存越多越好，但是也不能浪费内存，所以要找到一个平衡。

> 摘自：https://pdai.tech/md/java/java8up/java11.html

### 废弃 Nashorn JavaScript 引擎

Nashorn JavaScript引擎最初是在JDK 8中引入的，用于取代Rhino脚本引擎，它允许在JVM上执行和调用JavaScript代码。但是随着Java
11的到来标志着Nashorn JavaScript引擎的废弃。

### 增加 Files API

Java 11 为 Files 类增加了两个非常有用的实用方法，进一步简化了文件读写的操作：readString() 和 writeString()。

Files.readString(Path path)：读取一个文件的所有内容并将其返回为一个字符串。该方法非常适用于一次性读取整个文件。

Files.writeString(Path path, CharSequence csq, OpenOption... options)：将一个字符串写入到文件中。
这两个方法使得读写文件变得更加方便。

### Optional API 增强

新增了一个方法：

|    方法名    | 描述                           |
|:---------:|:-----------------------------|
| isEmpty() | 判断容器是否为空，如果包含的值不存在，则返回 true。 |

### 飞行记录器（Flight Recorder）

飞行记录器（Java Flight Recorder）是一种监控工具，它收集运行中的Java虚拟机（JVM）的详细运行时信息，这些信息可以用于诊断问题，以及分析和改进性能。飞行记录器之前是商业版JDK的一项分析工具，但从Java
11开始，它被贡献给了OpenJDK，成为了所有Java开发者可用的标准功能。

飞行记录器记录的主要数据源于应用程序、JVM和OS，这些事件信息保存在单独的事件记录文件中，故障发生后，能够从事件记录文件中提取出有用信息对故障进行分析。

启用参数：

~~~
java -XX:StartFlightRecording=filename=myrecording.jfr,duration=60s MyApplication
~~~

这条命令会启动一个60秒的飞行记录，并将结果保存在myrecording.jfr文件中。

还可以使用jcmd工具在运行中的Java应用程序上启动和控制JFR，例如：

~~~
jcmd <PID> JFR.start
jcmd <PID> JFR.dump filename=myrecording.jfr
jcmd <PID> JFR.stop
~~~

其中<PID>是Java进程的进程ID。

收集到的JFR记录文件可以使用多种工具进行分析，最常用的是JDK Mission Control（JMC）。JMC提供了一个图形界面，用于查看和分析JFR产生的数据文件。

### 运行单文件源码程序

允许开发者直接运行一个包含源代码的单个Java文件，而无需事先将其编译成字节码。这个特性简化了运行简单Java程序的过程，使得快速测试和运行小段代码更加便捷。

### 删除 Java EE 和 corba 模块

在 Java 9 和 Java 10 中 Java EE（现Jakarta EE）和CORBA模块就已经被标记为弃用了，并且在默认情况下是不可用的，在 Java 11
中，被彻底移除了。