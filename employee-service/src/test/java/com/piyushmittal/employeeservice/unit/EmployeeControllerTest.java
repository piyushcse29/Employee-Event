package com.piyushmittal.employeeservice.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.piyushmittal.employeeservice.Employee;
import com.piyushmittal.employeeservice.EmployeeController;
import com.piyushmittal.employeeservice.EmployeeRepository;
import com.piyushmittal.employeeservice.kafka.Event;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmployeeRepository employeeRepository;
	
	@MockBean
	KafkaTemplate<String, Event> kafkaTemplate;

	@Test
	public void testGetEmployee() throws Exception {

		List<String> hobbylist = new ArrayList<String>();
		hobbylist.add("Photography");
		hobbylist.add("Singing");

		Employee employee = new Employee("m@piyushmittal.com", "Piyush Mittal", LocalDate.of(1989, 2, 27), hobbylist);

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		RequestBuilder request = MockMvcRequestBuilders.get("/employees/1").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\n" + "        \"email\": \"m@piyushmittal.com\",\n"
						+ "        \"fullName\": \"Piyush Mittal\",\n" + "        \"birthdate\": \"1989-02-27\",\n"
						+ "        \"hobbies\": [\n" + "            \"Photography\",\n" + "            \"Singing\"\n"
						+ "        ]\n" + "    }"))
				.andReturn();
	}
	
}
