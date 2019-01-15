package com.cranesteam.partylist.Services;

import com.cranesteam.partylist.Domain.Role;
import com.cranesteam.partylist.Domain.User;
import com.cranesteam.partylist.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Class for User detail services
 * Spring Security
 *
 * @author ilyaivankin
 *
 * @see UserDetailsService
 */
@Slf4j
@Service("userServices")
@Scope("singleton")
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
        /* find user */
        User user = userRepository.authFindUser(username);

        if (user == null) {
            log.warn(username + "-> user is not authorized for this application");
            throw new UsernameNotFoundException("user is not authorized for this application");
        }
        /* return user */
        return user;
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException{
        /* find user */
        return userRepository.findByUsername(username);
    }


    /**
     * todo: create full user (registration)
     *
     * @param user user
     */
    public void saveUser(User user) {

        // todo: check user

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setRole(new HashSet<Role>(Arrays.asList(Role.USER)));
        userRepository.save(newUser);
    }

}
