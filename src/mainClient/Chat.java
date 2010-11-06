package mainClient;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Chat {
	private static final Exception NoMatchFoundException = null;
	Scanner sc;
	ServerListener sl;
	Client c;
	String ipRegex;

	public Chat() {
		sc = new Scanner(System.in);
		ipRegex = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
		startChat();
	}

	public void startChat() {
		String decision = "";
		while(!decision.equalsIgnoreCase("L") && !decision.equalsIgnoreCase("C")) {
			System.out.print("Listen or Connect? (L/C) ");
			decision = sc.nextLine();
			if(decision.equalsIgnoreCase("L")) {
				sl = new ServerListener();
			} else if(decision.equalsIgnoreCase("C")) {
				sc.reset();
				System.out.print("Enter an IPv4 address to connect to: ");
				String ip = sc.nextLine();
				while(!isIPValid(ip)) {
					System.out.print("Invalid IPv4 address. Try again: ");
					ip = sc.nextLine();
				}
				try {
					c = new Client(getIPBytes(ip));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isIPValid(String ipToTest) {
		Pattern ipPattern = Pattern.compile(ipRegex);
		Matcher ipMatcher = ipPattern.matcher(ipToTest);
		if(!ipMatcher.matches()) {
			return false;
		}
		for(int i = 0; i < 4; i++) {
			if(Short.parseShort(ipMatcher.group(i + 1)) > 255 ||
					Short.parseShort(ipMatcher.group(i + 1)) < 0) {
				return false;
			}
		}
		return true;
	}

	private byte[] getIPBytes(String ipToGetBytes) throws Exception {
		Pattern ipPattern = Pattern.compile(ipRegex);
		Matcher ipMatcher = ipPattern.matcher(ipToGetBytes);
		byte[] ipAsByteArray = new byte[4];

		if(!ipMatcher.matches()) {
			throw NoMatchFoundException;
		}
		for(int i = 0; i < 4; i++) {
			ipAsByteArray[i] = new Integer(ipMatcher.group(i + 1)).byteValue();
		}
		return ipAsByteArray;
	}
}
