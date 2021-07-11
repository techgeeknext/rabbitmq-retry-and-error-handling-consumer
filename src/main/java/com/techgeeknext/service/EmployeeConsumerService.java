package com.techgeeknext.service;

import com.techgeeknext.exception.CustomInvalidException;
import com.techgeeknext.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class EmployeeConsumerService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeConsumerService.class);

	@RabbitListener(queues = "techgeeknext.queue")
	public void consumeMessage(Employee employee) throws CustomInvalidException {
		logger.info("Recieved Message From RabbitMQ techgeeknextExchange: " + employee);
		if (employee.getExperience() < 0 || employee.getExperience() > 30 ) {
			throw new CustomInvalidException();
		}
	}
}