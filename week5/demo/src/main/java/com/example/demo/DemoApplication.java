package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import q8.starter.starter.Student;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ImportResource(locations = {"classpath:application-bean.xml"})
@SpringBootApplication
public class DemoApplication {

	@Resource
	Student student;

	@Bean
	public void print(){
		System.out.println(student.toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
