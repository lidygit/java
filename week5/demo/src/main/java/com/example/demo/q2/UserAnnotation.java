package com.example.demo.q2;

import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 22:19
 * @需求:
 * @思路说明:
 */
@Component
public class UserAnnotation {
    private String name="UserAnnotation";
    private String phone="18888888888";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserAnnotation{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
