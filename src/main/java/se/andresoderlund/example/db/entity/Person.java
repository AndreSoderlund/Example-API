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

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Person(final String name, final int age, final Gender gender, final String email) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    private Person(final Builder builder) {
        this.email = builder.email;
        this.age = builder.age;
        this.name = builder.name;
        this.gender = builder.gender;
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

    public static class Builder {

        private String name = "";
        private String email = "";
        private int age = 0;
        private Gender gender = Gender.MALE;

        public Builder withEmail(final String email){
            this.email = email;
            return this;
        }

        public Builder withGender(final Gender gender){
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

        public Person build() {
            return new Person(this);
        }
    }
}
