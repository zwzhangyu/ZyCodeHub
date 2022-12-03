package com.zy.core_java.script_jsr.hello_world;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * ScriptEngine 测试代码
 *
 * @author zhangyu
 * @date 2022/12/3
 **/
public class HelloScript {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.eval("print('Hello World')");
    }
}
