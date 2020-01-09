package com.github.benji.net.monitor;

import java.net.InetAddress;

public class Main {
	static String host = System.getProperty("monitoring.host", "www.google.com");
	static Integer pingTimeout = Integer.getInteger("monitoring.ping.timeout", 3000);
	static Integer pingInterval = Integer.getInteger("monitoring.ping.interval", 1000);
	static Integer collectionInterval = Integer.getInteger("monitoring.collection.interval", 1000);

	public static void main(String[] args) throws Exception {
		java.security.Security.setProperty("networkaddress.cache.ttl", "0");

		InetAddress addr = InetAddress.getByName(host);
		System.out.println("Host " + host + " resolved to " + addr.getHostAddress());

		String fileName = null;
		if (args.length > 0) {
			fileName = args[0];
		}

		NetworkPollingLoop loop = new NetworkPollingLoop(addr, pingInterval, pingTimeout);
		loop.start();
		StatisticsCollector collector = new StatisticsCollector(fileName, collectionInterval);
		collector.start();

		loop.join();
		collector.join();
	}
}
