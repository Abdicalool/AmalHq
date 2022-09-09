package com.abdi.abdi.controller;

import com.abdi.abdi.entity.AmalHq;
import com.abdi.abdi.service.AmalHqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class AmalHqController {
    @Autowired
     AmalHqService amalHqService;
    @GetMapping("/branch")
    public ResponseEntity<List<AmalHq>> getBranch()
    {
      List<AmalHq> amalHq = amalHqService.getAllBranch();
       return new ResponseEntity<>(amalHq, HttpStatus.OK);

    }
    @PostMapping("/createBranch/{customerId}")
    public  ResponseEntity<AmalHq> createBranch(@Valid @PathVariable Long customerId,  @RequestBody AmalHq amalHq, BindingResult result){
        if(result.hasErrors()){

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        AmalHq amalHq1 = amalHqService.createNewBranch(amalHq,customerId);

        return new ResponseEntity<>(amalHq1, HttpStatus.CREATED);

    }
    @GetMapping("/branch/{id}")
    public ResponseEntity<List<AmalHq>>  getBranch(@PathVariable Long id){
     List<AmalHq> amalHq = amalHqService.getBrancById(id);
     return new ResponseEntity<>(amalHq,HttpStatus.OK);
    }
    @DeleteMapping("/branch/{id}")
    public String deleteBranch(@PathVariable Long id){
      amalHqService.deleteBranch(id);
      return "Deleted Seuccessfuly";
    }
}
