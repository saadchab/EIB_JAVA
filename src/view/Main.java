package view;

import model.*;

import java.util.ArrayList;

import com.rapplogic.xbee.api.XBee;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			Signal Sig = new Signal();
			SignalTools SigT = new SignalTools();
			Sig.setFreq(880);
			System.out.println("Freq = " + Sig.getFreq() + " Hz");
			Sig.setFe(44100);
			System.out.println("Fe = " + Sig.getFe() + " Hz");
			Sig.setNbEch(100);
			System.out.println("NbEch = " + Sig.getNbEch());
			// Sig.drawSignal(SignalType.SINE) ;
			ArrayList<Double> L = Sig.getSig();
			System.out.println(L);
			System.out.println("Mean = " + SigT.computeTNS(Sig, "mean"));
			// Screen S = new Screen(primaryStage, L) ;
			ControlPane C = new ControlPane(primaryStage, Sig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
