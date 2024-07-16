package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando {
	private String nomeAttrezzo;
	
	public ComandoPrendi() {}
	
	public ComandoPrendi(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public void esegui(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		final IO io = partita.getIOConsole();

		if(!corrente.hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Attrezzo "+nomeAttrezzo+" non presente!");
			return;
		}
		Attrezzo attrezzo = corrente.getAttrezzo(nomeAttrezzo);
		if(!borsa.addAttrezzo(attrezzo)) {
			io.mostraMessaggio("L'attrezzo "+nomeAttrezzo+" e troppo pesante!");
		}else {
			corrente.removeAttrezzo(attrezzo);
			io.mostraMessaggio("Attrezzo "+nomeAttrezzo+" preso!");
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
