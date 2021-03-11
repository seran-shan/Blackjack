package blackjack.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class UserValidation {

	public boolean validateFirstName(String firstName) {
		return firstName.matches("^([a-åA-Å]{2,})$");
	}
	
	public boolean validateLastName(String lastName) {
		return lastName.matches("^([a-åA-Å]{2,})$");
	}
	
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
		
		if (emailToValidate.matches("^([a-åA-Å0-9]{2,})@([a-åA-Å0-9]{1,})\\.([a-åA-Å]{2,})$")) {
			String[] mailSplitted = emailToValidate.split("[\\.@]");
			//String aliasMail = mailSplitted[0];
			//String domain = mailSplitted[1];
			String countryCode = mailSplitted[2];
			
			if (list.contains(countryCode)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validateBirthday(LocalDate birthday) {
		LocalDate eighteenYears = LocalDate.now();
		eighteenYears.minusYears(18);
		
		if (birthday.isBefore(eighteenYears)) {
			return true;
		}
		return false;
	}
	
	public boolean validateGender(String gender) {
		List<String> genderString = Arrays.asList("Mann", "Dame", "Udefinert");
		if (genderString.contains(gender)) {
			return true;
		}
		return false;
	}
	
	public boolean validateUsername(String username) {
		if (username.matches("^([a-zA-Z0-9]{3,})$")) {
			return true;
		}
		return false;
	}
	
	public boolean validatePassword(String password) {
		if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,}$")) {
			return true;
		}
		return false;
	}
	
	public boolean validateBalance(String balance) {;
		return balance.matches("[0-9]{1,}");
	}
	
}
