package com.zy.core_java.script_jsr.invoke_method;

import org.testng.annotations.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 测试在JAVA中调用JS方法
 *
 * @author zhangyu
 * @date 2022/12/3
 **/
public class Example1 {

    /**
     * 执行脚本函数
     */
    @Test
    public void test4() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        // 定义一个JavaScript方法
        String script = "function hello(name) { print('Hello, ' + name); }";
        // 执行脚本
        engine.eval(script);
        // 由ScriptEngines实现的接口，其方法允许调用以前已执行的脚本中的过程。
        Invocable inv = (Invocable) engine;
        // 调用全局函数，并传入参数
        inv.invokeFunction("hello", "测试");
    }

}
