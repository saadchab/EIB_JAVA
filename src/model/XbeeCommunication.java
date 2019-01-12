package model;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.wpan.IoSample;
import com.rapplogic.xbee.api.wpan.RxResponseIoSample;

import javafx.scene.control.TextField ;

public class XbeeCommunication {
	
	private int connect;
	
	public XbeeCommunication() {
		setConnect(0) ;
	}
	public int XbeeConnect(TextField portXbee, TextField baudT) {
		
		XBee xbee = new XBee() ;
		
		try {
			xbee.open(portXbee.getText(), Integer.parseInt(baudT.getText())) ;
			System.out.println("XBee open ok") ;
			setConnect(1) ;
		} catch (NumberFormatException | XBeeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return getConnect() ;
 	}
	
	public void SamplefromXbee(XBee xbee) {
		try {
			//while (true) {
			    RxResponseIoSample response;
			    response = (RxResponseIoSample) xbee.getResponse() ;
				for(IoSample sample : response.getSamples())
					System.out.println("sample is " + sample.getAnalog0()) ;
			//}
		}
		catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		finally {
			if (xbee != null && xbee.isConnected()) {
				xbee.close();		
			}
		}
	}
	public int getConnect() {
		return connect;
	}
	public void setConnect(int connect) {
		this.connect = connect;
	}
	
}

