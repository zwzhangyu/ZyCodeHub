package com.zy.core_java.script_jsr.invoke_class;

import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 测试JS中调用Java对象
 *
 * @author zhangyu
 * @date 2022/12/3
 **/
public class Example1 {

    /**
     * 测试JS中调用Java对象
     */
    @Test
    public void test1() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        engine.eval(new java.io.FileReader("src/main/java/com/zy/core_java/script_jsr/js_file/demo1.js"));
    }

}