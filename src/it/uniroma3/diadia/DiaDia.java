package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {
	
	public static final String MESSAGGIO_BENVENUTO = DiaProperties.getProperty("MESSAGGIO_BENVENUTO");
	public static final String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "saluta", "interagisci", "regala"};
	private Partita partita;
	private IO io;



	public DiaDia(IO io2) {
		this.partita = new Partita(io2);
		this.io = io2;
	}
	
	public DiaDia(IO io2, Labirinto labirinto) {
		this.partita = new Partita(io2, labirinto);
		this.io = io2;
	}


	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {		
			istruzione = this.io.leggiRiga();
			if(istruzione == null)
				return;
		}
		while (!processaIstruzione(istruzione));
	}

	
	public Partita getPartita() {
		return this.partita;
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione)  {
		AbstractComando comando;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comando = factory.costruisci(istruzione);
		comando.esegui(this.partita); 
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();

	}   


	public static void main(String[] argc)  {
		try (Scanner scannerDiLinee = new Scanner(System.in)) {
			IO io = new IOConsole(scannerDiLinee);
			DiaDia gioco = new DiaDia(io);
			gioco.gioca();
			scannerDiLinee.close();
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
	}
}