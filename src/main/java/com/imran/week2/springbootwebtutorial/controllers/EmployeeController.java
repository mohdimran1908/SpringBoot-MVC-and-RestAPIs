package com.imran.week2.springbootwebtutorial.controllers;

import com.imran.week2.springbootwebtutorial.dto.EmployeeDTO;
import com.imran.week2.springbootwebtutorial.entities.EmployeeEntity;
import com.imran.week2.springbootwebtutorial.repositories.EmployeeRepository;
import com.imran.week2.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: Hello buddy";
//    }

    //Controller is dependent on Employee Repository - Dependency Injection
   // private final EmployeeRepository employeeRepository;  //this is not a good practice to connect Controller with Repository, it should be handled by Service layer, as we have not learned Service yet, so we are connecting here to understand JPA

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService= employeeService;
    }


    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        //return new EmployeeDTO(employeeId, "Imran", "imran@gmail.com", 24, LocalDate.of(2024,1,2),true);

        //return employeeRepository.findById(id).orElse(null);

        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){

        return employeeService.createNewEmployee(inputEmployee);

    }

    @PutMapping()
    public String updateEmployeeById(){
        return "Hello from PUT";
    }

    @PatchMapping()
    public String patchEmployeeData(){
        return "Hello from PATCH";
    }


}
