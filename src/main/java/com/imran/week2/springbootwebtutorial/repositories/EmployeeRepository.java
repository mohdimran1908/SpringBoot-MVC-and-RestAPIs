package com.imran.week2.springbootwebtutorial.repositories;

import com.imran.week2.springbootwebtutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Extending from component annotation - This will become a bean now
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

   // List<EmployeeEntity> findByName(String name);
}
