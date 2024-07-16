package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {
	
	private Stanza corrente;
	private Borsa borsa;
	private String nomeAttrezzo;
	private Attrezzo attrezzo;
	
	
	public ComandoPosa() {}
	
	public ComandoPosa(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIOConsole();

		corrente = partita.getStanzaCorrente();
		borsa = partita.getGiocatore().getBorsa();

		
		if(!borsa.hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Attrezzo "+nomeAttrezzo+" non presente nella borsa!");
			return;
		}
		
		attrezzo = this.borsa.removeAttrezzo(nomeAttrezzo);		
		corrente.addAttrezzo(attrezzo);
		
		io.mostraMessaggio("Attrezzo "+nomeAttrezzo+" posato!");
		
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
