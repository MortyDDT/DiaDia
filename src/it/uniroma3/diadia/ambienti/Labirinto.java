package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private static final String FILE = "labirinto.txt";
	private CaricatoreLabirinto lab;
	public Stanza stanzaIniziale;
	public Stanza stanzaVincente;
	
	
	private Labirinto() {
		this.creaStanze();
	}
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    public void creaStanze() {
    	try {
        	lab = new CaricatoreLabirinto(FILE);
        	lab.carica();
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			e.printStackTrace();
		}

    	this.stanzaIniziale = lab.getStanzaIniziale();
    	this.stanzaVincente = lab.getStanzaVincente();
    }


    
	 public static Labirinto newBuilder(String tipo) {
		 if (tipo == null || tipo.isEmpty())
	            return null;
	        switch (tipo) {
	        case "labirintoBuilder":
	            return new LabirintoBuilder();
	        case "labirintoDaCaricatore":
	            return new Labirinto();
	        default:
	            throw new IllegalArgumentException("Non esiste un labirinto del tipo" +tipo);
	        }
	 }
    
    /* CLASSE BUILDER */
    public static class LabirintoBuilder extends Labirinto {	
    	
    	private List<Stanza> stanze = new LinkedList<>();    	
    	
    	/*CREA UNA NUOVA STANZA INIZIALE*/
    	public LabirintoBuilder addStanzaIniziale(String nome) {
    		super.stanzaIniziale = new Stanza(nome);
    		this.stanze.add(super.stanzaIniziale);
    		return this;
    	}
    	
    	
    	/*IMPOSTA O CREA LA STANZA VINCENTE*/
    	public LabirintoBuilder addStanzaVincente(String nome) {
    		Stanza vincente = null;
    		for(Stanza s : stanze)
    			if(s.getNome().equals(nome))
    				vincente = s;
    		if(vincente == null) {
    			vincente = new Stanza(nome);
    			stanze.add(vincente);
    		}
    		super.stanzaVincente = vincente;
    		return this;
    	}

    	
    	/* AGGIUNGI UN STANZA */
    	public LabirintoBuilder addStanza(String nome) {
    		Stanza nuova = new Stanza(nome);
    		this.stanze.add(nuova);
    		return this;
    	}
    	
    	
    	/* AGGIUNGI UN STANZA MAGICA*/
    	public LabirintoBuilder addStanzaMagica(String nome) {
    		Stanza nuova = new StanzaMagica(nome);
    		this.stanze.add(nuova);
    		return this;
    	}
    	
    	
    	/* AGGIUNGI UN STANZA BUIA*/
    	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoNecessario) {
    		Stanza nuova = new StanzaBuia(nome, attrezzoNecessario);
    		this.stanze.add(nuova);
    		return this;
    	}
    	
    	
    	/* AGGIUNGI UN STANZA BLOCCATA*/
    	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoNecessario, Direzione direzioneBloccata) {
    		Stanza nuova = new StanzaBloccata(nome, attrezzoNecessario, direzioneBloccata);
    		this.stanze.add(nuova);
    		return this;
    	}
    	
    	
    	/*AGGIUNGE UN'ATTREZZO ALL'ULTIMA STANZA*/
    	public LabirintoBuilder addAttrezzo(String nome, int peso) {
    		Attrezzo nuovo = new Attrezzo(nome, peso);
    		this.stanze.get(this.stanze.size() - 1).addAttrezzo(nuovo);
    		return this;
    	}

    	
    	/*IMPOSTA UN'ADIACENZA ALLA PRIMA STANZA CON LA SECONDA NELLA DATA DIREZIONE*/
    	public LabirintoBuilder addAdiacenza(String primaStanza, String secondaStanza, Direzione direzioneSecondaStanza) {
    		Stanza prima = null;
    		Stanza seconda = null;
    		for(Stanza s : stanze) {
    			if(s.getNome().equals(primaStanza))
    				prima = s;
    			if(s.getNome().equals(secondaStanza))
    				seconda = s;
    		}
    		if(prima != null && seconda != null) {
    			prima.impostaDoppiaAdiacenza(seconda, direzioneSecondaStanza);
    		}
    		return this;
    	}
    	
    	
    	/*RITORNA IL LABIRINTO CREATO*/
    	public LabirintoBuilder getLabirinto() {
    		return this;
    	}

    }

}
