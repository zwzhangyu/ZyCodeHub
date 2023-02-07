package com.zy.design_pattern.builder.example1;

/**
 * 内部类构造者
 *
 * @author zhangyu
 * @date 2023/2/4
 */
public class User {

    private String name;

    private String address;

    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    private User(Builder builder) {
        name = builder.name;
        address = builder.address;
        age = builder.age;
    }


    public static class Builder {

        private String name;

        private String address;

        private int age;


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User builder() {
            return new User(this);
        }
    }

}
