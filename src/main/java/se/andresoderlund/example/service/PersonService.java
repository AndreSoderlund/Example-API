package se.andresoderlund.example.service;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.andresoderlund.example.api.request.CreatePerson;
import se.andresoderlund.example.db.DbIntegration;
import se.andresoderlund.example.db.entity.Person;
import se.andresoderlund.example.db.entity.PersonDTO;
import se.andresoderlund.example.db.entity.utility.Gender;
import se.andresoderlund.example.service.salary.Developer;
import se.andresoderlund.example.service.salary.ProductOwner;
import se.andresoderlund.example.service.salary.SalaryControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static se.andresoderlund.example.service.salary.utility.SalaryFactory.createSalaryStrategy;

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


    /**
     * Declarative style
     * Get all person entities and map them to DTO
     * before returning to presentation layer
     */
    public List<PersonDTO> getAll() {
        return dbIntegration.getAllPersons().stream()
                .map(mapper::toDto)
                .toList();
    }

    /**
     * Imperative style
     * Get list of all person entities.
     * Create list of personDTO
     * Loop through list of entities and call toDTO on every instance
     * Put the dtos in the new list
     * Return to presentation layer
     */
    public List<PersonDTO> getAll_Imperative() {
        List<Person> persons = dbIntegration.getAllPersons();
        List<PersonDTO> dtos = new ArrayList<>();
        for (Person person : persons) {
            dtos.add(mapper.toDto(person));
        }
        return dtos;
    }

    /**
     * Declarative style
     * Get all person entities from database and filter based on given gender
     * map the filtered entities to DTO and create list of PersonDTO that is returned to presentation layer
     */
    public List<PersonDTO> getPersonsByGender(final Gender gender) {
        return dbIntegration.getAllPersons().stream()
                .filter(person -> person.getGender().equals(gender))
                .map(mapper::toDto)
                .toList();
    }


    //Fetches all persons, sorts them by gender in map.
    public Map<Gender, List<PersonDTO>> getPersonMapByGender() {
        return dbIntegration.getAllPersons().stream()
                .collect(groupingBy(Person::getGender, mapping(mapper::toDto, toList())));
    }

    public List<PersonDTO> getAllPersonsByGender(final Gender gender) {
        return getPersonMapByGender().get(gender);
    }


    /**
     * Imperative style
     * Get all person entities from database
     * Create new List of PersonDTO
     * Loop through all entities and check if their gender matches given gender
     * Add matches to List of PersonDTO
     * Return the new list to the presentation layer
     */
    public List<PersonDTO> getPersonsByGender_Imperative(final Gender gender) {
        List<Person> persons = dbIntegration.getAllPersons();
        List<PersonDTO> dtos = new ArrayList<>();
        for (Person person : persons) {
            if (person.getGender().equals(gender)) {
                dtos.add(mapper.toDto(person));
            }
        }
        return dtos;
    }

    public List<PersonDTO> getPersonsByGender_Iterator(final Gender gender) {
        List<Person> persons = dbIntegration.getAllPersons();
        Iterator<Person> iterator = persons.iterator();
        List<PersonDTO> dtos = new ArrayList<>();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getGender().equals(gender)) {
                dtos.add(mapper.toDto(person));
            }
        }
        return dtos;
    }

    /**
     * Raising the persons salary based on age.
     * This is using the strategy pattern.
     * This approach requires an interface and a separate class for each strategy.
     */
    public void raiseSalary_Imperative(final Long id) {
        var person = dbIntegration.getPersonById(id);
        if (person.getAge() <= 40) {
            var raiseStrategy = new Developer();
            raise_Imperative(person, raiseStrategy);
        } else {
            var raiseStrategy = new ProductOwner();
            raise_Imperative(person, raiseStrategy);
        }
    }

    private void raise_Imperative(final Person person, final SalaryControl raiseStrategy) {
        person.setSalary(raiseStrategy.applyRaise(person.getSalary()));
        dbIntegration.savePerson(person);
    }


    private void raise_Imperative(final Person person) {
        var salaryControl = createSalaryStrategy(person);
        person.setSalary(salaryControl.applyRaise(person.getSalary()));
    }

    @Transactional
    public List<PersonDTO> raiseAllSalaries_Imperative() {
        List<Person> persons = dbIntegration.getAllPersons();
        List<PersonDTO> dtos = new ArrayList<>();
        for (Person person : persons) {
            raise(person);
            dtos.add(mapper.toDto(person));
            dbIntegration.savePerson(person);
        }
        return dtos;
    }


    /**
     * Raising the salary by role using a functional style.
     */

    private Person raise(final Person person) {
        person.setSalary(person.getRole().raiser.apply(person.getSalary()));
        return person;
    }

    @Transactional
    public List<PersonDTO> raiseAllSalaries() {
        return dbIntegration.getAllPersons().stream()
                .map(this::raise)
                .map(dbIntegration::savePerson)
                .map(mapper::toDto)
                .toList();
    }


    public PersonDTO getPersonById(final Long id) {
        return mapper.toDto(dbIntegration.getPersonById(id));
    }

    public PersonDTO createPerson(final CreatePerson request) {
        return mapper.toDto(dbIntegration.savePerson(mapper.toEntity(request)));
    }

    public void deletePersonById(final Long id) {
        dbIntegration.deletePersonById(id);
    }

    public void updatePerson(final Long id, final JsonPatch patch) {
        Person person = dbIntegration.getPersonById(id);
        Person updatedPerson = patcher.applyPatch(patch, Person.class, person);
        dbIntegration.savePerson(updatedPerson);
    }


}
