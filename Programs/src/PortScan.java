import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScan {

	private String HOST_NAME;
	private int    STATUS;

	public PortScan(String hostName) {
		this.HOST_NAME = hostName;
	}

	public int doScan(int port) {

		Socket socket = new Socket();

		try {
			InetSocketAddress addrInfo = new InetSocketAddress(this.HOST_NAME, port);
            // TCP接続
			socket.connect(addrInfo, 5);
			this.STATUS = 1;
		} catch(IOException ex) {
			this.STATUS = 0;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return STATUS;
	}
}
