import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


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
			Client myClient = new Client(myServerSocket.accept());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
