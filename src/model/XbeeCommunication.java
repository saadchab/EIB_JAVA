package model;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.wpan.IoSample;
import com.rapplogic.xbee.api.wpan.RxResponseIoSample;

import javafx.scene.control.TextField ;

public class XbeeCommunication {
	
	public XbeeCommunication() {}
	public XBee XbeeConnect(TextField portXbee, TextField baudT) {
		
		XBee xbee  = new XBee() ;
		try {
			xbee.open(portXbee.getText(), Integer.parseInt(baudT.getText())) ;
			System.out.println("XBee open ok") ;
		} catch (NumberFormatException | XBeeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return xbee;
	}
	
	public void SamplefromXbee(XBee xbee) {
		try {
			while (true) {
			    RxResponseIoSample response;
			    response = (RxResponseIoSample) xbee.getResponse() ;
				for(IoSample sample : response.getSamples())
					System.out.println("sample is " + sample.getAnalog0()) ;
			}
		}
		catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
}

