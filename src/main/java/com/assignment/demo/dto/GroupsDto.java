package com.assignment.demo.dto;

import com.assignment.demo.model.Group;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class GroupsDto {

    private Long id;

    @Size(max = 255, message = "Input must not be greater than 255 characters")
    @NotNull(message = "Group name must not be empty")
    private String name;

    @Size(max = 500, message = "Input must not be greater than 500 characters")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<GroupsDto> toDto(Set<Group> groups){
        return groups.stream().map(GroupsDto::toDto).collect(Collectors.toList());
    }

    public static GroupsDto toDto(Group group){
        GroupsDto groupsDto = new GroupsDto();
        if(!Objects.isNull(group)){
            groupsDto.setName(group.getName());
            groupsDto.setId(group.getId());
            groupsDto.setDescription(group.getDescription());
        }
        return groupsDto;
    }


}
