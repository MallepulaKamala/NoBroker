package com.nobroker.Service.impl;

import com.nobroker.Entity.OwnerPlan;
import com.nobroker.Repository.OwnerPlanRepository;
import com.nobroker.Service.OwnerPlanService;
import com.nobroker.payload.OwnerPlanDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerPlanServiceImpl implements OwnerPlanService {

    private OwnerPlanRepository ownerPlanRepo;
    private ModelMapper modelMap;

    public OwnerPlanServiceImpl(OwnerPlanRepository ownerPlanRepo,ModelMapper modelMap) {
        this.ownerPlanRepo = ownerPlanRepo;
        this.modelMap=modelMap;
    }

    @Override
    public OwnerPlanDto createOwnerPlans(OwnerPlanDto ownerPlanDto) {
        OwnerPlan ownerPlan = mapToEntity(ownerPlanDto);
        OwnerPlan savedOwnerPlan = ownerPlanRepo.save(ownerPlan);
        return mapToDto(savedOwnerPlan);

    }

    @Override
    public List<OwnerPlanDto> getAllOwnerPlans() {
        List<OwnerPlan> ownerPlan = ownerPlanRepo.findAll();
        List<OwnerPlanDto> ownerPlanDtos = ownerPlan.stream().map(plan -> mapToDto(plan)).collect(Collectors.toList());
        return ownerPlanDtos;
    }

    OwnerPlan mapToEntity(OwnerPlanDto ownerPlanDto){
        OwnerPlan ownerPlans = modelMap.map(ownerPlanDto, OwnerPlan.class);
        return ownerPlans;

    }
    OwnerPlanDto mapToDto(OwnerPlan ownerPlan){
        OwnerPlanDto ownerPlanDto = modelMap.map(ownerPlan, OwnerPlanDto.class);
        return ownerPlanDto;
    }
}
