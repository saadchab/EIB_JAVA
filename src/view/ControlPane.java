package view ;

import model.* ;
import java.util.ArrayList;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.wpan.IoSample;
import com.rapplogic.xbee.api.wpan.RxResponseIoSample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.* ;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.* ;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.* ;
import javafx.stage.Stage;


public class ControlPane extends Application {
	
	private static final String SERIAL_BAUDRATE = "115200";
	private static final String SERIAL_COM = "COM3";
	int connect ;
	Button ButNoise = new Button("NOISE") ;
	Button ButSine = new Button("SINE") ;
	Button ButSquare = new Button("SQUARE") ;
	Button ButConnect = new Button ("CONNECT XBee") ;
	
	ControlPane(Stage stage, Signal Sig, XbeeCommunication xbeeCom) {
		try {
			BorderPane panel = new BorderPane() ;
			panel.setTop(createToolbar()) ;
			panel.setCenter(createMainContent(Sig)) ;
			panel.setRight(XBeeInterface(xbeeCom));
			Scene scene = new Scene(panel, 1600, 800) ;
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ;
			stage.setScene(scene) ;
			stage.setTitle("Oscilloscope") ;
			stage.show() ;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Node createToolbar() {
		return new ToolBar(ButNoise, ButSine, ButSquare, new Separator(), ButConnect) ;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private Node createMainContent(Signal Sig) {
		Group g = new Group() ;
		
		ButNoise.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Sig.drawSignal(SignalType.NOISE) ;
						Screen screen = new Screen() ;
						g.getChildren().clear();
						g.getChildren().add(screen.drawScreen(Sig.getSig())) ;
						g.getChildren().add(createView(Sig)) ;
					}
			    }) ;
		
		ButSine.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Sig.drawSignal(SignalType.SINE) ;
						Screen screen = new Screen() ;
						g.getChildren().clear();
						g.getChildren().add(screen.drawScreen(Sig.getSig())) ;
						g.getChildren().add(createView(Sig)) ;
					}
			    }) ;
		
		ButSquare.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						Sig.drawSignal(SignalType.SQUARE) ;
						Screen screen = new Screen() ;
						g.getChildren().clear();
						g.getChildren().add(screen.drawScreen(Sig.getSig())) ;
						g.getChildren().add(createView(Sig)) ;
					}
			    }) ;
		
		return g ;
	}
	
	private Node createView(Signal Sig) {
		Group g = new Group() ;
		SignalTools SigT = new SignalTools() ;
		double mean = 0, variance = 0 ;
		
		mean = SigT.computeTNS(Sig, "mean") ;
		variance = SigT.computeTNS(Sig, "variance") ;
		HBox hbM = new HBox() ;
		HBox hbV = new HBox() ;
		Label labelM = new Label("Mean = ") ;
		Label labelV = new Label("Variance = ") ;
		Label labelM1 = new Label() ;
		Label labelV1 = new Label() ;
	    labelM1.setText(Double.toString(mean)) ;
	    labelV1.setText(Double.toString(variance)) ;
		hbM.getChildren().addAll(labelM, labelM1) ;
		hbM.setLayoutY(-30) ; hbM.setSpacing(10) ;
		g.getChildren().add(hbM) ;
		hbV.getChildren().addAll(labelV, labelV1) ;
		hbV.setLayoutY(-50) ; hbV.setSpacing(10) ;
		g.getChildren().add(hbV) ;
		 
		return g ;
	}
	
	private Node XBeeInterface(XbeeCommunication xbeeCom) {
		Group g = new Group() ;
		HBox hb1 = new HBox() ;
		Label namePort = new Label("Port XBee") ;
		TextField portXbee = new TextField(SERIAL_COM) ;
		hb1.setSpacing(10) ;
		hb1.getChildren().addAll(namePort, portXbee) ;
		HBox hb2 = new HBox() ;
		Label baud = new Label("Baudrate") ;
		TextField baudT = new TextField(SERIAL_BAUDRATE) ;
		hb2.setSpacing(10) ; hb2.setLayoutX(300) ;
		hb2.getChildren().addAll(baud, baudT) ;
		g.getChildren().addAll(hb1, hb2) ;
		Circle c = new Circle() ;
		c.setCenterX(500); c.setCenterY(50); c.setRadius(10); c.setFill(Color.BLACK);
		g.getChildren().add(c);
		ButConnect.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						connect = 1 ;//xbeeCom.XbeeConnect(portXbee, baudT) ;
						if(connect == 1)	c.setFill(Color.GREEN) ;
						g.getChildren().remove(c) ;
					    g.getChildren().add(c) ;
						//xbeeCom.SamplefromXbee(xbee) ;
						}
			    }) ;
		
		return g ;
	}
	

}
