package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.DiaProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

public class Borsa{
	public final static int PESO_MAX_BORSA = Integer.parseInt(DiaProperties.getProperty("PESO_MAX_BORSA"));
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
	}


	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}


	public int getPesoMax() {
		return pesoMax;
	}
	
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a : this.attrezzi)
			if(a.getNome().equals(nomeAttrezzo))
				 return a;
		return null;
	}


	public int getPeso() {
		int peso = 0;
		for(Attrezzo a : this.attrezzi)
			 peso += a.getPeso();
		return peso;
	}


	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}


	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}


	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while (iteratore.hasNext()) {
			a = iteratore.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				iteratore.remove();
				return a;
			}
		}
		return null;
	}

	
	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Collections.sort(this.attrezzi, new ComparatorePerPeso());
		return this.attrezzi;
	}
	
	
	List<Attrezzo> getContenutoOrdinatoPerPesoReverse(){
		List<Attrezzo> inversa = this.getContenutoOrdinatoPerPeso();
		Collections.reverse(inversa);
		return this.attrezzi;
	}
	
	
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		TreeSet<Attrezzo> alberoAttrezzi = new TreeSet<>(new ComparatorePerNome());
		alberoAttrezzi.addAll(this.attrezzi);
		return alberoAttrezzi;
	}
	
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> alberoAttrezzi = new TreeSet<>(new ComparatorePerPeso());
		alberoAttrezzi.addAll(this.attrezzi);
		return alberoAttrezzi;
	}

	
	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Set<Attrezzo> tmp;
		Map<Integer, Set<Attrezzo>> peso2Attrezzi = new HashMap<>();
		
		for(Attrezzo a : this.attrezzi) {
			tmp = peso2Attrezzi.get(a.getPeso());
			if(tmp == null) {
				tmp = new TreeSet<>(new ComparatorePerNome());
				peso2Attrezzi.put(a.getPeso(), tmp);
			}
			tmp.add(a);
		}
		return peso2Attrezzi;
	}


	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			this.getContenutoOrdinatoPerPesoReverse().forEach(attrezzo -> s.append(attrezzo.toString()+" "));
		}
		else 
			s.append("Borsa vuota");
		return s.toString();
	}

	
	
}
