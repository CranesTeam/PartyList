package com.cranesteam.partylist.Services;

import com.cranesteam.partylist.Domain.Role;
import com.cranesteam.partylist.Domain.User;
import com.cranesteam.partylist.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
@Service("userServices")
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setRole(new HashSet<Role>(Arrays.asList(Role.USER)));
        userRepository.save(newUser);
    }

}
