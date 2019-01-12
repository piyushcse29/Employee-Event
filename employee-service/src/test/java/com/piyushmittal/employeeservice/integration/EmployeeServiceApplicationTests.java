package com.piyushmittal.employeeservice.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EmployeeServiceApplicationTests {


	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetEmployee() throws Exception {
		String response = this.restTemplate.getForObject("/employees/1", String.class);

		JSONAssert.assertEquals("{\n" + "        \"email\": \"m@piyushmittal.com\",\n"
				+ "        \"fullName\": \"Piyush Mittal\",\n" + "        \"birthdate\": \"1989-02-28\",\n"
				+ "        \"hobbies\": [\n" + "            \"Photography\",\n" + "            \"Singing\"\n"
				+ "        ]\n" + "    }", response, false);

	}

}
