package model;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.wpan.IoSample;
import com.rapplogic.xbee.api.wpan.RxResponseIoSample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField ;

public class XbeeCommunication {
	
	private int connect;
	
	public XbeeCommunication() {
	}
	
	public void XbeeConnect(XBee xbee, ComboBox<String> portXbee, ComboBox<String> baudT) {
		
		try {
			xbee.open(portXbee.getValue(), Integer.parseInt((String)baudT.getValue())) ;
			System.out.println("XBee open ok") ;
		} catch (NumberFormatException | XBeeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
 	}
	
	public void SamplefromXbee(XBee xbee) {
		try {
			RxResponseIoSample response;
			response = (RxResponseIoSample) xbee.getResponse() ;
			for(IoSample sample : response.getSamples())
				System.out.println("sample is " + sample.getAnalog0()) ;
		}
		catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	}
	public int getConnect() {
		return connect;
	}
	public void setConnect(int connect) {
		this.connect = connect;
	}
	
}

