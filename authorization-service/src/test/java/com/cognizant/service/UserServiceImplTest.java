package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void testInsertUser() {
        MyUser user = new MyUser();
        doAnswer((Answer<Void>) invocation -> {
            MyUser arg = invocation.getArgument(0);
            arg.setUserId(1);
            return null;
        }).when(userRepo).save(user);

        userService.insertUser(user);

        verify(userRepo).save(user);
        assertEquals(1L, user.getUserId());
    }
}
