package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class ComandoFineTest {
		
	@Test
	public void testComandoFine() {
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("fine");
		IOSimulator io = Fixture.creaSimulazione(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}

}
