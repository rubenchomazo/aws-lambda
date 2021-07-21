package com.aws.lambda.test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.aws.lambda.StartApplication;
import com.aws.lambda.entity.Employee;
import com.aws.lambda.repositories.EmployeeRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class EmployeeRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    EmployeeRepository repository;


    @Before
    public void setup() throws Exception {
//        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
//        dynamoDBMapper.generateDeleteTableRequest(Employee.class);
//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(Employee.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(1L, 1L));
//        amazonDynamoDB.createTable(tableRequest);
//        dynamoDBMapper.batchDelete(
//                (List<Employee>) repository.findAll());
    }

    @Test
    @Ignore
    public void testSaveOrProduct() {
        Employee employee = new Employee();
        employee.setId("003");
        employee.setName("Rubencho");
        Employee employeeResp = repository.save(employee);
        assertTrue(employeeResp != null);
    }


}
