package com.github.benji.net.monitor;

import java.io.IOException;
import java.net.InetAddress;

public class OneNetworkPollThread extends Thread {

	InetAddress addr;
	int timeout;

	public OneNetworkPollThread(InetAddress addr, int timeout) {
		this.addr = addr;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		try {
			if (addr.isReachable(timeout)) {
				PingCollector.registerReceivedPing(start);
			} else {
				System.out.println("Failed to reach " + addr.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
