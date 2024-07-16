package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String PRESENTAZIONE = "\nSono una strega, sono contenta che esistono ancora giovani educati!";
	private static final String MESSAGGIO_CATTIVA = "Sei un po' maleducato, sparisci!";
	private static final String MESSAGGIO_GENTILE = "Mi stai simpatico, " + "quindi ora ti mando nella stanza vicina con piu'attrezzi!";
	private static final String MESSAGGIO_RINGRAZIO = "Mwahahahaha ora l'attrezzo e' mio!";

	public Strega(String nome) {
		super(nome, PRESENTAZIONE, null);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.haSalutato()) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteConPiuAttrezzi());
			msg = MESSAGGIO_GENTILE;
		}
		else {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteConMenoAttrezzi());
			msg = MESSAGGIO_CATTIVA;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RINGRAZIO;
	}
}
