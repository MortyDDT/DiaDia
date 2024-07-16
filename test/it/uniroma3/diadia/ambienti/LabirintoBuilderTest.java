package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.fixture.Fixture;

public class LabirintoBuilderTest {

	private Partita partita;
	private IO io;
	private Labirinto monolocale;
	private Labirinto bilocale;
	private Labirinto bilocale2;
	private Labirinto trilocale;
	private Labirinto labirintoStanzeSpeciali;


	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}


	@Before
	public void inizializza(){
		this.io = new IOConsole();
		this.partita = new Partita(io);

		monolocale = ((LabirintoBuilder) Labirinto.newBuilder("labirintoBuilder"))
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();

		bilocale = ((LabirintoBuilder) Labirinto.newBuilder("labirintoBuilder"))
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto" ,10)
				.addAdiacenza("salotto", "camera", Direzione.nord)
				.getLabirinto();

		bilocale2 = ((LabirintoBuilder) Labirinto.newBuilder("labirintoBuilder"))
				.addStanzaIniziale("salotto")
				.addAttrezzo("letto" ,10) 
				.addStanzaVincente("camera")					
				.addAdiacenza("salotto", "camera", Direzione.nord) 
				.getLabirinto();

		trilocale = ((LabirintoBuilder) Labirinto.newBuilder("labirintoBuilder"))
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola", 1)
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.nord)
				.addAdiacenza("cucina", "camera", Direzione.est)
				.getLabirinto();

		labirintoStanzeSpeciali = ((LabirintoBuilder) Labirinto.newBuilder("labirintoBuilder"))
				.addStanzaIniziale("stanzaIniziale")
				.addAttrezzo("attrezzoNecessario", 1)
				.addAttrezzo("attrezzoNecessario", 1)
				.addStanzaMagica("stanzaMagica")
				.addStanzaBuia("stanzaBuia", "attrezzoNecessario")
				.addStanzaBloccata("stanzaBloccata", "attrezzoNecessario", Direzione.est)
				.addStanzaVincente("stanzaVincente")
				.addAdiacenza("stanzaIniziale", "stanzaMagica", Direzione.nord)
				.addAdiacenza("stanzaMagica", "stanzaBuia", Direzione.ovest)
				.addAdiacenza("stanzaMagica", "stanzaBloccata", Direzione.est)
				.addAdiacenza("stanzaBloccata", "stanzaVincente", Direzione.est);

	}

	@Test
	public void testLabirintoMonolocaleStanzaIniziale() {
		partita.setLabirinto(monolocale);
		assertEquals(monolocale.getStanzaIniziale(), partita.getStanzaCorrente());
	}


	@Test
	public void testLabirintoMonolocaleStanzaVincente() {
		partita.setLabirinto(monolocale);
		assertEquals(monolocale.getStanzaVincente(), partita.getStanzaVincente());
	}


	@Test
	public void testLabirintoBilocaleStanzaVincente() {
		partita.setLabirinto(bilocale);
		assertEquals(bilocale.getStanzaVincente(), partita.getStanzaVincente());
	}


	@Test
	public void testLabirintoBilocaleAttrezzoStanzaIniziale() {
		partita.setLabirinto(bilocale2);
		assertTrue(bilocale2.getStanzaIniziale().hasAttrezzo("letto"));
	}


	@Test
	public void testLabirintoBilocaleAttrezzoInStanzaVincente() {
		partita.setLabirinto(bilocale);
		assertTrue(bilocale.getStanzaVincente().hasAttrezzo("letto"));
	}


	@Test
	public void testAdiacenzaLabirintoBilocale() {
		partita.setLabirinto(bilocale);
		assertEquals(bilocale.getStanzaVincente(), bilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord));
	}


	@Test
	public void testAdiacenzeLabirintoTrilocale() {
		partita.setLabirinto(trilocale);
		assertEquals(trilocale.getStanzaIniziale(), partita.getStanzaVincente().getStanzaAdiacente(Direzione.ovest).getStanzaAdiacente(Direzione.sud));
	}


	@Test
	public void testStanzaMagica() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi attrezzoNecessario");
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("posa attrezzoNecessario");
		comandiDaEseguire.add("prendi attrezzoNecessario");
		comandiDaEseguire.add("posa attrezzoNecessario");
		comandiDaEseguire.add("prendi attrezzoNecessario");
		comandiDaEseguire.add("posa attrezzoNecessario");
		comandiDaEseguire.add("prendi oirasseceNozzertta");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, labirintoStanzeSpeciali);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("oirasseceNozzertta"));
	}

	@Test
	public void testStanzaBuiaSenzaAttrezzoNecessario() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("vai ovest");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, labirintoStanzeSpeciali);
		assertEquals("Qui c'è buio pesto!", this.partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	public void testStanzaBuiaConAttrezzoNecessario() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi attrezzoNecessario");
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("vai ovest");
		comandiDaEseguire.add("posa attrezzoNecessario");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, labirintoStanzeSpeciali);
		assertNotEquals("Qui c'è buio pesto!", this.partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	public void testStanzaBloccataSenzaAttrezzoNecessario() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("vai est");
		comandiDaEseguire.add("vai est");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, labirintoStanzeSpeciali);
		assertEquals("stanzaBloccata", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testStanzaBloccataConAttrezzoNecessario() {
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("prendi attrezzoNecessario");
		comandiDaEseguire.add("vai nord");
		comandiDaEseguire.add("vai est");
		comandiDaEseguire.add("posa attrezzoNecessario");
		comandiDaEseguire.add("vai est");
		comandiDaEseguire.add("fine");
		partita = Fixture.creaSimulazione(comandiDaEseguire, labirintoStanzeSpeciali);
		assertEquals(this.partita.getStanzaVincente(), this.partita.getStanzaCorrente());
	}
}
