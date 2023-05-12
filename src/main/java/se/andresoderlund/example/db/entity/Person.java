package se.andresoderlund.example.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import se.andresoderlund.example.db.entity.utility.Gender;
import se.andresoderlund.example.db.entity.utility.Role;

import java.math.BigDecimal;
import java.util.function.Consumer;

@Entity
@Table(name = "persons")
@SequenceGenerator(name = "PERSON_SEQ", sequenceName = "persons_seq", allocationSize = 1)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    public Person(String name, String email, int age, BigDecimal salary, Role role, Gender gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.role = role;
        this.gender = gender;
    }

    private Person(final Builder builder) {
        this.role = builder.role;
        this.email = builder.email;
        this.age = builder.age;
        this.name = builder.name;
        this.gender = builder.gender;
        this.salary = builder.salary;
    }

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * This is a functional style builder that uses the consumer inteface to have only one method
     * that does all the work that an imperative builder would need n amount of methods for.
     * This seems like a very elegant way to get the Builder functionality.
     */
    public static class FunctionalBuilder {

        private Person person;

        public FunctionalBuilder with(final Consumer<Person> consumer) {
            consumer.accept(person);
            return this;
        }

        public Person build() {
            return person;
        }

    }

    /**
     * This is a builder of the person class. This lets us create complex objects in a very simple way.
     */

    public static class Builder {

        private String name;
        private String email;
        private int age;
        private BigDecimal salary;
        private Gender gender;
        private Role role;

        public Builder withRole(final Role role) {
            this.role = role;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder withGender(final Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(final int age) {
            this.age = age;
            return this;
        }

        public Builder withSalary(final BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
