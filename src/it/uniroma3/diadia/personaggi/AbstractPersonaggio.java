package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	protected boolean haSalutato;
	private Attrezzo attrezzo;


	public AbstractPersonaggio(String nome, String presentaz, Attrezzo a) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
		this.attrezzo = a;
	}

	public String getNome() {
		return this.nome;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);


	public String saluta() {
		StringBuilder risposta = 
				new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+"."); 
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append(".. Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}
	
	abstract public String agisci(Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}

	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}
	
	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

}
