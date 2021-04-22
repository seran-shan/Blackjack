package blackjack.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSupport implements IFileReading {
	
	private final static String FILE_NAME = "BlackJackUsers.txt";
	protected static final String USER_INFO_PATH = System.getProperty("user.home") + "/Downloads/" + FILE_NAME;
	
	public void writeToFile(String lineToWrite) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(USER_INFO_PATH, true));
			writer.write(lineToWrite);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkIfUserExist(String username, String password) throws FileNotFoundException {
		File file = new File(USER_INFO_PATH);
		checkExistingFile(file);
		
		Scanner scanner = new Scanner(file);
		String[] lineInfo = null;
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineInfo = line.split(",");
			String checkUsername = lineInfo[2];
			String checkPassword = lineInfo[3];

			if (checkUsername.equalsIgnoreCase(username) && checkPassword.equals(password)) {
				scanner.close();
				return true;
			}
		}
		scanner.close();
	
		return false;
	}
	
	public String getUserInfo(String username) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(USER_INFO_PATH));
		String[] lineInfo = null;
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineInfo = line.split(",");
			String checkUsername = lineInfo[2];

			if (checkUsername.equalsIgnoreCase(username)) {
				scanner.close();
				return line;
			}
		}
		scanner.close();
		return null;
	}
	
	public void saveNewBalance(String playerInfo, String username) {
		try {
			Scanner scanner = new Scanner(new File(USER_INFO_PATH));
			StringBuffer buffer = new StringBuffer();
 
			while (scanner.hasNextLine()) {
				buffer.append(scanner.nextLine() + System.lineSeparator());
			}	
     
			String fileContents = buffer.toString();
			scanner.close();
	      
			String oldLine = getUserInfo(username);
			String newLine = playerInfo;
			fileContents = fileContents.replaceAll(oldLine, newLine);
	      
			FileWriter writer = new FileWriter(USER_INFO_PATH);
			writer.append(fileContents);
			writer.flush();
			writer.close();
		      
	  	} catch (IOException io) {
		  io.printStackTrace();
	  	}
	}

	public void checkExistingFile(File file) throws FileNotFoundException {
		String fileName = file.getName();  
	
		if (!(file.exists())) {
			throw new FileNotFoundException("Filen \"" + fileName + "\" ekisterer ikke.");
		}
	
		if (!(file.isFile())) {
			throw new FileNotFoundException("Filen \"" + fileName + "\" er unormal.");
		}
	
		if (!(file.canRead())) {
			throw new FileNotFoundException("Filen \"" + fileName + "\" er ikke leslig.");
		}
	}
}
