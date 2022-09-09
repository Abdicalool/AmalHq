package com.abdi.abdi.repo;

import com.abdi.abdi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
