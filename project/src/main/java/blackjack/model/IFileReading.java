package blackjack.model;

import java.io.FileNotFoundException;

public interface IFileReading {
	
	/**
	 * Skriver informasjon til en bestemt fil.
	 * @param filename Filen det skal skrives til.
	 * @param lineTowrite det som skal skrives.
	 */
	void writeToFile(String lineToWrite);
	
	/**
	 * Sjekker om en bruker allerede er registrert.
	 * @param username
	 * @param password
	 * @param filename
	 * @return en boolean avhengig av om brukeren ekisterer.
	 * @throws FileNotFoundException om filen ikke blir funnet.
	 */
	boolean checkIfUserExist(String username, String password) throws FileNotFoundException;

	/**
	 * Henter informasjonen til en bruker
	 * @param username til brukeren som saldoen endres til
	 * @return brukerinformasjoen som en String
	 * @throws FileNotFoundException om filen ikke blir funnet.
	 */
	String getUserInfo(String username) throws FileNotFoundException;
	
	/**
	 * Endrer saldoen til bruker avhengig av vinn og tap
	 * @param playerInfo til brukeren er logget inn
	 */
	void saveNewBalance(String playerInfo, String username); 
	
}
