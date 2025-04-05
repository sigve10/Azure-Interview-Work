package no.ntnu.sigve.dogapi.repository;

import org.springframework.stereotype.Repository;
import no.ntnu.sigve.dogapi.entity.User;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {}