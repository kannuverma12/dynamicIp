package com.kv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kv.service.AllocationService;

@RestController
@RequestMapping("/allocate")
public class AllocationController {
	
	@Autowired
	private AllocationService allocationService;
	
	@RequestMapping("/ipaddress")
	public String allocateIPAddress(String macAddress) {
		return allocationService.allocate(macAddress);
	}

}
