package com.prac.mongodb.repository;

import com.prac.mongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String>,PagingAndSortingRepository<Employee,String> {
    @Query("{'position':?0}")
    List<Employee> findAllByPosition(String position);
}
