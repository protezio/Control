package co.teubi.raspberrypi.io;

import protezio.control.PORTFUNCTION;
import protezio.control.PORTVALUE;

public class GPIOPin {
	public int number;
	public int gpio;
	public PORTFUNCTION function;
	public PORTVALUE value;
	public String info;
	
	public GPIOPin() {
		number = -1;
		gpio = -1;
		function = PORTFUNCTION.UNKNOWN;
		value = PORTVALUE.UNKNOWN;
		this.info = "";
	}
	
	public GPIOPin(int number, int gpio,PORTFUNCTION funct, PORTVALUE value) {
		this.number = number;
		this.gpio = gpio;
		this.function = funct;
		this.value = value;
		this.info = "";
	}

	public GPIOPin(int number, int gpio,PORTFUNCTION funct, PORTVALUE value,String info) {
		this.number = number;
		this.gpio = gpio;
		this.function = funct;
		this.value = value;
		this.info = info;
	}
}
