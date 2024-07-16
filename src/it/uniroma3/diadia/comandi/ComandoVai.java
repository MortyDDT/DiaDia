package it.uniroma3.diadia.comandi;

import java.util.Arrays;
import java.util.List;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	private Direzione direzione;
	
	public ComandoVai() {}
	
	public ComandoVai(String parametro) {
		List<String> direzioniPossibili = Arrays.asList("nord", "sud", "est", "ovest");
		if(direzioniPossibili.contains(parametro))
			this.direzione = Direzione.valueOf(parametro);
		else
			this.direzione = null;
	}

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIOConsole();
		
		if(direzione == null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		
		Stanza corrente = partita.getStanzaCorrente();
		Stanza prossima = null;
		prossima = corrente.getStanzaAdiacente(direzione);

		
		if (prossima == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}else {
			partita.setStanzaCorrente(prossima);
			int cfu = partita.getCfu();
			cfu--;
			partita.setCfu(cfu);
		}
		
		if (prossima != null)
			io.mostraMessaggio(prossima.getDescrizione());

	}
	
	@Override
	public void setParametro(String parametro) {
		List<String> direzioniPossibili = Arrays.asList("nord", "sud", "est", "ovest");
		if(direzioniPossibili.contains(parametro))
			this.direzione = Direzione.valueOf(parametro);
		else
			this.direzione = null;	
	}

}
