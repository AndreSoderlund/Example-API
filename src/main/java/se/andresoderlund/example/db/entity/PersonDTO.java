package se.andresoderlund.example.db.entity;

public class PersonDTO {

    private Long id;

    private String name;

    private int age;

    private PersonDTO(Builder builder) {
        this.id = builder.id;
        this.age = builder.age;
        this.name = builder.name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {

        private Long id;
        private String name = "";
        private int age = 0;

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

        public PersonDTO build() {
            return new PersonDTO(this);
        }
    }


}
