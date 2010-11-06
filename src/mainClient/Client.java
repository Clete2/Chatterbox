package mainClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	Socket mySocket;
	short port;
	byte [] ip;
	boolean running;

	public Client(byte[] ipAsByteArray) {
		this.ip = ipAsByteArray;
		initializeSocket();
		startChat();
	}

	public Client(Socket socket) {
		this.mySocket = socket;
		startChat();
	}

	private void initializeSocket() {
		port = 1337;

		try {
			this.mySocket = new Socket(InetAddress.getByAddress(ip), port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startChat() {
		BufferedReader br;
		running = true;
		try {
			br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			ScannerThread st = new ScannerThread(mySocket.getOutputStream());
			st.start();
			
			String in = "";
			while(running) {
				try{
					while((in = br.readLine()) != null) {
						System.out.println("Stranger: "+ in);
					}
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
