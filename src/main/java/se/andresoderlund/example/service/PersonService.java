package se.andresoderlund.example.service;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.stereotype.Service;
import se.andresoderlund.example.api.request.CreatePerson;
import se.andresoderlund.example.db.DbIntegration;
import se.andresoderlund.example.db.entity.Person;
import se.andresoderlund.example.db.entity.PersonDTO;
import se.andresoderlund.example.db.entity.utility.Gender;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonMapper mapper;

    private final JsonPatcher patcher;

    private final DbIntegration dbIntegration;

    public PersonService(final PersonMapper mapper, final JsonPatcher patcher, final DbIntegration dbIntegration) {
        this.mapper = mapper;
        this.patcher = patcher;
        this.dbIntegration = dbIntegration;
    }

    //Declarative style
    //Get all persons and map them to DTO before returning to presentation layer.
    public List<PersonDTO> getAll(){
        return dbIntegration.getAllPersons()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    //Imperative style
    //Get list of all person entities.
    //Create list of personDTO
    //Loop through list of entities and call toDTO on every instance
    //Put the dtos in the new list.
    public List<PersonDTO> getAll_Imperative(){
        List<Person> persons = dbIntegration.getAllPersons();
        List<PersonDTO> dtos = new ArrayList<>();
        for (Person person : persons) {
            dtos.add(mapper.toDto(person));
        }
        return dtos;
    }

    //Declarative style
    //Get all persons and filter based on given gender, map result to DTO and return to presentation layer.
    public List<PersonDTO> getPersonsByGender(final Gender gender){
        return dbIntegration.getAllPersons().stream()
                .filter(a -> a.getGender().equals(gender))
                .map(mapper::toDto)
                .toList();
    }

    //Imperative style
    //Get all persons
    //Create new of PersonDTO
    //Loop through all persons and check if their gender matches given gender.
    //Map matches to DTOs and add to the new list.
    //Return this list to the presentation layer.
    public List<PersonDTO> getPersonsByGender_Imperative(final Gender gender){
        List<Person> persons = dbIntegration.getAllPersons();
        List<PersonDTO> dtos = new ArrayList<>();
        for(Person person : persons){
            if(person.getGender().equals(gender)){
                dtos.add(mapper.toDto(person));
            }
        }
        return dtos;
    }

    public PersonDTO getPersonById(final Long id){
        return mapper.toDto(dbIntegration.getPersonById(id));
    }

    public PersonDTO createPerson(final CreatePerson request){
        return mapper.toDto(dbIntegration.savePerson(mapper.toEntity(request)));
    }

    public void deletePersonById(final Long id){
        dbIntegration.deletePersonById(id);
    }

    public void updatePerson(final Long id, final JsonPatch patch) {
        Person person = dbIntegration.getPersonById(id);
        Person updatedPerson = patcher.applyPatch(patch, Person.class, person);
        dbIntegration.savePerson(updatedPerson);
    }
}
