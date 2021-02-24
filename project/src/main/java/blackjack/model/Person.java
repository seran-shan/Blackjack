package blackjack.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Person {
	
	private String name;
	private String email;
	private String username;
	private Date birthday;
	private char gender;
	private String password;
	
	
	public Person(String name, String email, String username, String password, Date birthday, char gender) {
		setName(name);
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setBirthday(birthday);
		setGender(gender);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (!validateName(name)) {
			throw new IllegalArgumentException("feil");
		} else {
			this.name = name;
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (!validateUsername(username)) {
			throw new IllegalArgumentException("Brukernavnet kan kun bestå av bokstaver og tall, og må være minst tre tegn");
		}
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (!validatePassword(password)) {
			throw new IllegalArgumentException("Passordet er nødt til å bestå av: 1 stor bokstav - 1 liten bokstav - et tall - minst 3 tegn - ingen mellomrom ");
		}
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if (!validateMail(email)) {
			throw new IllegalArgumentException("Emailen er ikke gyldig");
		}
		this.email = email;
	}
	
	public Date getBirthday() {
		return birthday;
	} 
	
	public void setBirthday(Date birthday) {
		if (!validateBirthday(birthday)) {
			throw new IllegalArgumentException("18-års grense!");
		} else {
			this.birthday = birthday;
		}
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		if (!validateGender(gender)) {
			throw new IllegalArgumentException("Kjønnet må beskrives av kun et tegn, hendholdsvis; M, F, eller 0");
		} else {
			this.gender = gender;
		}
	}
	
	private boolean validateName(String name) {
		return name.matches("^([a-åA-Å]{2,})\s([a-åA-Å]{2,})$");
	}
	
	private boolean validateMail(String emailToValidate) {
		String[] nameSplitted = getName().split(" ");
		String firstName = nameSplitted[0].toLowerCase();
		String lastName = nameSplitted[1].toLowerCase();
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
	
	private boolean validateBirthday(Date birthday1) {
		Long eighteenYears = 18L * 365L * 24L * 60L * 60L * 1000L;
		Date d1 = new Date(eighteenYears);
		if (birthday1.before(d1)) {
			return true;
		} 
		return false;
	}
	
	
	private boolean validateGender(char gender) {
		String genderString = String.valueOf(gender);
		if (genderString.matches("[MF\0]{1}")) {
			return true;
		}
		return false;
	}
	
	private boolean validateUsername(String username) {
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
	
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + ", birthday=" + birthday + ", gender=" + gender + "]";
	}
}