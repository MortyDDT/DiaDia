package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa;
	private static final String ATTREZZO = "attrezzoSemplice";
	
	
	private Attrezzo creaAttrezzoEAggiungiInBorsa(Borsa borsa, String nomeAttrezzo, int peso) {
		Attrezzo nuovoAttrezzo = new Attrezzo(nomeAttrezzo, peso);
		borsa.addAttrezzo(nuovoAttrezzo);
		return nuovoAttrezzo;
	}
	
	
	@Before
	public void creaBorsaPerTest() {
		this.borsa = new Borsa(Borsa.PESO_MAX_BORSA);
	}
	
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo = creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 1);
		assertEquals(attrezzo, this.borsa.getAttrezzo(ATTREZZO));
	}

	@Test
	public void testAddAttrezzoTroppoPesante() {
		Attrezzo attrezzo = new Attrezzo("attrezzoTroppoPesante", Borsa.PESO_MAX_BORSA + 1);
		assertFalse(this.borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoInesistente() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 1);
		assertNull(this.borsa.getAttrezzo("attrezzoInesistente"));
	}
	
	@Test
	public void testGetAttrezzoEsistente() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 1);
		assertNotNull(this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoEsistente() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 1);
		assertTrue(this.borsa.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoInesistente() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 1);
		assertFalse(this.borsa.hasAttrezzo("attrezzoInesistente"));
	}
	
	@Test
	public void testsGetPesoIniziale() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPesoMassimo() {
		assertEquals(Borsa.PESO_MAX_BORSA, this.borsa.getPesoMax());
	}
	
	@Test
	public void testGetPesoDopoAggiungereAttrezzo() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 5);
		assertEquals(5, this.borsa.getPeso());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 5);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testRemoveAttrezzo() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 5);
		this.borsa.removeAttrezzo(ATTREZZO);
		assertFalse(this.borsa.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testVerificaAttrezzoRimosso() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 5);
		Attrezzo attrezzoRimosso;
		attrezzoRimosso = this.borsa.removeAttrezzo(ATTREZZO);
		assertNotNull(attrezzoRimosso);
	}
	
	@Test
	public void testRemoveAttrezzoPesoZero() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 5);
		this.borsa.removeAttrezzo(ATTREZZO);
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testRemoveAttrezzoPiuElementi() {
		creaAttrezzoEAggiungiInBorsa(borsa, ATTREZZO, 5);
		creaAttrezzoEAggiungiInBorsa(borsa, "altroAttrezzo", 5);
		this.borsa.removeAttrezzo("altroAttrezzo");
		assertNotNull(this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testOrdinaListaPerPesoDiverso() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 5);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 4);
		assertEquals(b, this.borsa.getContenutoOrdinatoPerPeso().get(0));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testOrdinaListaPerPesoUguale() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 5);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 5);
		assertEquals(a, this.borsa.getContenutoOrdinatoPerPeso().get(0));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testOrdinaListaPerPesoDiversoReverse() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 5);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 4);
		assertEquals(a, this.borsa.getContenutoOrdinatoPerPesoReverse().get(0));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testOrdinaListaPerPesoUgualeReverse() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 5);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 5);
		assertEquals(b, this.borsa.getContenutoOrdinatoPerPesoReverse().get(0));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testSortedSetPerNome() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 5);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 1);
		assertEquals(a, this.borsa.getContenutoOrdinatoPerNome().first());
	}
	
	@Test
	public void testMapAttrezziConPesoDiverso() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 1);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 2);
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().get(1).contains(a));
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().get(2).contains(b));
	}
	
	@Test
	public void testMapAttrezziConPesoUguale() {
		Attrezzo a = creaAttrezzoEAggiungiInBorsa(borsa, "a", 1);
		Attrezzo b = creaAttrezzoEAggiungiInBorsa(borsa, "b", 1);
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().get(1).contains(a));
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().get(1).contains(b));
	}
	
	@Test
	public void testMapAttrezziBorsaVuota() {
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().isEmpty());
		assertEquals(Collections.emptyMap(), this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testMapAttrezziSingleton() {
		Attrezzo attrezzo = new Attrezzo("test", 1);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(Collections.singletonMap(1, Collections.singleton(attrezzo)), this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testMapAttrezziDoubleton() {
		int stesoPeso = 1;
		Attrezzo attrezzo = new Attrezzo("test", stesoPeso);
		Attrezzo attrezzo2 = new Attrezzo("test2", stesoPeso);
		this.borsa.addAttrezzo(attrezzo);
		this.borsa.addAttrezzo(attrezzo2);
		Set<Attrezzo> insiemeAttrezziStesoPeso = new HashSet<>(Arrays.asList(attrezzo, attrezzo2));
		
		assertEquals(Collections.singletonMap(stesoPeso, insiemeAttrezziStesoPeso), this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
}
