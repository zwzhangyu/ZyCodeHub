package com.example.oshicore;

import com.example.oshicore.server.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SystemInfoController {


    @GetMapping("/show")
    public Map<String, Object>  show() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Server server = new Server();
        server.copyTo();
        map.put("server", server);
        return  map;
    }

}
