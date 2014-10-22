package protezio.control;

import java.util.ArrayList;


public class ClienConn {
	

	public ArrayList<GPIOPort> Pin;
	
	
	
	
 	public static class ConnectionInfo {
		public String host;
		public int port;
		public String username;
		public String password;

		public ConnectionInfo(String host, int port, String user, String pass) {
			this.host = host;
			this.port = port;
			this.username = user;
			this.password = pass;
		}
	}
	
	public class GPIOPort {
		public int num;
		public int pin;
		public PORTFUNCTION function;
		public PORTVALUE value;

		public GPIOPort() {
			this.num = 0;
			this.pin = -1;
			this.function = PORTFUNCTION.UNKNOWN;
			this.value = PORTVALUE.LOGIC_0;
		}
	
	}
	
	
	 	
	
	
}
	
	
	
	
	
	
	
	
	
	
	

