package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIOConsole();
		
		for(int i=0; i < DiaDia.elencoComandi.length; i++) 
			io.mostraMessaggio(DiaDia.elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}
	
}
