package com.zy.utils.tsc_reload;

import cn.hutool.core.io.FileUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class TscReloadExample {

    public static void main(String[] args) throws InterruptedException {
       while (true){
           Config cfg = ConfigFactory.parseFile(new File("config.conf"));
           int configValue = cfg.getInt("foo.bar");
           System.out.println(configValue);
           System.out.println(String.valueOf(FileUtil.readUtf8String(new File("config.conf"))));


           Thread.sleep(2000);
       }
    }
}
