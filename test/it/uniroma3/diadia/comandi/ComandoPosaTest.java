package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class ComandoPosaTest {
	
	private Partita partita;
	private Labirinto monolocale;


	
	@Before
	public void creaBorsaPerTest(){		
		monolocale = new LabirintoBuilder()
	    		 .addStanzaIniziale("salotto")
	    		 .addAttrezzo("spada", 1)
	    		 .addAttrezzo("lanterna", 1)
	    		 .getLabirinto();

	}

	@Test
	public void testPosaAttrezzoSingleton() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("posa spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, monolocale);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());	
	}
	
	@Test
	public void testPosaUnUnicoAttrezzo() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("prendi lanterna");
		comandiDaEseguire.add("posa spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, monolocale);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testPosaAttrezzoInesistente() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("posa lanterna");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, monolocale);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));	
	}
	
	@Test
	public void testPosaABorsaVuota() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("posa lanterna");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, monolocale);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());		
	}
	
	@Test
	public void testVerificaSeInStanzaVieneAggiuntoIlAttrezzo() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("posa spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, monolocale);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));		
	}

}
