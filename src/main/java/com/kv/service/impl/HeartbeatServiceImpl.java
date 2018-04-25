package com.kv.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kv.model.IPAddress;
import com.kv.service.HeartbeatService;

@Service
public class HeartbeatServiceImpl implements HeartbeatService {
	
	@Autowired
	private MacToIpServiceImpl macToIpServiceImpl;

	@Override
	public Boolean refresh(String macAddress, String allocatedIPAddr) {
		IPAddress ipAddress = macToIpServiceImpl.getIPFromMac(macAddress);
		
		if(ipAddress != null && ipAddress.getIpAddress().equals(allocatedIPAddr)) {
			ipAddress.setLastModifiedDate(LocalDateTime.now());
			return true;
		}
		
		return false;
	}

}
