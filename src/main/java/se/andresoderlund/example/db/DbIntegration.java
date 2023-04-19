package se.andresoderlund.example.db;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.andresoderlund.example.db.entity.Person;
import se.andresoderlund.example.db.repository.PersonRepository;
import se.andresoderlund.example.db.exception.EntityNotFoundException;

import java.util.List;

@Component
@Transactional
public class DbIntegration {

    private final PersonRepository personRepository;

    public DbIntegration(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Get by id using optionals for null/exception handling
    public Person getPersonById(final Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }

    //Get by id using imperative programming to handle null and throw exception
    public Person getPersonById_Imperative(final Long id) {
        Person person = personRepository.findPersonById(id);
        if (person == null) {
            throw new EntityNotFoundException("Person not found");
        }
        return person;
    }

    //Delete by id using optionals for null/exception handling
    public void deletePersonById(final Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        personRepository.delete(person);
    }

    //Delete by id with imperative style
    public void deletePersonById_Imperative(final Long id) {
        if (personRepository.existsById(id)) {
            Person person = personRepository.findPersonById(id);
            personRepository.delete(person);
        } else {
            throw new EntityNotFoundException("Person not found");
        }
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(final Person person){
        return personRepository.save(person);
    }

}

