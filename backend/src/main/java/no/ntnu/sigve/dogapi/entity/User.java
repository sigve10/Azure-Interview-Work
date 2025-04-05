package no.ntnu.sigve.dogapi.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class User {
	static final Pattern MAIL_PATTERN = Pattern.compile("[\\S]+@[\\S]+\\.[\\S]{2,3}");

	@Id
	protected String mail;

	@ElementCollection
	protected Set<String> favorites = new HashSet<>();

	public User() {}

	public User(String mail) {
		this.setMail(mail);
		this.favorites = new HashSet<>();
	}

	private void setMail(String mail) {
		Matcher matcher = MAIL_PATTERN.matcher(mail);

		if (mail == null || mail.isBlank()) {
			throw new IllegalArgumentException("Mail cannot be null or blank");
		}


		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid mail provided");
		}

		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}

	public Set<String> getFavorites() {
		return this.favorites;
	}

	public void addFavorite(String url) {
		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("Image URL cannot be null");
		}
		this.favorites.add(url);
	}

	public void removeFavorite(String url) {
		this.favorites.remove(url);
	}

}
