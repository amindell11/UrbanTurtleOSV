package org.uturtle.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class TestSerialLink {
	public static void main(String[] args) {
		SerialPort comPort = SerialPort.getCommPort("/dev/serial0");
		comPort.openPort();
		comPort.addDataListener(new SerialPortDataListener() {
		   @Override
		   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
		   @Override
		   public void serialEvent(SerialPortEvent event)
		   {
		      if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE || comPort.bytesAvailable()<19)
		         return;
		      if(comPort.bytesAvailable()>19) {
		    	  while(comPort.bytesAvailable()>0) {
		    		  try {
						comPort.getInputStream().read();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	  }
		    	  return;
		      }
		      System.out.println(comPort.bytesAvailable()+" bytes available");
		      byte[] newData = new byte[comPort.bytesAvailable()];
		      int numRead = comPort.readBytes(newData, newData.length);
		      byte[] gyro = Arrays.copyOfRange(newData, 1, 5);
		     float f = ByteBuffer.wrap(gyro).order(ByteOrder.LITTLE_ENDIAN).getFloat();
		      
		      System.out.println("Read " + numRead + " bytes.");
		      //System.out.println(Integer.toBinaryString(newData[0]));
		      System.out.println(f);
		   }
		});

	}
}
