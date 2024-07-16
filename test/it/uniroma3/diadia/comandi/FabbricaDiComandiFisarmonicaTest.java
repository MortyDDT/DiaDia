package it.uniroma3.diadia.comandi;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class FabbricaDiComandiFisarmonicaTest {
	
	private static final String VAI_SUD = "vai sud";
	private static final String AIUTO = "aiuto";
	private static final String GUARDA = "guarda";
	private static final String PRENDI = "prendi";
	private static final String POSA = "posa";
	private static final String FINE = "fine";
	
	
	private IOConsole io;
	private Partita partita;
	private AbstractComando comando;
	private FabbricaDiComandi factory;
	
	
	@Before
	public void creaPartitaPerITest(){
		this.io = new IOConsole();
		this.partita = new Partita(io);
		this.factory = new FabbricaDiComandiFisarmonica();
		
	}

	@Test
	public void testComandoVai() {
		comando = factory.costruisci(VAI_SUD);
		comando.esegui(this.partita); 
	}
	
	@Test
	public void testComandoAiuto() {
		comando = factory.costruisci(AIUTO);
		comando.esegui(this.partita); 
	}
	
	@Test
	public void testComandoGuarda() {
		comando = factory.costruisci(GUARDA);
		comando.esegui(this.partita); 
	}
	
	@Test
	public void testComandoPrendi() {
		comando = factory.costruisci(PRENDI);
		comando.esegui(this.partita); 
	}
	
	@Test
	public void testComandoPosa() {
		comando = factory.costruisci(POSA);
		comando.esegui(this.partita); 
	}
	
	@Test
	public void testComandoFine() {
		comando = factory.costruisci(FINE);
		comando.esegui(this.partita); 
	}

}
