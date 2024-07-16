package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";      

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze Buie*/
	private static final String STANZE_BUIE_MARKER = "Stanze Buie:";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze Bloccate*/
	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze Magiche*/
	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String MAGHI_MARKER = "Maghi:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String STREGHE_MARKER = "Streghe:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String CANI_MARKER = "Cani:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";


	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException   {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader r) {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(r);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			try (Scanner scannerLinea = new Scanner(nomeStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += scannerLinea.next();
				}
			}
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanzaBuia = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String stringa : separaStringheAlleVirgole(specificheStanzaBuia)) {
			String nomeStanza = null; 
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo "+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += nomeAttrezzo;
					nomeAttrezzo = scannerLinea.next();
				}
			}
			Stanza stanza = new StanzaBuia(nomeStanza, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanzaBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String stringa : separaStringheAlleVirgole(specificheStanzaBloccate)) {
			String nomeStanza = null;
			String nomeAttrezzo = null;
			String direzione = null;
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo "+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione "+direzione+"."));
				direzione = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += nomeAttrezzo;
					nomeAttrezzo = direzione;
					direzione = scannerLinea.next();
				}
			}
			
			Stanza stanza = new StanzaBloccata(nomeStanza, nomeAttrezzo, Direzione.valueOf(direzione));
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanzaBloccate = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String stringa : separaStringheAlleVirgole(specificheStanzaBloccate)) {
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += scannerLinea.next();
				}
			}
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next().substring(1));
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).substring(1);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).substring(1);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException  {
		String specificheStanzaBloccate = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		for(String stringa : separaStringheAlleVirgole(specificheStanzaBloccate)) {
			String nome = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo "+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("nella stanza "+nomeStanza+"."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += scannerLinea.next();
				}
			}
			Attrezzo attrezzoMago = new Attrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo));
			AbstractPersonaggio mago = new Mago(nome, attrezzoMago);
			
			if(nome2stanza.containsKey(nomeStanza))
				this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
		}
	}
	
	private void leggiECreaStreghe() throws FormatoFileNonValidoException  {
		String specificheStanzaBloccate = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		for(String stringa : separaStringheAlleVirgole(specificheStanzaBloccate)) {
			String nome = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("nella stanza "+nomeStanza+"."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += scannerLinea.next();
				}
			}
			AbstractPersonaggio strega = new Strega(nome);
			
			if(nome2stanza.containsKey(nomeStanza))
				this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
		}
	}
	
	private void leggiECreaCani() throws FormatoFileNonValidoException  {
		String specificheStanzaBloccate = this.leggiRigaCheCominciaPer(CANI_MARKER);
		for(String stringa : separaStringheAlleVirgole(specificheStanzaBloccate)) {
			String nome = null;
			String nomeAttrezzoDesiderato = null;
			String nomeAttrezzoDaDare = null;
			String pesoAttrezzoDaDare = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo "+nomeAttrezzoDesiderato+"."));
				nomeAttrezzoDesiderato = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo "+nomeAttrezzoDaDare+"."));
				nomeAttrezzoDaDare = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+pesoAttrezzoDaDare+"."));
				pesoAttrezzoDaDare = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("nella stanza "+nomeStanza+"."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += scannerLinea.next();
				}
			}
			Attrezzo attrezzoCane = new Attrezzo(nomeAttrezzoDaDare, Integer.parseInt(pesoAttrezzoDaDare));
			AbstractPersonaggio cane = new Cane(nome, nomeAttrezzoDesiderato, attrezzoCane);
			
			if(nome2stanza.containsKey(nomeStanza))
				this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
		}
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					nomeStanza += " ";
					nomeStanza += scannerLinea.next();
				}
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(USCITE_MARKER);

		for(String stringa : separaStringheAlleVirgole(specificheAttrezzi)) {
			String stanzaPartenza = null;
			String dir = null;
			String stanzaDestinazione = null; 
			try (Scanner scannerLinea = new Scanner(stringa)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					String maybeNext = scannerLinea.next();
					if(!(maybeNext.equals("nord") || maybeNext.equals("sud") || maybeNext.equals("est") || maybeNext.equals("ovest"))) {
						stanzaPartenza += " ";
						stanzaPartenza += maybeNext;
						check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
						dir = scannerLinea.next();
					}else {
						check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
						dir = maybeNext;
					}
				}
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				stanzaDestinazione = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					stanzaDestinazione += " ";
					stanzaDestinazione += scannerLinea.next();
				}
				
			}
			impostaUscita(stanzaPartenza, Direzione.valueOf(dir), stanzaDestinazione);	
		}
	 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaDoppiaAdiacenza(arrivoA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}