package com.kv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kv.service.HeartbeatService;

@RestController
@RequestMapping("/heartbeat")
public class HearbeatController {
	
	@Autowired
	private HeartbeatService heartbeatService;
	
	@RequestMapping("/referesh")
	public Boolean refreshHearbeat(String macAddress, String allocatedIPAddr) {
		
		return heartbeatService.refresh(macAddress, allocatedIPAddr);
		
	}

}
