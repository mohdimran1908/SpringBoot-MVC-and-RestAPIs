package com.imran.week2.springbootwebtutorial.controllers;

import com.imran.week2.springbootwebtutorial.dto.EmployeeDTO;
import com.imran.week2.springbootwebtutorial.entities.EmployeeEntity;
import com.imran.week2.springbootwebtutorial.repositories.EmployeeRepository;
import com.imran.week2.springbootwebtutorial.services.EmployeeService;
import org.h2.command.ddl.CreateDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        //return new EmployeeDTO(employeeId, "Imran", "imran@gmail.com", 24, LocalDate.of(2024,1,2),true);

        //return employeeRepository.findById(id).orElse(null);

        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
//        if(employeeDTO == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){

        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }


}
