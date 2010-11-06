import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	Socket mySocket;
	short port;
	boolean running;

	public Client() {
		initializeSocket();
		startChat();
	}

	public Client(Socket socket) {
		this.mySocket = socket;
		startChat();
	}

	private void initializeSocket() {
		byte b[] = new byte[4];
		b[0] = new Integer(127).byteValue();
		b[1] = new Integer(0).byteValue();
		b[2] = new Integer(0).byteValue();
		b[3] = new Integer(1).byteValue();
		port = 1337;

		try {
			this.mySocket = new Socket(InetAddress.getByAddress(b), port);
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
