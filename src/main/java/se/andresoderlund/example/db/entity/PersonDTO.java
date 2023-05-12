package se.andresoderlund.example.db.entity;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class PersonDTO {

    private final Long id;

    private final String name;

    private final int age;

    private final BigDecimal salary;

    public PersonDTO(Long id, String name, int age, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    private PersonDTO(final Builder builder) {
        this.id = builder.id;
        this.age = builder.age;
        this.name = builder.name;
        this.salary = builder.salary;
    }

    private PersonDTO(final PersonDTO.FunctionalBuilder builder) {
        this.id = builder.id;
        this.age = builder.age;
        this.name = builder.name;
        this.salary = builder.salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSalary() {
        return salary;
    }


    public static class FunctionalBuilder {

        public Long id;
        public String name = "";
        public int age = 0;
        public BigDecimal salary = BigDecimal.valueOf(0);

        public PersonDTO.FunctionalBuilder with(Consumer<PersonDTO.FunctionalBuilder> consumer) {
            consumer.accept(this);
            return this;
        }

        public PersonDTO build() {
            return new PersonDTO(this);
        }

    }

    public static class Builder {

        private Long id;
        private String name = "";
        private int age = 0;
        private BigDecimal salary = BigDecimal.valueOf(0);

        public PersonDTO.Builder withId(final Long id) {
            this.id = id;
            return this;
        }

        public PersonDTO.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public PersonDTO.Builder withAge(final int age) {
            this.age = age;
            return this;
        }

        public PersonDTO.Builder withSalary(final BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public PersonDTO build() {
            return new PersonDTO(this);
        }
    }


}
