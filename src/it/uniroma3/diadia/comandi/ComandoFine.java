package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaProperties;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	public static final String MESSAGGIO_FINE = DiaProperties.getProperty("MESSAGGIO_FINE");	
	
	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIOConsole();
		io.mostraMessaggio(MESSAGGIO_FINE);
		partita.setFinita();
	}
	
}
