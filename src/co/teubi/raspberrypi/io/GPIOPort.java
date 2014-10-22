package co.teubi.raspberrypi.io;

import protezio.control.PORTFUNCTION;
import protezio.control.PORTVALUE;

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