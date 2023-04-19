package se.andresoderlund.example.service;

import org.springframework.stereotype.Component;
import se.andresoderlund.example.api.request.CreatePerson;
import se.andresoderlund.example.db.entity.Person;
import se.andresoderlund.example.db.entity.PersonDTO;

@Component
public class PersonMapper {

    public PersonDTO toDto(final Person entity) {
        if (entity == null) {
            return null;
        }

        return new PersonDTO.Builder()
                .withId(entity.getId())
                .withAge(entity.getAge())
                .withName(entity.getName())
                .build();
    }

    public Person toEntity(final CreatePerson request){
        if(request == null){
            return null;
        }

        return new Person.Builder()
                .withAge(request.age())
                .withName(request.name())
                .withEmail(request.email())
                .withGender(request.gender())
                .build();
    }


}
