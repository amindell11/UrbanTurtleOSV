package org.uturtle.console;

import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class ConsoleMain {
	public static void main(String[] args) {
		SerialPort comPort = SerialPort.getCommPort("COM9");
		for (SerialPort port : SerialPort.getCommPorts()) {
			System.out.println(port.getSystemPortName());
			//comPort = port;
		}
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		InputStream in = comPort.getInputStream();
		try {
			while(true)
				System.out.print((char)in.read());
		} catch (Exception e) {
			e.printStackTrace();
		}
		comPort.closePort();
	}
}
