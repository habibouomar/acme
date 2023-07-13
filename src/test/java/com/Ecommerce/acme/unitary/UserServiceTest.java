package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.User;
import com.Ecommerce.acme.repository.UserRepository;
import com.Ecommerce.acme.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService service;

    @Test
    public void mustToSendTheCorrespondingUserById(){
        User user = new User();
        user.setId_user(1);

        Optional result = Optional.of(user);

        when(repository.findById(1)).thenReturn(result);
        assertEquals(result, service.getUser(1));
    }

    @Test
    public void mustToSendTheCorrespondingUserByUsername(){
        User user = new User();
        user.setUsername("john");

        when(repository.findByUsername("john")).thenReturn(user);
        assertEquals(user, service.findByUsername("john"));
    }
}