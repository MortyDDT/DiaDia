package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;

public class PartitaTest {

	private Partita partita;
	private IOConsole io;
	
	@Before	
	public void creaPartitaPerIlTest() {
		this.partita = new Partita(io);
	}
	
	@Test
	public void testStanzaVincenteEsistente() {
		assertNotNull(this.partita.getStanzaVincente());
	}
	
	@Test
	public void testVintaSeInStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testNonVintaNonInStanzaVincente() {
		Stanza StanzaNonVincente = new Stanza("StanzaNonVincente");
		this.partita.setStanzaCorrente(StanzaNonVincente);
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testNonVintaInizioPartita() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testIsFinitaInStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaInStanzaNonVincente() {
		Stanza StanzaNonVincente = new Stanza("StanzaNonVincente");
		this.partita.setStanzaCorrente(StanzaNonVincente);
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita0CFU() {
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}
}
