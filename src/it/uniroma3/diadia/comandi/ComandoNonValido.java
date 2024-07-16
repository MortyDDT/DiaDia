package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	private ReflectiveOperationException exception;
	
	public ComandoNonValido() {}
	
	public ComandoNonValido(ReflectiveOperationException e) {
		this.exception = e;
	}
	
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIOConsole();
		io.mostraMessaggio("Comando inesistente!");
	}
	
	public ReflectiveOperationException getException() {
		return this.exception;
	}

}
