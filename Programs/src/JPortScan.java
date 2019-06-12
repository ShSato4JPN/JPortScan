import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JPortScan {

	private final int MAX_PORT = 1024;
	private int[][] resAry = new int[MAX_PORT + 1][1];
	private String hostName;

	public JPortScan(String hostName) {
		this.hostName = hostName;
	}

	public int[][] startScan() {
		ExecutorService service = Executors.newCachedThreadPool();
		try {
			System.out.println("START PORTSCANNING... \n");
			for (int i = 0; i <= MAX_PORT; i++) {
				Future<Integer> f = service.submit(new portScanTask(hostName, i));
				resAry[i][0] = f.get().intValue();
			}
		} catch(Exception ex) {
			System.out.println("処理中にエラーが発生");
			resAry = new int[1][1];
			resAry[0][1] = -1;
		} finally {
			service.shutdown();
		}

		return resAry;
	}

	// getter
	public int[][] getResult() {
		return resAry;
	}

}

class portScanTask implements Callable<Integer> {

	private String hostName;
	private int    port;

	public portScanTask(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
	}

	@Override
	public Integer call() throws Exception {
		PortScan ps = new PortScan(this.hostName);
		return ps.doScan(port);
	}

}
