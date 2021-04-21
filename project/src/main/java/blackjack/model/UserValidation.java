package blackjack.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Klasse som inneholder sjekk av forksjellige variabler i programmet
 * @author seranshanmugathas og pravinthevakan
 *
 */
public class UserValidation {

	/**
	 * Sjekker om fornavnet er gyldig
	 * @param firstName
	 * @return fornavn dersom det er gyldig 
	 */
	public boolean validateFirstName(String firstName) {
		return firstName.matches("^([a-åA-Å]{2,})$");
	}
	
	/**
	 * sjekker om etternavnet er gyldig
	 * @param lastName
	 * @return etternavn dersom det er gyldig
	 */
	public boolean validateLastName(String lastName) {
		return lastName.matches("^([a-åA-Å]{2,})$");
	}
	
	/**
	 * sjekker om E mailen er gyldig
	 * må inneholde riktig landskode
	 * må inneholde riktige bokstaver og tegn
	 * @param emailToValidate
	 * @return true hvis den er gyldig, false hvis den ikke er gyldig
	 */
	public boolean validateMail(String emailToValidate) {
		String[] countryCodes = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", 
								 "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", 
								 "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", 
								 "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn",
								 "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", 
								 "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", 
								 "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl",
								 "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm",
								 "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir",
								 "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn",
								 "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls",
								 "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", 
								 "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", 
								 "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", 
								 "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", 
								 "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", 
								 "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", 
								 "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", 
								 "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", 
								 "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", 
								 "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw","com","org","net"};
		
		List<String> list = Arrays.asList(countryCodes);
		
		if (emailToValidate.matches("[A-Å0-9a-å._]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")) {
			String[] mailSplitted = emailToValidate.split("[\\.@]");
			//String aliasMail = mailSplitted[0];
			//String domain = mailSplitted[1];
			String countryCode = mailSplitted[mailSplitted.length - 1];
			
			if (list.contains(countryCode)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * sjekker om fødselsdatoen er gylig
	 * må være minst 18 år
	 * @param birthday
	 * @return true dersom den er gyldig, false hvis ikke
	 */
	public boolean validateBirthday(LocalDate birthday) {
		LocalDate eighteenYears = LocalDate.now();
		eighteenYears.minusYears(18);
		
		if (birthday.isBefore(eighteenYears)) {
			return true;
		}
		return false;
	}
	
	/**
	 * sjekker om vi har gyldig kjønn
	 * kan ikke være ubesvart
	 * @param gender
	 * @return true hvis den er gyldig, false hvis ikke
	 */
	public boolean validateGender(String gender) {
		List<String> genderString = Arrays.asList("Mann", "Dame", "Udefinert");
		if (genderString.contains(gender)) {
			return true;
		}
		return false;
	}
	
	/**
	 * sjekker om brukernavnet er gyldig 
	 * må bestå av rette tegn bokstaver og tall
	 * @param username
	 * @return true hvis den er gyldig, false hvis ikke 
	 */
	public boolean validateUsername(String username) {
		if (username.matches("^([a-zA-Z0-9]{3,})$")) {
			return true;
		}
		return false;
	}
	
	/**
	 * sjekker om passordet er gyldig
	 * må bestå av rette tegm, bokstaver og tall
	 * @param password
	 * @return true hvis den er gyldig, false hvis ikke
	 */
	public boolean validatePassword(String password) {
		if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,}$")) {
			return true;
		}
		return false;
	}
	
	/**
	 * sjekke rom saldoen er gyldig
	 * @param balance
	 * @return den gyldige saldoen
	 */
	public boolean validateBalance(String balance) {;
		return balance.matches("[0-9.]{1,}");
	}
}
