package se.andresoderlund.example.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.andresoderlund.example.api.request.CreatePerson;
import se.andresoderlund.example.db.entity.Person;
import se.andresoderlund.example.db.entity.PersonDTO;

import java.util.Optional;

@Component
public class PersonMapper {

    private static final Logger LOG = LoggerFactory.getLogger(PersonMapper.class);

    /**
     * Declarative approach
     * Optional.ofNullable will create an optional of whatever input is given.
     * If any exception happens during the mapping to DTO the orElseThrow will throw an IllegalArgumentException.
     * This catches null input as well as a null return from any of the getters.
     */
    public PersonDTO toDto(final Person entity) {
        return Optional.ofNullable(entity)
                .map(person -> new PersonDTO.Builder()
                        .withId(entity.getId())
                        .withAge(entity.getAge())
                        .withName(entity.getName())
                        .withSalary(entity.getSalary())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Cannot map a Person with null values to DTO"));
    }

    public Person toEntity(final CreatePerson request) {
        return Optional.ofNullable(request)
                .map(personDto -> new Person.Builder()
                        .withName(request.name())
                        .withAge(request.age())
                        .withGender(request.gender())
                        .withEmail(request.email())
                        .withRole(request.role())
                        .withSalary(request.salary())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot map a DTO with null values to Person"));
    }

    /**
     * Declarative approach using the functional style builder of the person class.
     * Using a functional style builder might be considered an anti-pattern.
     * The purpose is to make the code more readable and less verbose, but this might be doing the opposite.
     */
    public Person toEntityFunctionalBuilder(final CreatePerson request) {
        return Optional.ofNullable(request)
                .map(entity -> new Person.FunctionalBuilder()
                        .with(person -> person.setSalary(request.salary()))
                        .with(person -> person.setAge(request.age()))
                        .with(person -> person.setName(request.name()))
                        .with(person -> person.setGender(request.gender()))
                        .with(person -> person.setEmail(request.email()))
                        .with(person -> person.setRole(request.role()))
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Cannot map a Person with null values to DTO"));
    }

    public PersonDTO toDtoFunctionalBuilder(final Person entity) {
        return Optional.ofNullable(entity)
                .map(person -> new PersonDTO.FunctionalBuilder()
                        .with(builder -> builder.id = entity.getId())
                        .with(builder -> builder.age = entity.getAge())
                        .with(builder -> builder.name = entity.getName())
                        .with(builder -> builder.salary = entity.getSalary())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Cannot map a Person with null values to DTO"));
    }


    /**
     * Imperative mapping with null check and try/catch to log any nullpointerexceptions.
     */
    public PersonDTO toDto_Exception_Imperative(final Person entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot map null to personDTO");
        }
        try {
            return new PersonDTO(entity.getId(), entity.getName(),
                    entity.getAge(), entity.getSalary());
        } catch (NullPointerException ex) {
            LOG.error("Getter returned null when mapping entity to dto", ex);
            throw ex;
        }
    }

    public Person toEntity_Exception_Imperative(final CreatePerson request) {
        if (request == null) {
            throw new IllegalArgumentException("Cannot map null to personDTO");
        }
        try {
            return new Person(request.name(), request.email(),
                    request.age(), request.salary(),
                    request.role(), request.gender());
        } catch (NullPointerException ex) {
            LOG.error("Getter returned null when mapping request to entity", ex);
            throw ex;
        }
    }
}
