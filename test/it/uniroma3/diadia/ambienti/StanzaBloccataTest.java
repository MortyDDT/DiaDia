package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private static final String ATTREZZO_NECESSARIO = "chiave";
	private static final String ATTREZZO_SBAGLIATO = "attrezzoTest";
	private static final Direzione DIREZIONE_BLOCCATA = Direzione.sud;

	private Stanza stanzaIniziale;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzo;
	
	@Before
	public void creaStanzePerITest() {
		stanzaIniziale = new StanzaBloccata("StanzaBloccata", ATTREZZO_NECESSARIO, DIREZIONE_BLOCCATA);
		stanzaAdiacente = new Stanza("stanzaAdiacente");
		
		stanzaIniziale.impostaStanzaAdiacente(stanzaAdiacente, DIREZIONE_BLOCCATA);
		stanzaAdiacente.impostaStanzaAdiacente(stanzaIniziale, DIREZIONE_BLOCCATA.opposta());

	}

	@Test
	public void testGetStanzaAdiacenteRitornaStanzaAdiacente() {
		attrezzo = new Attrezzo(ATTREZZO_NECESSARIO, 1);
		stanzaIniziale.addAttrezzo(attrezzo);
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE_BLOCCATA), stanzaAdiacente);
	}
	
	@Test
	public void testGetStanzaAdiacenteRitornaStanzaCorrente() {
		attrezzo = new Attrezzo(ATTREZZO_SBAGLIATO, 1);
		stanzaIniziale.addAttrezzo(attrezzo);
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE_BLOCCATA), stanzaIniziale);
	}
	
	@Test
	public void testGetStanzaAdiacenteStanzaVuota() {
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE_BLOCCATA), stanzaIniziale);	
	}

}
