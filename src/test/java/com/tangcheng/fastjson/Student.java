package com.tangcheng.fastjson;

public class Student {
    private String name;
    private int age;
    private boolean isMale;
    private Student gf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public Student getGf() {
        return gf;
    }

    public void setGf(Student gf) {
        this.gf = gf;
    }
}