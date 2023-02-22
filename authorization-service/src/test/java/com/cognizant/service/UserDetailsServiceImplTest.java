package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;

/**
 * Test - UserDetailsServiceImpl
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class UserDetailsServiceImplTest {

    @Mock
    UserRepo repo;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsernameTest() {

        MyUser user = new MyUser(1, "admin", "admin");
        when(repo.findByUserName("admin")).thenReturn(user);
        UserDetails loadUserByUsername = userDetailsService.loadUserByUsername("admin");
        assertEquals(user.getUserName(), loadUserByUsername.getUsername());
    }

}
