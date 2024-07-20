package com.imran.week2.springbootwebtutorial.controllers;

import com.imran.week2.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: Hello buddy";
//    }
    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Imran", "imran@gmail.com", 24, LocalDate.of(2024,1,2),true);
    }

    @GetMapping()
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi age "+ age + " " + sortBy;
    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping()
    public String updateEmployeeById(){
        return "Hello from PUT";
    }

    @PatchMapping()
    public String patchEmployeeData(){
        return "Hello from PATCH";
    }

    //PostMapping using @RequestBody



}
