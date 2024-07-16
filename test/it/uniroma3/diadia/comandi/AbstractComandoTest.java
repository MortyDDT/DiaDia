package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractComandoTest {

	private AbstractComando comando;
	
	@Before
	public void creaComando(){
		
	}

	@Test
	public void testComandoAiuto() {
		comando = new ComandoAiuto();
		assertEquals(ComandoAiuto.class, comando.getClass());
	}
	
	@Test
	public void testComandoFine() {
		comando = new ComandoFine();
		assertEquals(ComandoFine.class, comando.getClass());
	}
	
	@Test
	public void testComandoGuarda() {
		comando = new ComandoGuarda();
		assertEquals(ComandoGuarda.class, comando.getClass());
	}
	
	@Test
	public void testComandoNonValido() {
		comando = new ComandoNonValido();
		assertEquals(ComandoNonValido.class, comando.getClass());
	}

	@Test
	public void testComandoPosa() {
		comando = new ComandoPosa();
		assertEquals(ComandoPosa.class, comando.getClass());
	}
	
	@Test
	public void testComandoPrendi() {
		comando = new ComandoPrendi();
		assertEquals(ComandoPrendi.class, comando.getClass());
	}
	
	@Test
	public void testComandoVai() {
		comando = new ComandoVai();
		assertEquals(ComandoVai.class, comando.getClass());
	}
}
