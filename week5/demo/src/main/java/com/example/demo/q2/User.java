package com.example.demo.q2;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 18:57
 * @需求:
 * @思路说明:
 */
public class User {
    private String name;
    private String phone;

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
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
