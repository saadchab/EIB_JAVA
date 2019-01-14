package view;

import java.util.ArrayList;

import com.rapplogic.xbee.api.XBee;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Signal;
import model.XbeeCommunication;

public class Main extends Application {
	
	static XbeeCommunication xbeeCom  = new XbeeCommunication() ;
	static XBee xbee = new XBee() ;
	static Signal Sig = new Signal() ;
	
	public void start(Stage primaryStage) {
		try {
			Sig.setFreq(880);
			Sig.setFe(44100);
			Sig.setNbEch(100);
			ArrayList<Double> L = Sig.getSig();
			ControlPane C = new ControlPane(primaryStage, Sig, xbee, xbeeCom);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
