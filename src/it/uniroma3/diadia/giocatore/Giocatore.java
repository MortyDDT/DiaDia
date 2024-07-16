package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.DiaProperties;

public class Giocatore {
	
	static final public int CFU_INIZIALI = Integer.parseInt(DiaProperties.getProperty("CFU_INIZIALI"));
	
	private int cfu;
	private Borsa borsa;
	
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	
	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public Borsa getBorsa() {
		return borsa;
	}
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	
	
	
}
