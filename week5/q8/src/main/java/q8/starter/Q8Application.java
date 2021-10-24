package q8.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import q8.starter.starter.Klass;
import q8.starter.starter.School;
import q8.starter.starter.Student;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Q8Application {


    @Autowired
    Student student;

    @Autowired
    Klass klass;

    @Autowired
    School school;

    @Bean
    public void printStudent(){
        System.out.println(student.toString());
    }

    @Bean
    public void printKlass(){
        List<Student> list = new ArrayList<>();
        list.add(student);
        klass.setStudents(list);
        klass.dong();
    }

    @Bean
    public void printSchool(){

        List<Student> list = new ArrayList<>();
        list.add(student);
        klass.setStudents(list);
        school.setClass1(klass);
        school.setStudent100(student);
        school.ding();
    }
    public static void main(String[] args) {
        SpringApplication.run(Q8Application.class, args);
    }

}
