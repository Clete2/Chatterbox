import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerThread extends Thread {
	Scanner sc;
	PrintWriter pw;
	
	public ScannerThread(OutputStream os) {
		sc = new Scanner(System.in);
		pw = new PrintWriter(os, true);
	}
	
	public void run() {
		while(true) {
			if(sc.hasNextLine()) {
				pw.println(sc.nextLine());
			}
		}
	}
}
