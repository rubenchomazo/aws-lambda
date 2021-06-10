package com.aws.lambda.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda.config.DynamoDBConfig;
import com.aws.lambda.entity.Employee;
import com.aws.lambda.repositories.EmployeeRepository;
import com.aws.lambda.service.impl.LoggerServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeHandler
        extends AbstractHandler<DynamoDBConfig>
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Autowired
    EmployeeRepository employeeRepository;

    private LoggerServiceImpl log;

    public EmployeeHandler() {

    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        log = new LoggerServiceImpl();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            String body = request.getBody();
            log.info("Function: " + context.getFunctionName());
            log.info("Request Body:" + body);
            Employee employee = null;
            if (body != null) {
                employee = new Gson().fromJson(request.getBody(), Employee.class);
                if (employeeRepository == null) {
                    employeeRepository = getApplicationContext().getBean(EmployeeRepository.class);
                }
                return response
                        .withStatusCode(200)
                        .withBody(new Gson().toJson(employeeRepository.save(employee)));
            } else {
                return response
                        .withStatusCode(405)
                        .withBody("Error with the request: " + request.getBody());
            }
        } catch (Exception e) {
            log.error("Function: " + context.getFunctionName());
            log.error("Exception " + e.getMessage());
            return response
                    .withBody("{error:" + e.getMessage() + "}")
                    .withStatusCode(500);
        }
    }
}
