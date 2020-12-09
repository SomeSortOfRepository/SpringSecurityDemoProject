package ru.dolinini.springsecuritydemoproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dolinini.springsecuritydemoproject.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByFirstname(String firstname);
    Optional<User> findByLastname(String lastname);
    Optional<User> findByEmail(String email);
}
