package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class ComandoPrendiTest {

	private Partita partita;
	private Labirinto bilocale;

	
	@Before
	public void impostaStanzaPerITest(){
		
		bilocale = new LabirintoBuilder()
	    		 .addStanzaIniziale("salotto")
	    		 .addAttrezzo("spada", 5)
	    		 .addAttrezzo("lanterna", 5)
	    		 .addAttrezzo("piombo", 11)
	    		 .addStanza("cucina")
	    		 .addAdiacenza("salotto", "cucina", Direzione.nord)
	    		 .getLabirinto();

	}

	
	@Test
	public void testPrendiAttrezzoSingleton() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testPrendiAttrezzoInesistente() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi chiave");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testPrendiAttrezzoInStanzaVuota() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}

	@Test
	public void testAttrezzoPresoDallaStanza() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testAttrezzoRimanenteInStanzaDopoComandoPrendiSingleton() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi spada");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testPrendiAttrezzoTroppoPesante() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi piombo");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testAttrezzoTroppoPesanteRimaneInStanza() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi piombo");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, bilocale);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("piombo"));
	}
}
