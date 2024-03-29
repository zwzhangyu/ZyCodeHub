package io.springboot.log.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@RestController
public class LogController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);
	
	@GetMapping(value = { "/", "/index"})
	public ModelAndView index () {
		return new ModelAndView("index/index");
	}
	
	@GetMapping("/log")
	public void log () throws InterruptedException {

		while (true){
			LOGGER.info("随机日志消息：number={}, uuid={}", 2, UUID.randomUUID().toString());
			Thread.sleep(1000);
		}
		
//		Map<String, Object> retVal = new HashMap<>();
//		retVal.put("success", Boolean.TRUE);
//		return ResponseEntity.ok(retVal);
	}
}	




