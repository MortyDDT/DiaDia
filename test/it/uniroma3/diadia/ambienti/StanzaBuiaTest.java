package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private static final String MESSAGGIO_BASE = "Qui c'è buio pesto!";
	private static final String ATTREZZO_NECESSARIO = "Lanterna";
	private static final String ATTREZZO_SBAGLIATO = "attrezzoTest";
	
	private Stanza stanzaBuia;
	private Attrezzo attrezzo;
	
	@Before
	public void creaStanzePerITest() {
		stanzaBuia = new StanzaBuia("StanzaBuia", ATTREZZO_NECESSARIO);
	}

	@Test
	public void testGetDescrizioneConAttrezzoNecessario() {
		attrezzo = new Attrezzo(ATTREZZO_NECESSARIO, 1);
		stanzaBuia.addAttrezzo(attrezzo);
		assertNotEquals(stanzaBuia.getDescrizione(), MESSAGGIO_BASE);
	}
	
	@Test
	public void testGetDescrizioneConAttrezzoSbagliato() {
		attrezzo = new Attrezzo(ATTREZZO_SBAGLIATO, 1);
		stanzaBuia.addAttrezzo(attrezzo);
		assertEquals(stanzaBuia.getDescrizione(), MESSAGGIO_BASE);
	}
	
	@Test
	public void testGetDescrizioneStanzaVuota() {
		assertEquals(stanzaBuia.getDescrizione(), MESSAGGIO_BASE);
	}

}
