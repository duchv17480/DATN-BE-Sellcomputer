package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.GroupComponent;
import com.example.udpm14sellcomputerpartsbackend.repository.GroupComponentRepository;
import com.example.udpm14sellcomputerpartsbackend.service.GroupComponentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class GroupComponentServiceImpl implements GroupComponentService {

    private final GroupComponentRepository groupComponentRepository;

    @Override
    public List<GroupComponentEntity> getAll() {
        return groupComponentRepository.findAll();
    }

    @Override
    public GroupComponent createComponent(GroupComponent groupComponent) {
        GroupComponentEntity groupComponentEntity = new GroupComponentEntity();
        groupComponentEntity.setName(groupComponent.getName());
        groupComponentRepository.save(groupComponentEntity);
        return groupComponent;
    }

    @Override
    public GroupComponent updateComponent(Long id, GroupComponent groupComponent) {
        GroupComponentEntity groupComponentEntity = groupComponentRepository.findById(id).get();
        if (groupComponentEntity == null){
            return null;
        }
        groupComponentEntity.setName(groupComponent.getName());
        groupComponentRepository.save(groupComponentEntity);
        return groupComponent;
    }

    @Override
    public GroupComponent deleteComponent(Long id) {
        groupComponentRepository.deleteById(id);
        return new GroupComponent();
    }
}
