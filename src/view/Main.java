package view;

import model.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			Signal Sig = new Signal();
			Sig.setFreq(880);
			Sig.setFe(44100);
			Sig.setNbEch(100);
			ArrayList<Double> L = Sig.getSig();
			ControlPane C = new ControlPane(primaryStage, Sig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
