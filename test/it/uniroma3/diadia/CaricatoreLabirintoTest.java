package it.uniroma3.diadia;

import static org.junit.Assert.*;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirintoTest {

	private StringReader reader1;
	private CaricatoreLabirinto caricatore1;
	private StringReader reader2;
	private CaricatoreLabirinto caricatore2;
	private StringReader reader3;
	private CaricatoreLabirinto caricatore3;

	private static final String MONOLOCALE = 
			  "Stanze: aula N10\r\n"
			+ "Stanze Magiche:\r\n"
			+ "Stanze Buie:\r\n"
			+ "Stanze Bloccate:\r\n"
			+ "Inizio: aula N10\r\n"
			+ "Vincente: aula N10\r\n"
			+ "Attrezzi: martello 10 aula N10\r\n"
			+ "Maghi:\r\n"
			+ "Streghe:\r\n"
			+ "Cani:\r\n"
			+ "Uscite:";
	
	private static final String TRILOCALE = 
			  "Stanze: biblioteca, aula N10, N11\r\n"
			+ "Stanze Magiche:\r\n"
			+ "Stanze Buie:\r\n"
			+ "Stanze Bloccate:\r\n"
			+ "Inizio: aula N10\r\n"
			+ "Vincente: N11\r\n"
			+ "Attrezzi: martello 10 biblioteca, pinza 2 aula N10\r\n"
			+ "Maghi: Pierre attrezzoMagico 1 biblioteca\r\n"
			+ "Streghe: Luna N11\r\n"
			+ "Cani: Bob osso spada 1 aula N10\r\n"
			+ "Uscite: biblioteca nord aula N10, biblioteca sud N11";
	
	private static final String TRILOCALE_STANZE_SPECIALI = 
			  "Stanze:\r\n"
			+ "Stanze Magiche: N10\r\n"
			+ "Stanze Buie: N11 lanterna\r\n"
			+ "Stanze Bloccate: biblioteca chiave sud\r\n"
			+ "Inizio: N10\r\n"
			+ "Vincente: N11\r\n"
			+ "Attrezzi: martello 10 biblioteca, pinza 2 N10\r\n"
			+ "Maghi:\r\n"
			+ "Streghe:\r\n"
			+ "Cani:\r\n"
			+ "Uscite: biblioteca nord N11, N10 sud N11";
	
	
	@Before
	public void setUp() {
		
		reader1 = new StringReader(MONOLOCALE);
		caricatore1 = new CaricatoreLabirinto(reader1);
		reader2 = new StringReader(TRILOCALE);
		caricatore2 = new CaricatoreLabirinto(reader2);
		reader3 = new StringReader(TRILOCALE_STANZE_SPECIALI);
		caricatore3 = new CaricatoreLabirinto(reader3);
	}

	@Test
	public void testVerificaMonolocaleStanzaIniziale() throws FormatoFileNonValidoException{
		caricatore1.carica();
		assertEquals("aula N10", caricatore1.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testVerificaMonolocaleStanzaVincente() throws FormatoFileNonValidoException{
		caricatore1.carica();
		assertEquals("aula N10", caricatore1.getStanzaVincente().getNome());
	}
	
	@Test
	public void testVerificaMonolocaleAdiacenze() throws FormatoFileNonValidoException{
		caricatore1.carica();
		assertEquals(0, caricatore1.getStanzaIniziale().getNumeroStanzeAdiacenti());
	}
	
	@Test
	public void testVerificaMonolocaleAttrezzo() throws FormatoFileNonValidoException{
		caricatore1.carica();
		assertTrue(caricatore1.getStanzaIniziale().hasAttrezzo("martello"));
	}
	
	@Test
	public void testVerificaTrilocaleStanzaIniziale() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals("aula N10", caricatore2.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testVerificaTrilocaleStanzaVincente() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals("N11", caricatore2.getStanzaVincente().getNome());
	}
	
	@Test
	public void testVerificaTrilocaleStanzaVincenteTramiteAdiacenze() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals(caricatore2.getStanzaVincente(), caricatore2.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getStanzaAdiacente(Direzione.sud));
	}
	
	@Test
	public void testVerificaTrilocaleAdiacenze() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals(1, caricatore2.getStanzaIniziale().getNumeroStanzeAdiacenti());
	}
	
	@Test
	public void testVerificaTrilocaleAdiacenzaSud() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals("biblioteca", caricatore2.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getNome());
	}
	
	@Test
	public void testVerificaTrilocaleAttrezzo() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertTrue(caricatore2.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).hasAttrezzo("martello"));
	}
	
	@Test
	public void testVerificaTrilocaleVerificaCane() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals(Cane.class, caricatore2.getStanzaIniziale().getPersonaggio().getClass());
	}
	
	@Test
	public void testVerificaTrilocaleVerificaMago() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals(Mago.class, caricatore2.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getPersonaggio().getClass());
	}
	
	@Test
	public void testVerificaTrilocaleVerificaAtrezzoMago() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertNotNull(caricatore2.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getPersonaggio().getAttrezzo());
	}
	
	@Test
	public void testVerificaTrilocaleVerificaStrega() throws FormatoFileNonValidoException{
		caricatore2.carica();
		assertEquals(Strega.class, caricatore2.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getStanzaAdiacente(Direzione.sud).getPersonaggio().getClass());
	}
	
	@Test
	public void testVerificaTrilocaleSpecialeStanzaIniziale() throws FormatoFileNonValidoException{
		caricatore3.carica();
		assertEquals("N10" ,caricatore3.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testVerificaTrilocaleSpecialeClasseStanzaMagica() throws FormatoFileNonValidoException{
		caricatore3.carica();
		assertEquals(StanzaMagica.class ,caricatore3.getStanzaIniziale().getClass());
	}
	
	@Test
	public void testVerificaTrilocaleSpecialeClasseStanzaBuia() throws FormatoFileNonValidoException{
		caricatore3.carica();
		assertEquals(StanzaBuia.class ,caricatore3.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getClass());
	}
	
	@Test
	public void testVerificaTrilocaleSpecialeClasseStanzaBloccata() throws FormatoFileNonValidoException{
		caricatore3.carica();
		assertEquals(StanzaBloccata.class ,caricatore3.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getStanzaAdiacente(Direzione.sud).getClass());
	}
}
