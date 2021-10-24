package q8.starter.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 20:11
 * @需求:
 * @思路说明:
 */
@ConfigurationProperties(prefix="student")
public class StudentProperties {

    String name="同学1";
    int id=1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
