package com.zy.methodtimeagent;

import java.lang.instrument.Instrumentation;

public class RunTimeAgent {
    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("探针传入参数：" + arg);
        instrumentation.addTransformer(new RunTimeTransformer());
    }
}
