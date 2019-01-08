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

    private final String email = "test@test.com";
    private final String username = "test";
    private final String number = "+79991234567";

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

        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(user);
        Mockito.when(userRepository.authFindUser(anyString())).thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // Run the test
        final UserDetails result = userServices.loadUserByUsername(username);
        // Verify the results
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testSaveUser() {
        // Run the test
        userServices.saveUser(user);

        // Verify the results
        assertEquals("find user by username before save method",
                username, userRepository.findByUsername(username).getUsername());
    }

    @Test
    public void findUserJpa() {
        // Verify the results
        assertEquals("test email",
                email, userRepository.findByUsername(email).getEmail());
        assertEquals("test name",
                username, userRepository.findByUsername(email).getUsername());
        assertEquals("test number",
                number, userRepository.findByUsername(number).getNumber());

    }

    @Test
    public void findAutUser() {
        // auth test
        assertEquals("find by 3 param -> email",
                username, userRepository.authFindUser(email).getUsername());

        assertEquals("find by 3 param -> phone",
                username, userRepository.authFindUser(number).getUsername());

        assertEquals("find by 3 param -> username",
                username, userRepository.authFindUser(username).getUsername());

        assertNotEquals("find by 3 param -> username",
                "user", userRepository.authFindUser(username).getUsername());

        assertNotEquals("find by 3 param -> email",
                "email", userRepository.authFindUser(email).getUsername());

        assertNotEquals("find by 3 param -> phone",
                "+789999999909", userRepository.authFindUser(number).getUsername());
    }


}