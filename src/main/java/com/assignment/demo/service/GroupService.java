package com.assignment.demo.service;

import com.assignment.demo.dto.GroupsDto;
import com.assignment.demo.model.Group;
import com.assignment.demo.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {

        this.groupRepository = groupRepository;
    }

    public List<GroupsDto> fetchAllGroups() {
        return groupRepository.findAll().stream()
                .map(GroupsDto::toDto)
                .toList();
    }


    @Transactional(rollbackFor = Exception.class)
    public GroupsDto saveGroup(GroupsDto groupsDto) {

        Group group = new Group();
        group.setName(groupsDto.getName());
        group.setDescription(groupsDto.getDescription());
        groupRepository.save(group);
        return GroupsDto.toDto(group);

    }


}
