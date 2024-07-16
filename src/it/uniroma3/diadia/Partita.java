package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private IO io;
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;



	public Partita(IO io2){
		this.labirinto = Labirinto.newBuilder("labirintoDaCaricatore");
		this.giocatore = new Giocatore();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.io = io2;
		this.finita = false;
	}


	public Partita(IO io2, Labirinto labirintoEsterno){
		this.labirinto = labirintoEsterno;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.io = io2;
		this.finita = false;
	}



	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}
	public Giocatore getGiocatore() {
		return giocatore;
	}
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	public int getCfu() {
		return this.giocatore.getCfu();
	}
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);
	}
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente= stanzaCorrente;
	}
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public Stanza getStanzaVincente() {
		return this.labirinto.stanzaVincente;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}


	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getCfu() == 0);
	}


	public IO getIOConsole() {
		return this.io;
	}


	public boolean giocatoreIsVivo() {
		if(this.getGiocatore().getCfu() == 0)
			return false;
		return true;
	}




}
