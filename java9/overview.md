## Java 9 新特性

### 引入模块系统

使用 module-info.java 文件来声明模块，指定模块所需的其他模块和模块导出的包。

* 使用module关键字定义模块，并指定模块的名称，例如：module java.module01 { }。
* 使用requires关键字声明模块之间的依赖关系，例如：requires java.sql; 表示模块依赖于java.sql模块。
* 使用exports关键字声明模块中哪些包可以被其他模块访问，例如：exports com.skjava.module01.entity;
  表示导出com.skjava.module01.entity包。

### 集合工厂方法

提供一种简洁、安全且不可变的方式来创建集合（List、Set、Map）。

* List.of()：创建一个不可变的 List，可以传递任意数量的元素。
* Set.of()：创建一个不可变的 Set，元素不可重复。
* Map.of() 和 Map.ofEntries()：用于创建一个不可变的 Map。Map.of() 可以直接传递键值对，而 Map.ofEntries() 可以通过
  Map.entry(k, v) 创建条目。

### Jshell

提供一个官方的 Java REPL，支持快速测试、探索和实验 Java 代码片段。

### 接口支持私有方法

支持在接口中定义私有方法和私有静态方法。

### Stream API 增强

* takeWhile()：允许从流的开始处理元素，直到给定的谓词返回 false。这在处理有序流时特别有用。
* dropWhile()：与 takeWhile() 相反，它从流的开始丢弃元素，直到谓词返回 false，然后处理剩余的元素。
* ofNullable()：用于创建单元素流，如果元素是 null，则返回一个空流，避免了 NullPointerException。
* iterate() 的新重载：在 Java 8 中，iterate() 方法是无限的。Java 9 添加了一个重载，允许你提供一个谓词作为终止条件，这样就可以创建有限的流。

### Optional 的增强

* stream() ：允许将 Optional 对象转换为一个（最多只有一个元素的）流。这在将多个 Optional 对象组合到一个流中时特别有用。
* ifPresentOrElse()：这个方法允许执行一个操作，如果 Optional 包含值，则执行一个操作，否则执行另一个操作。这提供了类似于“if-else”语句的功能。
* or()：允许在当前的 Optional 为空时，提供一个替代的 Optional。这类似于 orElse() 和 orElseGet()，但返回的是 Optional 对象而不是值。

### 改进 try-with-resources

允许使用在 try 语句块外部声明的资源。这意味着如果资源已经是 final 或者 effectively final（即实际上没有被后续代码修改），就可以在
try-with-resources 语句中直接使用，而无需在 try 语句内再声明一个新的局部变量。

* Java 7

~~~
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
try (BufferedReader r = reader) {
    // 使用 reader
}
~~~

* Java 9

~~~
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
try (reader) {
    // 使用 reader
}
~~~

### Process API

引入 Process API，其目的是为了提供更好的控制和管理操作系统进程的能力，并使其在不同操作系统上的行为更加一致。

### 平台日志 API 和 服务

引入该特性其主要目的是为 JDK 提供一个统一的日志系统，它能够通过不同的日志框架来捕获 JDK 内部的日志信息。这不仅简化了 JDK
自身的日志处理，也为开发者提供了更大的灵活性和控制力，使得他们能够更好地管理和监控 JDK 产生的日志信息。  
主要内容：

* 新的日志API：引入了一组新的日志API，称为 System.Logger API，用于 JDK 内部日志记录。
* 日志级别支持：支持不同的日志级别，例如 ERROR, WARNING, INFO, DEBUG, 和 TRACE。
* 日志服务接口：定义了一个服务接口，允许替换JDK的日志记录系统，或者将其桥接到其他日志框架。

下面代码是该日志 API 的示例：

~~~
System.Logger logger = System.getLogger("Java9Test");
logger.log(System.Logger.Level.INFO, "这是 INFO 级别");
logger.log(System.Logger.Level.WARNING, "这是 WARNING 级别");
logger.log(System.Logger.Level.ERROR, "这是 ERROR 级别");
~~~

### 反应式流（Reactive Streams）

引入反应式流，其目的是提供一种在 Java
中处理异步数据流的标准方式，同时保证高效率、低延迟，并支持背压（back-pressure），即允许接收者控制数据流的速度，防止被快速生产的数据淹没。  
引入了 java.util.concurrent.Flow 类，它包含了几个嵌套的静态接口：Publisher、Subscriber、Subscription 和 Processor。

* Publisher：一个数据流的生产者。
* Subscriber：订阅 Publisher 并处理数据的消费者。
* Subscription：连接 Publisher 和 Subscriber，允许 Subscriber 控制数据流。
* Processor：充当生产者和消费者的中间人，即 Publisher 和 Subscriber 的组合。

### HTML5 Javadoc

引入 HTML5 Javadoc

### 多版本兼容 JAR 文件

应用程序可以在不同的 Java 运行时环境中运行，而无需更改或重新打包。

### 改进的弃用注解 @Deprecated

对其进行了改进，增加了两个的属性：

* since：用于指明从哪个版本开始 API 被弃用。
* forRemoval：指出这个API是否计划在未来的版本中被移除。

该项特性可以让开发者能够更清晰地了解 API 的状态和未来规划，比如是否继续使用该 API、寻找替代方案。

### 改进钻石操作符(Diamond Operator)

改进了钻石操作符，它可以与匿名内部类一起使用。这意味着当我们在创建一个匿名内部类的实例，并且该类具有泛型参数时，我们可以省略这些参数，Java
编译器会根据上下文推断出正确的类型。

### 增强 CompletableFuture

进行了一些增强，内容如下：

* 新增方法
    * completeAsync()：允许异步地完成 CompletableFuture。它受一个 Supplier 函数和可选的 Executor，用于异步生成结果。
    * orTimeout()：为 CompletableFuture 添加超时功能。如果在指定的时间内未完成，CompletableFuture 将会被异常地完成。
    * completeOnTimeout()：类似于 orTimeout()，但在超时发生时，它会使用提供的值来完成 CompletableFuture，而不是抛出异常。
* 改进异常处理
    * exceptionallyCompose()：它允许在 CompletableFuture 遇到异常时，构建并返回一个新的 CompletionStage，这为异常处理提供了更多的灵活性。
* 增强的组合操作
    * delayedExecutor()：这是一个工具方法，用于创建一个延迟执行任务的 Executor。它可以和其他 CompletableFuture
      方法结合使用，实现延迟执行的效果。
    * minimalCompletionStage()和completeMinimalFuture()：这两个方法分别用于创建一个具有最小完成状态的 CompletionStage，以及从
      CompletionStage 创建一个 CompletableFuture。这些方法有助于在不需要 CompletableFuture 完整功能的场景中减少资源消耗。