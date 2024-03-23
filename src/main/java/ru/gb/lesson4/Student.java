package ru.gb.lesson4;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "firstName")
    private String firstName;
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "secondName")
    private String secondName;
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column(name = "age")
    private Integer age;
    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", secondName=" + secondName +
                ", age=" + age;
    }
}
