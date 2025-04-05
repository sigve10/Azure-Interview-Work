package no.ntnu.sigve.dogapi.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import no.ntnu.sigve.dogapi.entity.User;
import no.ntnu.sigve.dogapi.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class UserController {
	private UserRepository userRepository;

	protected UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private User getOrCreateUser(String mail) {
		Optional<User> user = userRepository.findById(mail);

		if (!user.isPresent()) {
			userRepository.save(new User(mail));
			user = userRepository.findById(mail);

			if (!user.isPresent()) {
				throw new IllegalStateException("Failed to create user");
			}
		}

		return user.get();
	}

	@GetMapping("/{mail}")
	public ResponseEntity<Set<String>> getUser(@PathVariable String mail) {
		User user = getOrCreateUser(mail);

		return new ResponseEntity<>(user.getFavorites(), HttpStatus.OK);
	}

	@PostMapping("/{mail}")
	public ResponseEntity<String> putFavorite(@PathVariable String mail, @RequestBody String url) {
		User user = getOrCreateUser(mail);

		user.addFavorite(url);
		userRepository.save(user);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{mail}")
	public ResponseEntity<String> deleteFavorite(@PathVariable String mail, @RequestBody String url) {
		User user = getOrCreateUser(mail);

		user.removeFavorite(url);
		userRepository.save(user);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}