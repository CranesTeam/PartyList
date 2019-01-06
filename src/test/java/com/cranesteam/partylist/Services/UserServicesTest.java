package com.cranesteam.partylist.Services;

import com.cranesteam.partylist.Domain.Role;
import com.cranesteam.partylist.Domain.User;
import com.cranesteam.partylist.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServicesTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserServices userServices;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServices = new UserServices(userRepository,
                bCryptPasswordEncoder);

        user = new User();
        user.setId((long) 1);
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setNumber("+79991234567");
        user.setImage(false);

        user.setRole(Collections.singleton(Role.USER));
        user.setActive(true);

        Mockito.when(userRepository.save(any()))
                .thenReturn(user);
        Mockito.when(userRepository.findByUsername(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        final String username = "test";

        // Run the test
        final UserDetails result = userServices.loadUserByUsername(username);

        // Verify the results
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testSaveUser() {
        final String name = "test";

        // Run the test
        userServices.saveUser(user);

        // Verify the results
        assertEquals(name, userRepository.findByUsername(name).getUsername());
    }

    @Test
    public void testAuth() {
        final String email = "test@test.com";
        final String username = "test";
        final String number = "+79991234567";

        // Verify the results
        assertEquals("test email",
                email, userRepository.findByUsername(email).getEmail());
        assertEquals("test name",
                username, userRepository.findByUsername(email).getUsername());
        assertEquals("test number",
                number, userRepository.findByUsername(number).getNumber());

    }


}