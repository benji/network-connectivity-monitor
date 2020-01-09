package com.github.benji.net.monitor;

public class StatisticsCollector extends Thread {

	String fileName;
	int interval;
	StatisticsLogger logger;

	public StatisticsCollector(String fileName, int interval) {
		this.interval = interval;
		this.logger = new StatisticsLogger(fileName);
	}

	@Override
	public void run() {
		while (true) {
			long oldestStartReceived = PingCollector.getAndReset();
			this.logger.log(oldestStartReceived);
			Utils.sleep(interval);
		}
	}

}
