package com.kv.service;

public interface HeartbeatService {
	Boolean refresh(String macAddress, String allocatedIPAddr);
}
