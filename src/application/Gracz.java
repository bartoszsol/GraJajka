package application;

import java.io.Serializable;

public class Gracz implements Serializable {
	private static final long serialVersionUID = -7887612267521882048L;
	String imie;
	int wynik;
	
	public Gracz(String imie, int wynik) {
		this.imie = imie;
		this.wynik = wynik;
	}

	public Gracz(String nazwaGracza) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Gracz [imie=" + imie + ", wynik=" + wynik + "]";
	}
	
	
}
