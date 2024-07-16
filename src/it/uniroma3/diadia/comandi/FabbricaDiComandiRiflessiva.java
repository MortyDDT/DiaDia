package it.uniroma3.diadia.comandi;

import java.util.Scanner;


public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {


	@Override
	public AbstractComando costruisci(String istruzione) {
		if(istruzione == null)
			throw new IllegalArgumentException();

		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();

		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (AbstractComando)Class.forName(nomeClasse).newInstance();
			comando.setParametro(parametro);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			comando = new ComandoNonValido(e);
		}

		return comando;
	} 

}
