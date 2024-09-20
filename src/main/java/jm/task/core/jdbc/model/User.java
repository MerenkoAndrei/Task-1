package jm.task.core.jdbc.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "age")
    private byte age;

    public User() {
    }

    public User(String name, String lastName,byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {return age;}

    public void setAge(byte age) {this.age = age;}


    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               '}';
    }
}
