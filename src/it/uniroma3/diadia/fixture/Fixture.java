package it.uniroma3.diadia.fixture;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List;

public class Fixture {

	public static IOSimulator creaSimulazione(List<String> righeDaLeggere){
		IOSimulator io = new IOSimulator(righeDaLeggere);
		new DiaDia(io).gioca();
		return io;
	}
	
	public static Partita creaSimulazione(List<String> righeDaLeggere, Labirinto labirinto){
		IOSimulator io = new IOSimulator(righeDaLeggere);
		DiaDia dia = new DiaDia(io, labirinto);
		dia.gioca();
		return dia.getPartita();
	}
	
	public static Attrezzo creaAttrezzoEAggiungiInStanza(Stanza daRiempire, String nomeAttrezzo, int peso){
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		daRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}
	
	
}
