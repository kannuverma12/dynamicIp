package com.kv.service;

import com.kv.model.IPAddress;

public interface MacToIpService {
	IPAddress getIPFromMac(String mac);

}
