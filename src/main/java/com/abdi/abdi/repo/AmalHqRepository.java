package com.abdi.abdi.repo;

import com.abdi.abdi.entity.AmalHq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AmalHqRepository extends JpaRepository<AmalHq,Long> {


    List<AmalHq> findByCustomerId(Long id);
}
