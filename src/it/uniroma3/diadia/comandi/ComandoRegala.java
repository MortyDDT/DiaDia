package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoRegala extends AbstractComando {

	private static final String MESSAGGIO_A_CHI = "A chi dovrei dare questo attrezzo ?...";
	private String nomeAttrezzo;
	private Stanza corrente;
	private Borsa borsa;
	private Attrezzo attrezzo;

	public ComandoRegala() {}
	
	public ComandoRegala(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIOConsole();
		corrente = partita.getStanzaCorrente();
		borsa = partita.getGiocatore().getBorsa();

		if(partita.getStanzaCorrente().getPersonaggio() != null) {
			if(!borsa.hasAttrezzo(nomeAttrezzo)) {
				io.mostraMessaggio("Attrezzo "+nomeAttrezzo+" non presente nella borsa!");
				return;
			}

			attrezzo = this.borsa.removeAttrezzo(nomeAttrezzo);		
			io.mostraMessaggio("Attrezzo "+nomeAttrezzo+" regalato a "+corrente.getPersonaggio().getNome()+"!");
			io.mostraMessaggio(corrente.getPersonaggio().riceviRegalo(attrezzo, partita));
		}else
			partita.getIOConsole().mostraMessaggio(MESSAGGIO_A_CHI);
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
