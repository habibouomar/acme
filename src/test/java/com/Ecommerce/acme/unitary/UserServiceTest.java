package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.Role;
import com.Ecommerce.acme.model.User;
import com.Ecommerce.acme.repository.UserRepository;
import com.Ecommerce.acme.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.Ecommerce.acme.model.Role.CLIENT;
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
        Assertions.assertEquals(result, service.getUser(1));
    }

    @Test
    public void mustToSendTheCorrespondingUserByUsername(){
        User user = new User();
        user.setUsername("john");

        when(repository.findByUsername("john")).thenReturn(user);
        assertEquals(user, service.findByUsername("john"));
    }

    @Test
    public void mustToSendTheCorrespondingAllUserByRole(){
        User user = new User();
        List<User> usersList = new ArrayList<>();
        user.setRole(CLIENT);
        usersList.add(user);

        when(repository.findAllByRole(Role.CLIENT)).thenReturn(usersList);
        assertEquals(usersList, service.getAllUsers());
    }
}
