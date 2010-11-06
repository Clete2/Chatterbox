package mainClient;
import java.io.IOException;
import java.net.ServerSocket;


public class ServerListener {
	ServerSocket myServerSocket;
	short port;
	
	public ServerListener() {
		port = 1337;
		this.startServerSocket();
	}
	
	private void startServerSocket() {
		try {
			myServerSocket = new ServerSocket(port);
			new Client(myServerSocket.accept());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
