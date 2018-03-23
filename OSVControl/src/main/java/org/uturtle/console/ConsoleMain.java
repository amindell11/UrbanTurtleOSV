package org.uturtle.console;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.fazecast.jSerialComm.SerialPort;

public class ConsoleMain {
	public static void main(String[] args) {
		SerialPort comPort = SerialPort.getCommPort("COM9");
		for (SerialPort port : SerialPort.getCommPorts()) {
			System.out.println(port.getSystemPortName());
			// comPort = port;
		}
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		InputStream in = comPort.getInputStream();
		try {
			for (int j = 1; j < 1000; ++j) {
				while(in.available()<4);
				byte[] b = new byte[4];
				in.read(b);
				float f = ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getFloat();
				System.out.println(f);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		comPort.closePort();
	}
}
