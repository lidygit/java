package q8.starter.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 20:11
 * @需求:
 * @思路说明:
 */
@ConfigurationProperties(prefix="school")
public class SchoolProperties {


    Klass class1;


    Student student100;

    public Klass getClass1() {
        return class1;
    }

    public void setClass1(Klass class1) {
        this.class1 = class1;
    }

    public Student getStudent100() {
        return student100;
    }

    public void setStudent100(Student student100) {
        this.student100 = student100;
    }
}
