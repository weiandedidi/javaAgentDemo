package com.qidi;

import java.lang.instrument.Instrumentation;

public class JavaAgentTest {
    /**
     * 在main方法运行前，与main方法运行于同一JVM中
     *
     * @param agentArgs agentArgs是premain函数得到的程序参数，随同"-javaagent"一同传入，
     *                  与main函数不同的是，该参数是一个字符串而不是一个字符串数组，
     *                  如果程序参数有多个，程序将自行解析这个字符串
     * @param inst      一个java.lang.instrument.Instrumentation实例，由JVM自动传入，
     *                  java.lang.instrument.Instrumentation是instrument包中的一个接口，
     *                  也是核心部分，集中了其中几乎所有的功能方法，例如类定义的转换和操作
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.err.println("==============启动之前执行===================");
        System.err.println("agentArgs : " + agentArgs);
        // 添加Transformer
        inst.addTransformer(new TransformerTest());
    }
}
