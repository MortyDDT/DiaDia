package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	
	private Labirinto labirinto;
	
	@Before
	public void creaLabirintoPerTest() {
		this.labirinto = Labirinto.newBuilder("labirintoDaCaricatore");
	}
	
	
	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testGetStanzaVincente() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
}
