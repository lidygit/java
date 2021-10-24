package q8.starter.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 20:06
 * @需求:
 * @思路说明:
 */
@Configuration
@EnableConfigurationProperties({StudentProperties.class})
public class StudentAutoConfiguration {

    @Autowired
    StudentProperties studentProperties;


    @Bean
    public Student getStudent(){
        return new Student(studentProperties.getId(),studentProperties.getName());
    }

}
