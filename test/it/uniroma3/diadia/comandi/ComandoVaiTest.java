package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class ComandoVaiTest {
	
	private Labirinto monolocale;
	private Labirinto bilocale;
	private IOSimulator ioS;
	private Partita partita;
	
	@Before
	public void creaLabirintoPerTest(){
		monolocale = new LabirintoBuilder()
	    		 .addStanzaIniziale("salotto")
	    		 .addStanzaVincente("salotto")
	    		 .getLabirinto();
		
		bilocale = new LabirintoBuilder()
				 .addStanzaIniziale("salotto")
				 .addStanza("camera")
				 .addAttrezzo("letto" ,10)
				 .addAdiacenza("salotto", "camera", Direzione.nord)
				 .getLabirinto();
		
	}

	@Test
	public void testIOSimulator() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai sud");
		comandiDaEseguire.add("fine");
		ioS = Fixture.creaSimulazione(comandiDaEseguire);
		
		assertTrue(ioS.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertContains("Aula N10", ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, ioS.nextMessaggio());	
		
	}
	
	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}
	
	@Test
	public void testVaiDirezioneInMonolocale() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai sud");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, monolocale);
		
		assertTrue(this.partita.getStanzaCorrente().getNome().equals("salotto"));
	}
	
	@Test
	public void testVaiDirezioneInBilocale() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		
		assertTrue(this.partita.getStanzaCorrente().getNome().equals("camera"));
	}
	
	@Test
	public void testVaiETornaInBilocale() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("vai sud");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		
		assertTrue(this.partita.getStanzaCorrente().getNome().equals("salotto"));
	}
}

