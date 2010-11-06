package mainClient;
import java.util.Scanner;


public class Chat {
	Scanner sc;
	ServerListener sl;
	Client c;

	public Chat() {
		sc = new Scanner(System.in);
		startChat();
	}

	public void startChat() {
		System.out.print("Listen or Connect? (L/C) ");
		String decision = sc.nextLine();
		if(decision.equalsIgnoreCase("L")) {
			sl = new ServerListener();
		} else if(decision.equalsIgnoreCase("C")) {
			c = new Client();
		} else {
			System.out.println("You lose the game.");
		}
	}
}
