package it.uniroma3.diadia.ambienti;

import java.util.List;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

	private String attrezzoNecessario;
	private Direzione direzioneBloccata;


	public StanzaBloccata(String nome, String nomeAttrezzo, Direzione direzione) {
		super(nome);
		this.attrezzoNecessario = nomeAttrezzo;
		this.direzioneBloccata = direzione;
	}


	@Override
	public String getDescrizione() {
        return this.toString();
	}
	
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione.equals(direzioneBloccata))
			if(!this.hasAttrezzo(attrezzoNecessario))
				return this;
		return super.getAdiacenti().get(direzione);
	}
	
	
	
	@Override
	public String toString() {
		List<Attrezzo> attrezzi = super.getAttrezzi();
		
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getNome());
		risultato.append("\nUscite: ");
		
		for (Direzione direzione : super.getAdiacenti().keySet())
			risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		
		for(Attrezzo a : attrezzi)
			risultato.append(a+" ");
		
		if(!this.hasAttrezzo(attrezzoNecessario)) {
			risultato.append("\nDirezione "+direzioneBloccata+" e' bloccata!");
			risultato.append("\nC'e bisogno dell'attrezzo "+attrezzoNecessario+" per sbloccare l'uscita!");
		}

		return risultato.toString();
	}

}
