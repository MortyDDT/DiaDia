package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
		
	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIOConsole();
		String descrizioneStanza = partita.getStanzaCorrente().getDescrizione();
		String attrezziBorsa = partita.getGiocatore().getBorsa().toString();
		
		io.mostraMessaggio(descrizioneStanza);
		io.mostraMessaggio("CFU Rimasti: " + partita.getCfu());
		io.mostraMessaggio(attrezziBorsa);
	}

}
