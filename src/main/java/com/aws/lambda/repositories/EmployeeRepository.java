package com.aws.lambda.repositories;

import com.aws.lambda.entity.Employee;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
