package com.assignment.demo.dto;

import com.assignment.demo.model.Group;
import com.assignment.demo.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;
import java.util.stream.Collectors;

public class UserDto {

    private Long id;

    @Size(max = 255, message = "Input must not be greater than 500 characters")
    @NotNull(message = "UserName must not be empty")
    private String userName;

    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Input must not be greater than 500 characters")
    @NotNull(message = "Email must not be empty")
    private String email;

    @Size(max = 255, message = "Input must not be greater than 500 characters")
    @NotNull(message = "Password must not be empty")
    private String password;

    private List<GroupsDto> groupsDtoList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GroupsDto> getGroupsDtoList() {
        return groupsDtoList;
    }

    public void setGroupsDtoList(List<GroupsDto> groupsDtoList) {
        this.groupsDtoList = groupsDtoList;
    }

    public static List<UserDto> toDto(List<User> users){
        return users.stream().map(UserDto::toDto).collect(Collectors.toList());
    }

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        if(!Objects.isNull(user)){
            userDto.setEmail(user.getEmail());
            userDto.setId(user.getId());
            userDto.setUserName(user.getName());
            userDto.setGroupsDtoList(GroupsDto.toDto(user.getGroups()));
        }
        return userDto;
    }


    //    public static UserDto create(User user){
//        return UserDto.builder()
//                .id(user.getId())
//                .userName(user.getName())
//                .email(user.getEmail())
//                .pass(user.getIsActive())
//                .build();
//    }

}
