package com.assignment.demo.service;

import com.assignment.demo.aop.AssignmentException;
import com.assignment.demo.dto.GroupsDto;
import com.assignment.demo.model.Group;
import com.assignment.demo.repository.GroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Spy
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    public GroupServiceTest() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void unitTest_SaveGroup_Success() {
        // Arrange
        GroupsDto groupsDto = createGroupDto("Group Name", "Group Description");
        Group group = new Group();
        group.setName(groupsDto.getName());
        group.setDescription(groupsDto.getDescription());

        lenient().when(groupRepository.save(any(Group.class))).thenReturn(group);

        GroupsDto savedGroup = groupService.saveGroup(groupsDto);

        Assertions.assertEquals(groupsDto.getName(), savedGroup.getName());
        Assertions.assertEquals(groupsDto.getDescription(), savedGroup.getDescription());
    }

    @Test
    void testFetchAllGroups() {
        // Arrange
        Group group1 = createGroup("Group 1", "Group 1 Desc", 1l);
        Group group2 = createGroup("Group 2", "Group 2 Desc", 2l);
        List<Group> groups = Arrays.asList(group1, group2);

        lenient().when(groupRepository.findAll()).thenReturn(groups);

        List<GroupsDto> groupsDtos = groupService.fetchAllGroups();

        Assertions.assertEquals(2, groupsDtos.size());
        Assertions.assertEquals("Group 1", groupsDtos.get(0).getName());
        Assertions.assertEquals("Group 2", groupsDtos.get(1).getName());
    }

    private Group createGroup(String name, String description, Long id) {
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        group.setId(id);
        return group;
    }

    private GroupsDto createGroupDto(String name, String description) {
        GroupsDto group = new GroupsDto();
        group.setName(name);
        group.setDescription(description);
        return group;
    }

}
