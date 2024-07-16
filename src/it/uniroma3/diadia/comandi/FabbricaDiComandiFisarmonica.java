package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	@Override
	public AbstractComando costruisci(String nomeComando) {

		String parametro = null;
		AbstractComando comando = null;
		
		Scanner scannerDiParole = new Scanner(nomeComando);
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();

		if (nomeComando == null) 
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(parametro);
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(parametro);
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(parametro);
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else comando = new ComandoNonValido();

		scannerDiParole.close();
		return comando;
	}

}
