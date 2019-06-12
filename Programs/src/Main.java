public class Main {

	public static void main(String[] args) {

		// 
		String ip = "localhost";

		System.out.println("Scanning Host Addr is " + ip);
		JPortScan jps = new JPortScan(ip);
		jps.startScan();

		System.out.println("ポートスキャン結果");

		int[][] foo = jps.getResult();

		for (int i = 0; i < foo.length; i++) {
			System.out.println("PORT : " + i + "  STATUS : " + foo[i][0]);
		}

		System.out.println("finish");
	}
}
