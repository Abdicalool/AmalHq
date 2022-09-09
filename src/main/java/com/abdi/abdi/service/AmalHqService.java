package com.abdi.abdi.service;

import com.abdi.abdi.entity.AmalHq;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface AmalHqService {

    List<AmalHq> getAllBranch();

    AmalHq createNewBranch(AmalHq amalHq,Long customerId);

    List<AmalHq> getBrancById(Long id);

    void deleteBranch(Long id);
}
