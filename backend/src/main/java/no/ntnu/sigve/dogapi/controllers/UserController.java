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
import org.springframework.web.server.ResponseStatusException;
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

	@GetMapping("/{mail}")
	public ResponseEntity<Set<String>> getUser(@PathVariable String mail) {
		Optional<User> user = userRepository.findById(mail);

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user.get().getFavorites(), HttpStatus.OK);
	}

	private void createUser(String mail) {
		userRepository.save(new User(mail));
	}

	@PostMapping("/{mail}")
	public ResponseEntity<String> putFavorite(@PathVariable String mail, @RequestBody String url) {
		Optional<User> userFound = userRepository.findById(mail);

		if (!userFound.isPresent()) {
			createUser(mail);
			userFound = userRepository.findById(mail);

			if (!userFound.isPresent()) {
				throw new IllegalStateException("Could not create user");
			}
		}

		User user = userFound.get();
		user.addFavorite(url);

		userRepository.save(user);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{mail}")
	public ResponseEntity<String> deleteFavorite(@PathVariable String mail, @RequestBody String url) {
		Optional<User> userFound = userRepository.findById(mail);

		if (!userFound.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		User user = userFound.get();
		user.removeFavorite(url);

		userRepository.save(user);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}