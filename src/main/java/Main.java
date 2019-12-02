import java.net.InetAddress;

public class Main {
	static String host = System.getProperty("monitoring.host", "www.google.com");
	static Integer pingTimeout = Integer.getInteger("monitoring.ping.timeout", 10000);

	public static void main(String[] args) throws Exception {
		java.security.Security.setProperty("networkaddress.cache.ttl", "0");

		long start, stop;

		while (true) {
			InetAddress addr = null;
			start = System.currentTimeMillis();
			addr = InetAddress.getByName(host);
			stop = System.currentTimeMillis();
			long elapsedDns = stop - start;

			long elapsedPing;
			start = System.currentTimeMillis();
			if (addr.isReachable(pingTimeout)) {
				stop = System.currentTimeMillis();
				elapsedPing = stop - start;
			} else {
				elapsedPing = -1;
			}

			System.out.println(elapsedDns + "," + elapsedPing);
			Thread.sleep(1000);
		}
	}
}
