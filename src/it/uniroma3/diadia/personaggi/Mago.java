package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String PRESENTAZIONE = "\nSono un mago, piacere di conoscerti!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " + "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	private static final String MESSAGGIO_RINGRAZIO = "Ti ringrazio non ne ho bisogno, pero ti posso aiutare facendolo meno pesante!";


	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE, attrezzo);
	}

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione, attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.getAttrezzo() != null) {
			partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
			this.setAttrezzo(null);
			msg = MESSAGGIO_DONO;
		}
		else
			msg = MESSAGGIO_SCUSE;
		return msg;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2));
		return MESSAGGIO_RINGRAZIO;
	}
}
