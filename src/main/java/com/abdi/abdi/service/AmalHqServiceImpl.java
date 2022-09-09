package com.abdi.abdi.service;

import com.abdi.abdi.entity.AmalHq;
import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.repo.AmalHqRepository;
import com.abdi.abdi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AmalHqServiceImpl implements AmalHqService{
    @Autowired
   private AmalHqRepository amalHqRepository;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<AmalHq> getAllBranch() {
        return  amalHqRepository.findAll();
    }

    @Override
    public AmalHq createNewBranch(AmalHq amalHq,Long customerId) {

        Customer customer = customerRepo.findById(customerId).get();
        amalHq.setCustomer(customer);

        return  amalHqRepository.save(amalHq);
    }

    @Override
    public List<AmalHq> getBrancById(Long id) {

        return  amalHqRepository.findByCustomerId(id);
    }

    @Override
    public void deleteBranch(Long id) {
      amalHqRepository.deleteById(id);
    }
}
