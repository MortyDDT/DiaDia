package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String PRESENTAZIONE = "\nWoof Woof grrr! (Non ti avvicinare troppo grr!)";
	private static final String MESSAGGIO_MORDE = "Grrr *CHOMP* \nIl cane ti ha morso, quindi perdi 1 CFU!";
	private static final String MESSAGGIO_AMICHEVOLE = "Il cane sembra piu' amichevole e ti lascia un attrezzo!";
	private static final String MESSAGGIO_RINGRAZIO = "Woof Woof (Il cane sembra felice)\nVedi il cane che si sposta e dietro a lui c'era un'attrezzo! ";
	private String attrezzoDesiderato;
	private boolean hasAttrezzoDesiderato;
	
	public Cane(String nome, String attrezzoDesiderato, Attrezzo daDare) {
		super(nome, PRESENTAZIONE, daDare);
		this.attrezzoDesiderato = attrezzoDesiderato;
		this.hasAttrezzoDesiderato = false;
	}
	
	@Override
	public String saluta() {
		StringBuilder risposta = new StringBuilder();
		if (!this.haSalutato())
			risposta.append(PRESENTAZIONE);
		else
			risposta.append(".. Grrrr!");
		this.haSalutato = true;
		return risposta.toString();
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.hasAttrezzoDesiderato) {
			partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
			this.setAttrezzo(null);
			msg = MESSAGGIO_AMICHEVOLE;
		}
		else {
			partita.setCfu(partita.getCfu() - 1);
			msg = MESSAGGIO_MORDE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(attrezzoDesiderato)) {
			partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
			this.setAttrezzo(null);
			this.hasAttrezzoDesiderato = true;
			return MESSAGGIO_RINGRAZIO;
		}
		partita.setCfu(partita.getCfu() - 1);
		return MESSAGGIO_MORDE;
	}

}
