package com.tangcheng.db.entity;

public class StudentDo {

    private Long id;

    private String name;

    private Integer age;

    private String classes;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return classes
     */
    public String getClasses() {
        return classes;
    }

    /**
     * @param classes
     */
    public void setClasses(String classes) {
        this.classes = classes;
    }
}