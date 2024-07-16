package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.Before;



public class StanzaTest {
	
	private static final String ATTREZZO = "AttrezzoTest";
	private static final String STANZA = "StanzaIniziale";
	private static final String STANZA_ADIACENTE = "StanzaAdiacenteNonVuota";
	private static final Direzione SUD = Direzione.sud;
	private static final Direzione NORD = Direzione.nord;
	private static final Direzione EST = Direzione.est;
	private static final Direzione OVEST = Direzione.ovest;
	protected Stanza stanza;
	
	
	private Stanza creaStanzaConAdiacenza(Stanza stanzaIniziale, String nomeStanzaAdiacente, Direzione direzione) {
		Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
		stanzaIniziale.impostaStanzaAdiacente(stanzaAdiacente, direzione);
		return stanzaAdiacente;
	}	
	
	
	@Before	
	public void creaLeStanzePerITest() {
		this.stanza = new Stanza(STANZA);
	}
	
		
	@Test
	public void testStanzaConUnAdiacenza() {
		Stanza adiacente = creaStanzaConAdiacenza(this.stanza, STANZA_ADIACENTE, SUD);
		assertEquals(adiacente, this.stanza.getStanzaAdiacente(SUD));
	}
	
	@Test
	public void testStanzaSenzaAdiacenza() {
		assertNull(this.stanza.getStanzaAdiacente(SUD));
	}
	
	@Test
	public void testCambiaStanzaAdiacente() {
		@SuppressWarnings("unused")
		Stanza adiacente = creaStanzaConAdiacenza(this.stanza, STANZA_ADIACENTE, SUD);
		Stanza nuova = creaStanzaConAdiacenza(this.stanza, "Nuova Adiacente", SUD);
		assertEquals(nuova, this.stanza.getStanzaAdiacente(SUD));

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testImpostaPiuDiMassimoAdiacenze() {
		Stanza adiacente = new Stanza(STANZA_ADIACENTE);
		Direzione[] direzioni = {NORD, SUD, OVEST, EST};
		for(Direzione direzione: direzioni)
			this.stanza.impostaStanzaAdiacente(adiacente, direzione);
		Direzione direzioneNuova = Direzione.valueOf("sud-ovest");
		creaStanzaConAdiacenza(this.stanza, "Da non inserire", direzioneNuova);
		assertFalse(this.stanza.getAdiacenti().containsKey(direzioneNuova));
	}
	
	@Test
	public void testVerificaGetAttrezzo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		assertNotNull(this.stanza.getAttrezzo(ATTREZZO));
	}

	@Test
	public void testVerificaHasAttrezzo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testStanzaVuotaHasAttrezzo() {
		assertFalse(this.stanza.hasAttrezzo(ATTREZZO));
	}

	@Test
	public void testStanzaVuotaGetAttrezzo() {
		assertNull(this.stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testRimuoviAttrezzoStanza() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.removeAttrezzo(attrezzo);
		assertNull(this.stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testAddAttrezzoStanza() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		assertNotNull(this.stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testAddAttrezziOltreMassimo() {
		for(int i = 0; i < Stanza.NUMERO_MASSIMO_ATTREZZI; i++) {
			Attrezzo attrezzo = new Attrezzo(ATTREZZO + i, 1);
			assertTrue(this.stanza.addAttrezzo(attrezzo));
		}
		Attrezzo attrezzoDiTroppo = new Attrezzo(ATTREZZO + Stanza.NUMERO_MASSIMO_ATTREZZI, 1);
		assertFalse(this.stanza.addAttrezzo(attrezzoDiTroppo));
	}
	
	
}
