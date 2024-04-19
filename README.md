该项目是javaagent的测试类

## 原理

javaagent实现的本质是在类加载的时候，对类进行修改，这样就可以在类加载的时候对类进行增强，比如在方法前后加入日志输出，或者在方法前后加入耗时统计等等。

Java Agent支持目标JVM启动时加载，也支持在目标JVM运行时加载，这两种不同的加载模式会使用不同的入口函数，如果需要在目标JVM启动的同时加载Agent，需要在Agent中实现premain方法，运行中挂载实现agentmain方法。
premain是在main函数之前执行的，而agentmain是在main函数之后执行的。
## JVM启动挂载
通过jvm启动时挂在实现方式如下:
```java
//方法一
public static void premain(String agentArgs, Instrumentation inst);
//方法二
public static void premain(String agentArgs);
```
JVM将首先寻找方法一，如果没有发现，再寻找方法二。
## JVM运行时挂载
```java
public static void agentmain(String agentArgs, Instrumentation inst);
public static void agentmain(String agentArgs);
```
- AgentArgs是随同 “– javaagent”一起传入的程序参数，如果这个字符串代表了多个参数，就需要自己解析这些参数。
- inst是Instrumentation类型的对象，是JVM自动传入的，我们可以拿这个参数进行类增强等操作。


挂载的方式
-

## 参考文档

[java动态调试技术](https://pdai.tech/md/java/jvm/java-jvm-agent-usage.html)

[javaAgent的demo实现](https://blog.csdn.net/qq_36370910/article/details/130562385)