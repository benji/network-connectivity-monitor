import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	static String host = System.getProperty("monitoring.host", "www.google.com");
	static Integer pingTimeout = Integer.getInteger("monitoring.ping.timeout", 10000);
	static Integer interval = Integer.getInteger("monitoring.interval", 1000);

	static DateTimeFormatter iso8601DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
			.withZone(OffsetDateTime.now().getOffset().normalized());

	public static void main(String[] args) throws InterruptedException {

		java.security.Security.setProperty("networkaddress.cache.ttl", "0");

		long start, stop;
		System.out.println("datetime,dns,ping");

		while (true) {
			Instant now = Instant.now();
			long elapsedDns;
			InetAddress addr = null;
			start = System.currentTimeMillis();
			try {
				addr = InetAddress.getByName(host);
				stop = System.currentTimeMillis();
				elapsedDns = stop - start;
			} catch (UnknownHostException e) {
				elapsedDns = -1; // failed DNS resolution
			}

			long elapsedPing = -1;

			if (elapsedDns != -1) {
				start = System.currentTimeMillis();
				try {
					if (addr.isReachable(pingTimeout)) {
						stop = System.currentTimeMillis();
						elapsedPing = stop - start;
					}
				} catch (IOException e) {
					e.printStackTrace(); // to syserr
				}
			}

			System.out.println(iso8601DateTimeFormatter.format(now) + "," + elapsedDns + "," + elapsedPing);
			Thread.sleep(interval);
		}
	}
}
