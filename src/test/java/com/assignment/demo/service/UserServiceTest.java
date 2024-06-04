package com.assignment.demo.service;

import com.assignment.demo.aop.AssignmentException;
import com.assignment.demo.dto.GroupsDto;
import com.assignment.demo.dto.UserDto;
import com.assignment.demo.model.Group;
import com.assignment.demo.model.User;
import com.assignment.demo.repository.GroupRepository;
import com.assignment.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Spy
    private UserRepository userRepository;
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @Transactional
    void unitTest_saveUser_WithNewGroupuccessfully() {

        UserDto userDto = createUserDto();
        userDto = userService.save(userDto);

        Assertions.assertNotNull(userDto);
    }

    @Test
    void unitTest_SaveUser_WithExistingGroupSuccessful() {
        UserDto userDto = createUserDto();
        lenient().when(groupRepository.findById(any())).thenReturn(Optional.of(new Group()));
        lenient().when(userRepository.save(any())).thenReturn(new User());

        UserDto savedUser = userService.save(userDto);

        Assertions.assertNotNull(savedUser);
    }

    @Test
    void unitTest_SaveUser_WithExistingIdUserException() {
        UserDto userDto = createUserDto();
        userDto.setId(1L);
        lenient().when(userRepository.save(any())).thenReturn(new User());

        Assertions.assertThrows(AssignmentException.class, () -> userService.save(userDto));
    }

    @Test
    void unitTest_SaveUser_WithInvalidGroupId() {
        UserDto userDto = createUserDto();
        userDto.getGroupsDtoList().get(0).setId(999L); // Invalid group ID
        lenient().when(groupRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(AssignmentException.class, () -> userService.save(userDto));
    }

    @Test
    void unitTest_FindAll_Success() {
        List<User> users = new ArrayList<>();
        users.add(createUser("user1", "user1@example.com", 1l));
        users.add(createUser("user2", "user2@example.com", 2l));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDto> userDtos = userService.findAll();

        Assertions.assertEquals("user1", userDtos.get(0).getUserName());
        Assertions.assertEquals("user1@example.com", userDtos.get(0).getEmail());
        Assertions.assertEquals("user2", userDtos.get(1).getUserName());
        Assertions.assertEquals("user2@example.com", userDtos.get(1).getEmail());
    }

    @Test
    void unitTest_FindById_ExistingUser() {

        User user = createUser("user1", "user1@example.com", 1l);
        lenient().when(userRepository.findById(1l)).thenReturn(Optional.of(user));


        UserDto userDto = userService.findOne(1l);

        Assertions.assertEquals("user1", userDto.getUserName());
        Assertions.assertEquals("user1@example.com", userDto.getEmail());
    }

    private User createUser(String userName, String email, Long id) {
        User user = new User();
        user.setPassword("password");
        user.setName(userName);
        user.setEmail(email);
        user.setId(id);
        return user;
    }


    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserName("Asaad");
        userDto.setEmail("asd100@gmail.com");
        userDto.setPassword("password");

        userDto.setGroupsDtoList(Collections.singletonList(new GroupsDto()));

        return userDto;
    }

}
