package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva fabbrica;
	
	@Before
	public void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testCostruisciComandoVai() {
		assertSame(ComandoVai.class, this.fabbrica.costruisci("vai sud").getClass());
	}
	
	@Test
	public void testCostruisciComandoPrendi() {
		assertSame(ComandoPrendi.class, this.fabbrica.costruisci("prendi attrezzo").getClass());
	}
	
	@Test
	public void testCostruisciComandoPosa() {
		assertSame(ComandoPosa.class, this.fabbrica.costruisci("posa attrezzo").getClass());
	}
	
	@Test
	public void testCostruisciComandoAiuto() {
		assertSame(ComandoAiuto.class, this.fabbrica.costruisci("aiuto").getClass());
	}
	
	@Test
	public void testCostruisciComandoRegala() {
		assertSame(ComandoRegala.class, this.fabbrica.costruisci("regala attrezzo").getClass());
	}
	
	@Test
	public void testCostruisciComandoSaluta() {
		assertSame(ComandoSaluta.class, this.fabbrica.costruisci("saluta").getClass());
	}
	
	@Test
	public void testCostruisciComandoInteragisci() {
		assertSame(ComandoInteragisci.class, this.fabbrica.costruisci("interagisci").getClass());
	}

	@Test
	public void testCostruisciComandoInesistente() {
		assertSame(ComandoNonValido.class, this.fabbrica.costruisci("comando sconosciuto").getClass());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCostruisciComandoNull() {
		this.fabbrica.costruisci(null);
	}
	
	@Test
	public void testCostruisciComandoClassNotFoundException() {
		assertSame(ComandoNonValido.class, this.fabbrica.costruisci("inesistente").getClass());
		assertSame(ClassNotFoundException.class , ((ComandoNonValido)this.fabbrica.costruisci("inesistente")).getException().getClass());
	}

}
