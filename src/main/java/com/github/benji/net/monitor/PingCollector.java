package com.github.benji.net.monitor;

public class PingCollector {

	final static long NOT_SET = -1;
	static long oldestPingReceivedTimestamp = NOT_SET;

	public synchronized static void registerReceivedPing(long start) {
		if (oldestPingReceivedTimestamp == NOT_SET || start < oldestPingReceivedTimestamp) {
			oldestPingReceivedTimestamp = start;
		}
	}

	public synchronized static long getAndReset() {
		long ts = oldestPingReceivedTimestamp;
		oldestPingReceivedTimestamp = NOT_SET;
		return ts;
	}

}
