package com.aws.lambda.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PingControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addValueTest() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // send json with POST
        ResponseEntity<String> response = restTemplate.getForEntity("/ping", String.class);
        //printJSON(response);
        String expectedJson = "pong";
        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedJson, response.getBody());

    }
}
