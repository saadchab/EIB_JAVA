package model;

import java.util.ArrayList;

public class Signal {

	private int Fe;
	private int Freq;
	private int NbEch;
	private int nbBits;
	private String Units;
	private ArrayList<Double> sig = new ArrayList<Double>();

	public Signal() {
	}

	public void drawSignal(SignalType f) {
		sig.clear();
		switch (f) {
		case NOISE:
			drawNoise();
			break;
		case SINE:
			drawSinus();
			break;
		case SQUARE:
			drawSquare();
			break;

		}
	}

	public void drawNoise() {
		for (int i = 0; i < NbEch; i++) {
			sig.add(Math.random());
		}
	}

	public void drawSinus() {
		double sample = Fe / Freq;
		for (int i = 0; i < NbEch; i++) {
			double angle = (2.0 * Math.PI * i) / sample;
			sig.add(Math.sin(angle));
		}
	}

	public void drawSquare() {
		for (int i = 0; i < (NbEch / 2); i++) {
			sig.add((double) 0);
		}
		for (int i = (NbEch / 2); i < NbEch; i++) {
			sig.add((double) 1);
		}
	}

	public void setFe(int sFe) {
		this.Fe = sFe;
	}

	public int getFe() {
		return this.Fe;
	}

	public void setUnits(String sUnits) {
		this.Units = sUnits;
	}

	public String getUnits() {
		return this.Units;
	}

	public void setNbEch(int sNbEch) {
		this.NbEch = sNbEch;
	}

	public int getNbEch() {
		return this.NbEch;
	}

	public void setFreq(int sFreq) {
		this.Freq = sFreq;
	}

	public int getFreq() {
		return this.Freq;
	}

	public ArrayList<Double> getSig() {
		return this.sig;
	}

	public void setSig(ArrayList<Double> Sig) {
		this.sig = Sig;
	}
}
