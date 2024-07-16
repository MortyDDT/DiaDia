package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {
	
	private static final String MESSAGGIO_CON_CHI = "A chi dovrei salutare ?...";
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() != null)
			partita.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else
			partita.getIOConsole().mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
}
