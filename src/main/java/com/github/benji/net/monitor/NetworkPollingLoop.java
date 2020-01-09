package com.github.benji.net.monitor;

import java.net.InetAddress;

public class NetworkPollingLoop extends Thread {

	InetAddress addr;
	int interval;
	int timeout;

	public NetworkPollingLoop(InetAddress addr, int interval, int timeout) {
		this.addr = addr;
		this.interval = interval;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		while (true) {
			OneNetworkPollThread poll = new OneNetworkPollThread(this.addr, timeout);
			poll.start();
			Utils.sleep(interval);
		}
	}

}
