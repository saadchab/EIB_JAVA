package model ;

import java.util.ArrayList;

public class SignalTools extends Signal {
	
	double mean, variance ;
	
	public SignalTools() {
		super() ;
	}
	
	public double computeTNS(Signal Sig, String string) {
		double val = 0 ;
		switch(string) {
			case "mean" : val = getMeanSignal(Sig.getSig()) ;
						  break ;
			case "variance" : val = getVarianceSignal(Sig.getSig()) ;
							  break ;
		}
		return val ;
	}
	
	public double getMeanSignal(ArrayList<Double> Sig) {
		double somme = 0 ;
		int n = Sig.size() ;
		for(int i=0;i<n;i++) {
			somme = somme + Sig.get(i) ;
		}
		mean = somme/n ;
		return mean ;
	}
	
	public double getVarianceSignal(ArrayList<Double> Sig) {
		double somme = 0 ;
		int n = Sig.size() ;
		for(int i=0;i<n;i++) {
			somme = somme + Math.pow(Sig.get(i)-getMeanSignal(Sig), 2)/n ;
		}
		variance = somme/n ;
		return variance ;
	}
	
	/*public Complex[] getFFTSignal(double[] Sig) {
		double somme = 0 ;
		int n = Sig.length ;
		return fft() ; 
	}*/

}
