package com.kv.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kv.service.AllocationService;
import com.kv.utils.Constants;

@Service
public class AllocationServiceImpl implements AllocationService {
	
	@Autowired
	private MacToIpServiceImpl macToIpService;
	
	@Override
	public String allocate(String macAddress) {
		if(macToIpService.getIPFromMac(macAddress) != null) {
			return macToIpService.getIPFromMac(macAddress).getIpAddress();
		}
		return Constants.ERROR_ALLOCATING_IP;
		
	}
}
