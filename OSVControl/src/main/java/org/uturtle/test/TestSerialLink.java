package org.uturtle.test;

import com.fazecast.jSerialComm.SerialPort;

public class TestSerialLink {
	public static void main(String[] args) {
		SerialPort comPort = SerialPort.getCommPort("dev/ttyACM0");
		System.out.println(comPort.openPort());
		comPort.setComPortParameters(115200, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000000, 0);
		try {
		int x = 0;
		   while (true)
		   { 
			  x++;
			  System.out.println(x);
		      byte[] readBuffer = new byte[1];
		      int numRead = comPort.readBytes(readBuffer, readBuffer.length);
		      System.out.println("Read " + numRead + " bytes. - "+readBuffer[0]);
		   }
		} catch (Exception e) { e.printStackTrace(); }
		comPort.closePort();
	}
}
