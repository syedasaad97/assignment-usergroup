package com.assignment.demo.controller;

import com.assignment.demo.aop.AssignmentException;
import com.assignment.demo.dto.GroupsDto;
import com.assignment.demo.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping
    public ResponseEntity<List<GroupsDto>> getAllGroups() {
        List<GroupsDto> productDto = groupService.fetchAllGroups();
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping
    public ResponseEntity<GroupsDto> saveGroup(@Valid GroupsDto groupsDto) {
        if (!Objects.isNull(groupsDto.getId())) {
            throw new AssignmentException("Group should not be null");
        }
        GroupsDto response = groupService.saveGroup(groupsDto);
        return ResponseEntity.ok(response);
    }
}
