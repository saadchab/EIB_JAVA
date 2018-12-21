package view ;

import java.util.ArrayList;

import javafx.application.Application ;
import javafx.scene.*;
import javafx.scene.chart.* ;
import javafx.stage.* ;

public class Screen extends Application {
	
	final NumberAxis xAxis = new NumberAxis() ;
	final NumberAxis yAxis = new NumberAxis() ;
	final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis) ;
	final XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>() ;
	
	Screen() {
		
		xAxis.setLabel("Time") ;
		yAxis.setLabel("Voltage") ;
		
		lineChart.setTitle("Signals Acquisition") ;

	}
	
	Node drawScreen(ArrayList<Double> Sig)  {
	
		lineChart.getData().clear() ;
		
	    for (int i=0;i<Sig.size();i++) {
	    	series.getData().add(new XYChart.Data<Number, Number>(i, Sig.get(i))) ;
	    }
	    
	    lineChart.getData().add(series) ;
	    
	    return lineChart ;
	    
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*public static void addOrRemoveSignal(boolean choix) {
		Button buttonAdd = new Button("Add") ;
		Button buttonRemove = new Button("Remove") ;
		if(choix) {
			buttonAdd.setOnMouseClicked(
				new EventHandler<MouseEvent >() {
					public void handle(MouseEvent e) {
						XYChart.Series series1 = new XYChart.Series() ;
						lineChart.getData().add(series1) ;
							
					}
			    }) ;
		}
		else if (!choix) {
				
		}
		
	}*/
	
}
