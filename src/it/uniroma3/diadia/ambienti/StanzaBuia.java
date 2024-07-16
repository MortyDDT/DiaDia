package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoNecessario;
	private String messaggioBase = "Qui c'è buio pesto!";
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.attrezzoNecessario = nomeAttrezzo;
	}

	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoNecessario))
			return super.getDescrizione();
		return messaggioBase;
	}

	
}
