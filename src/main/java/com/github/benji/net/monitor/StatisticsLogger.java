package com.github.benji.net.monitor;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class StatisticsLogger {
	static DateTimeFormatter iso8601DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
			.withZone(OffsetDateTime.now().getOffset().normalized());

	String fileName;
	FileWriter writer = null;

	public StatisticsLogger(String fileName) {
		this.fileName = fileName;
	}

	private void logLine(String line) {
		System.out.println(line);
		if (this.fileName == null) {
			return;
		}
		try {
			if (this.writer == null) {
				System.out.println("Opening file " + fileName);
				this.writer = new FileWriter(fileName);
				logLine("timestamp,ping");
			}
			this.writer.append(line + "\n");
			this.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void log(long oldestStartReceived) {
		long elapsed = -1;
		if (oldestStartReceived != -1) {
			elapsed = System.currentTimeMillis() - oldestStartReceived;
		}

		Instant now = Instant.now();
		String log = iso8601DateTimeFormatter.format(now) + "," + elapsed;
		logLine(log);
	}

}
