package com.assignment.demo.service;

import com.assignment.demo.aop.AssignmentException;
import com.assignment.demo.dto.UserDto;
import com.assignment.demo.model.Group;
import com.assignment.demo.model.User;
import com.assignment.demo.repository.GroupRepository;
import com.assignment.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {


    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        log.debug("Request to save User : {}", userDto);
        if (userDto.getId() != null) {
            throw new AssignmentException("A new user cannot already have an ID");
        }
        Set<Group> groups = new HashSet<>();
        User user = new User();
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));

        userDto.getGroupsDtoList().forEach(groupsDto ->
                {
                    Group group = new Group();
                    if (groupsDto.getId() != null) {
                        group = groupRepository.findById(groupsDto.getId())
                                .orElseThrow(() -> new AssignmentException("Group could not found"));
                    } else {
                        group.setName(groupsDto.getName());
                        group.setDescription(groupsDto.getDescription());
                        groupRepository.save(group);
                    }
                    groups.add(group);
                }
        );
        user.setGroups(groups);
        user = userRepository.save(user);

        return UserDto.toDto(user);
    }

    public List<UserDto> findAll() {
        log.debug("Request to get all UserDto");
        return userRepository.findAll().stream().map(UserDto::toDto).toList();
    }

    public UserDto findOne(Long id) {
        log.debug("Request to get User : {}", id);

        User user = userRepository.findById(id).orElseThrow(() -> new AssignmentException("User could not found"));
        return UserDto.toDto(user);
    }


}
