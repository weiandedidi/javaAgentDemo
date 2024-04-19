package com.qidi;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class TransformerTest implements ClassFileTransformer {
    /**
     * 需要被修改的类
     */
    public final String TEST_CLASS_NAME = "com.qidi.bootdemo2.controller.TestJavaAgentController";
    /**
     * 被agent修改的方法名
     */
    public final String METHOD_NAME = "testAgent";


    /**
     * 这里引入javassist，需要引入依赖
     *
     * @param loader              定义要转换的类装入器；如果是引导加载器，则为 null
     * @param className           类的二进制名称
     * @param classBeingRedefined 如果是被重定义或重转换的类，则为重定义或重转换的类；如果是类加载器的引导类，则为 null
     * @param protectionDomain    要定义或重定义的类的保护域
     * @param classfileBuffer     类文件格式的输入字节缓冲区（不得修改）
     * @return 一个转换后的字节码的字节数组；如果未执行转换，则返回 null
     * @throws IllegalClassFormatException 如果转换无法执行
     */
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        String finalClassName = className.replace("/", ".");

        if (TEST_CLASS_NAME.equals(finalClassName)) {
            System.err.println("启动中执行");

            CtClass ctClass;

            try {
                ctClass = ClassPool.getDefault().get(finalClassName);
                System.err.println("启动中执行1");
                CtMethod ctMethod = ctClass.getDeclaredMethod(METHOD_NAME);
                System.err.println("启动中执行2");
                ctMethod.insertBefore("System.err.println(\"通过javaAgebt插入了代码\");");
                ctMethod.insertBefore("text = \"text被javaAgebt篡改了\";");
                return ctClass.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        return null;
    }
}