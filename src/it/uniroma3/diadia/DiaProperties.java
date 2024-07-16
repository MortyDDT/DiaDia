package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DiaProperties {

	public static String getProperty(String nome) {
		Properties p = new Properties();
		try {
			InputStream is = new FileInputStream("diadia.properties");
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(nome);		
	}


}
