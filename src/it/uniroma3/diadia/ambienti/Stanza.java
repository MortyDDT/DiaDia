package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.DiaProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {
	
	public final static int NUMERO_MASSIMO_DIREZIONI = Integer.parseInt(DiaProperties.getProperty("NUMERO_MASSIMO_DIREZIONI"));
	public final static int NUMERO_MASSIMO_ATTREZZI = Integer.parseInt(DiaProperties.getProperty("NUMERO_MASSIMO_ATTREZZI"));
	
	private String nome;
	private AbstractPersonaggio personaggio;
	private int numeroStanzeAdiacenti;
	int numeroAttrezzi;

	private List<Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;


	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.personaggio = null;
		this.attrezzi = new ArrayList<>();
		this.stanzeAdiacenti = new HashMap<>();
	}


	/**
	 * Imposta una stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 */
	public void impostaStanzaAdiacente(Stanza stanza, Direzione direzione) {
		if(numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
			this.stanzeAdiacenti.put(direzione, stanza);
			numeroStanzeAdiacenti++;
		}
	}

	/*CREA UNA DOPPIA ADIACENZA*/
	public void impostaDoppiaAdiacenza(Stanza adiacente, Direzione d) {
		this.impostaStanzaAdiacente(adiacente, d);
		adiacente.impostaStanzaAdiacente(this, d.opposta());
	}


	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}


	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.add(attrezzo);
			this.numeroAttrezzi++;
			return true;
		}
		return false;
	}


	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato = false;
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
				trovato = true;
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi)
			if(attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzo;
		return attrezzoCercato;	
	}


	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}


	public Map<Direzione, Stanza> getAdiacenti() {
		return this.stanzeAdiacenti;
	}


	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for(Direzione key : stanzeAdiacenti.keySet())
			risultato.append(" " + key);
		risultato.append("\nAttrezzi nella stanza: ");
		this.attrezzi.forEach(attrezzo -> risultato.append(attrezzo+" "));
		if(this.getPersonaggio() != null)
			risultato.append("\nPersonaggio nella stanza: "+this.getPersonaggio().getNome());
		return risultato.toString();
	}

	public int getNumeroStanzeAdiacenti() {
		return numeroStanzeAdiacenti;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public Stanza getStanzaAdiacenteConPiuAttrezzi() {
		int nAttrezzi = -1;
		Stanza stanzaConPiuAttrezzi = null;
		for(Stanza s : this.getAdiacenti().values())
			if(s.getAttrezzi().size() > nAttrezzi) {
				stanzaConPiuAttrezzi = s;
				nAttrezzi = s.getAttrezzi().size();
			}
		return stanzaConPiuAttrezzi;
	}

	public Stanza getStanzaAdiacenteConMenoAttrezzi() {
		int nAttrezzi = 11;
		Stanza stanzaConMenoAttrezzi = null;
		for(Stanza s : this.getAdiacenti().values())
			if(s.getAttrezzi().size() < nAttrezzi) {
				stanzaConMenoAttrezzi = s;
				nAttrezzi = s.getAttrezzi().size();
			}
		return stanzaConMenoAttrezzi;
	}


}