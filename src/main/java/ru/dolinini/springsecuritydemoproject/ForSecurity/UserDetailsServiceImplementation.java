package ru.dolinini.springsecuritydemoproject.ForSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dolinini.springsecuritydemoproject.model.User;
import ru.dolinini.springsecuritydemoproject.repos.UserRepository;

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {

        User userFromRepository=userRepository.findByFirstname(firstName).orElseThrow(
                ()->new UsernameNotFoundException("User with such first name doesn't exist"));

        return SecureUser.userDetailsFromUser(userFromRepository);
    }
}
