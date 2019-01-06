package com.cranesteam.partylist.Services;

import com.cranesteam.partylist.Domain.Role;
import com.cranesteam.partylist.Domain.User;
import com.cranesteam.partylist.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Class for User detail services
 * Spring Security
 *
 * @author ilyaivankin
 *
 * @see UserDetailsService
 */
@Service("userServices")
public class UserServices implements UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServices(@Qualifier("userRepository") UserRepository userRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Load user
     *
     * @param username username or email or phone number
     * @return User
     *
     * @throws UsernameNotFoundException ex -> User not found
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else return null; // todo

    }

    /**
     * @param user
     *
     * todo: create full user
     *
     */
    public void saveUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setRole(new HashSet<Role>(Arrays.asList(Role.USER)));
        userRepository.save(newUser);
    }

}
