package view ;

import model.* ;
import java.util.ArrayList;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.wpan.IoSample;
import com.rapplogic.xbee.api.wpan.RxResponseIoSample;

import javafx.animation.AnimationTimer;
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
	Circle c = new Circle() ; Circle c2 = new Circle() ;
	int connect ;
	int i = 0 ;
	int start ;
	int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ;
	Button ButNoise = new Button("NOISE") ;
	Button ButSine = new Button("SINE") ;
	Button ButSquare = new Button("SQUARE") ;
	Button ButConnect = new Button ("CONNECT/DECONNECT XBee") ;
	Button ButStart = new Button ("START/STOP XBee Acquisition") ;
	
	ControlPane(Stage stage, Signal Sig, XBee xbee, XbeeCommunication xbeeCom) {
		try {
			BorderPane panel = new BorderPane() ;
			panel.setTop(createToolbar()) ;
			panel.setCenter(createMainContent(Sig)) ;
			panel.setRight(XBeeInterface(xbee, xbeeCom));
			Scene scene = new Scene(panel, 1600, 800) ;
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ;
			stage.setScene(scene) ;
			stage.setTitle("Oscilloscope") ;
			stage.show() ;
			new AnimationTimer() {
	            @Override public void handle(long currentTime) {
	            	if (xbeeCom.getConnect() == 1 && start == 1) {
	            		panel.setCenter(AfficheXBee(xbee, xbeeCom)) ;
	                }

	                try {
	                    Thread.sleep(100) ;
	                } catch (InterruptedException e) {
	                    // Do nothing
	                }
	            }
	        }.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Node createToolbar() {
		return new ToolBar(ButNoise, ButSine, ButSquare, new Separator(), ButConnect, ButStart) ;
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
	
	private Node XBeeInterface(XBee xbee, XbeeCommunication xbeeCom) {
		Group g = new Group() ;
		HBox hb1 = new HBox() ;
		Label namePort = new Label("Port XBee") ;
		final ComboBox<String> portXbee = new ComboBox<String>() ;
		portXbee.getItems().addAll("COM0", "COM1", "COM2", SERIAL_COM, "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10") ;
		portXbee.setValue(SERIAL_COM) ;
		hb1.setSpacing(10) ;
		hb1.getChildren().addAll(namePort, portXbee) ;
		HBox hb2 = new HBox() ;
		Label baud = new Label("Baudrate") ;
		final ComboBox<String> baudT = new ComboBox<String>() ;
		baudT.getItems().addAll("1200", "2400", "4800", "9600", "19200", "38400", "57600", SERIAL_BAUDRATE) ;   
	    baudT.setValue(SERIAL_BAUDRATE) ;
		hb2.setSpacing(10) ; hb2.setLayoutX(300) ;
		hb2.getChildren().addAll(baud, baudT) ;
		g.getChildren().addAll(hb1, hb2) ;
		
		Label xbeeCon = new Label("XBEE DECONNECTE") ;
		Label xbeeStart = new Label("ACQUISITION OFF") ;
		c.setCenterX(500); c.setCenterY(50); c.setRadius(10); c.setFill(Color.BLACK);
		c2.setCenterX(500); c2.setCenterY(90); c2.setRadius(10); c2.setFill(Color.BLACK);
		xbeeCon.setLayoutX(380) ; xbeeCon.setLayoutY(42) ;
		xbeeStart.setLayoutX(380) ; xbeeStart.setLayoutY(82) ;
		g.getChildren().addAll(xbeeCon, xbeeStart, c, c2) ;
		
		
		ButConnect.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						xbeeCom.XbeeConnect(xbee, portXbee, baudT) ;

						if(xbeeCom.getConnect() == 0) {
							xbeeCom.setConnect(1) ;
							c.setFill(Color.GREEN) ;
							System.out.println("XBEE CONNECTE");
							xbeeCon.setText("XBEE CONNECTE") ;
						}
						else if(xbeeCom.getConnect() == 1) {
							xbeeCom.setConnect(0) ;
							c.setFill(Color.BLACK) ;
							System.out.println("XBEE DECONNECTE");
							xbeeCon.setText("XBEE DECONNECTE") ;
						}
						g.getChildren().removeAll(xbeeCon, c) ;
					    g.getChildren().addAll(xbeeCon, c) ;
						}
			    }) ;
		
		ButStart.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						if(xbeeCom.getConnect() == 1) {
							if(start == 0) {
								start = 1 ;
								System.out.println("XBEE START") ;
								xbeeStart.setText("ACQUISITION ON") ;
								c2.setFill(Color.RED) ;
								xbeeCom.SamplefromXbee(xbee) ;
							}
							else if(start == 1) {
								start = 0 ;
								System.out.println("XBEE STOP") ;
								xbeeStart.setText("ACQUISITION OFF") ;
								c2.setFill(Color.BLACK) ;
							}
						}
						else if (xbeeCom.getConnect() == 0) {
							System.out.println("XBEE PAS CONNECTE, COMMENCEZ PAR APPUYER SUR LE BOUTON CONNECTE");
						}
						g.getChildren().removeAll(xbeeStart, c2) ;
					    g.getChildren().addAll(xbeeStart, c2) ;
					}
			    }) ;
	
		return g ;
	}
	
	private Node AfficheXBee(XBee xbee, XbeeCommunication xbeeCom) {
		Group g = new Group() ;
        System.out.println("test") ;
        xbeeCom.SamplefromXbee(xbee) ;
		Screen screen = new Screen() ;
		g.getChildren().clear();
		g.getChildren().add(screen.drawXBeeData(test[i], i)) ;
		System.out.println("test["+i+"]"+"="+test[i]) ;
		i++ ;
		return g ;
	}
	

}
