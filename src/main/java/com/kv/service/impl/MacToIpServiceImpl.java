package com.kv.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kv.model.IPAddress;
import com.kv.service.MacToIpService;
import com.kv.utils.Constants;

@Service
public class MacToIpServiceImpl implements MacToIpService {

	/*
	 * TCP/IP networks use both MAC addresses and IP addresses but for separate purposes. A MAC address remains fixed to the device's hardware while the IP address for that same device can be changed depending on its TCP/IP network configuration. Media Access Control operates at Layer 2 of the OSI model while Internet Protocol operates at Layer 3. This allows MAC addressing to support other kinds of networks besides TCP/IP.

IP networks manage the conversion between IP and MAC addresses using Address Resolution Protocol (ARP). The Dynamic Host Configuration Protocol (DHCP) relies on ARP to manage the unique assignment of IP addresses to devices.(non-Javadoc)
	 * @see com.kv.service.MacToIpService#getIPFromMac(java.lang.String)
	 */
	
	private static Map<String, IPAddress> macToIpMap = new HashMap<String, IPAddress>();
	static int counter = 0;
	
	private static Set<Integer> subnetIdSet = new HashSet<Integer>();
	
	@Value("${ip.start.range}")
	private Integer ipStartRange;
	
	@Value("${ip.end.range}")
	private Integer ipEndRange;
	
	@Value("${ip.start.value}")
	private String ipStartValue;
	
	@Override
	public IPAddress getIPFromMac(String mac) {
		
		if(macToIpMap.get(mac) != null) {
			return macToIpMap.get(mac);
		}else {
			String ip = generateIp();
			if(ip != null) {
				IPAddress ipAddress = new IPAddress();
				ipAddress.setCreatedDate(LocalDateTime.now());
				ipAddress.setLastModifiedDate(LocalDateTime.now());
				ipAddress.setIpAddress(ip);
				ipAddress.setMacAddress(mac);
				ipAddress.setStatus(Constants.ACTIVE);
				
				macToIpMap.put(mac, ipAddress);
			}else {
				//No new IPs provided
				return null;
			}
		}
		return macToIpMap.get(mac);
	}
	
	private String generateIp() {
		
		Integer lastAdd = Integer.parseInt(ipStartValue.substring(ipStartValue.lastIndexOf(".")+1));
		
		final Random rnd = new Random();
		final Integer newVal = ipStartRange + rnd.nextInt(ipEndRange);
		if(subnetIdSet.contains(newVal)) {
		
			Integer finalVal = lastAdd+newVal;
			
			String newIP = ipStartValue.substring(0, ipStartValue.lastIndexOf(".")) + String.valueOf(finalVal);
			subnetIdSet.remove(newVal);
			return newIP;
		}else {
			// IP Range Over... Need to add new IP from config
			return null;
		}
		
	}
	
	@PostConstruct
	void init(){
		for(int i = ipStartRange; i<=ipEndRange; i++) {
			subnetIdSet.add(i);
		}
	}

}
