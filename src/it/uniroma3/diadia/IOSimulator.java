package it.uniroma3.diadia;

import java.util.LinkedList;
import java.util.List;

public class IOSimulator implements IO {
	
	private List<String> produci;
	private List<String> leggi;
	
	
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.produci = new LinkedList<>();
		this.leggi = righeDaLeggere;
	}
	
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.produci.add(messaggio);
	}
	
	
	@Override
	public String leggiRiga() {
		if(!this.leggi.isEmpty())
			return this.leggi.remove(0);
		return null;
	}
	
	
	public String nextMessaggio() {
		if(hasNextMessaggio())
			return this.produci.remove(0);
		return null;
	}
	
	public boolean hasNextMessaggio() {
		return !this.produci.isEmpty();
	}
	
}
