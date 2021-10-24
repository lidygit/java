package q8.starter.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 20:11
 * @需求:
 * @思路说明:
 */
@ConfigurationProperties(prefix="klass")
public class KlassProperties {

    List<Student> students;

    @Override
    public String toString() {
        return "KlassProperties{" +
                "students=" + students +
                '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public KlassProperties(List<Student> students) {
        this.students = students;
    }
}
