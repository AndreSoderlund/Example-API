package se.andresoderlund.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import se.andresoderlund.example.api.request.CreatePerson;
import se.andresoderlund.example.db.entity.PersonDTO;
import se.andresoderlund.example.db.entity.utility.Gender;
import se.andresoderlund.example.service.PersonService;

import java.net.URI;
import java.util.List;

import static se.andresoderlund.example.api.PersonController.PATH;

@RestController
@RequestMapping(PATH)
public class PersonController {

    static final String PATH = "/api/person";

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    ResponseEntity<List<PersonDTO>> findAll(){
        List<PersonDTO> dtos = personService.getAll();
        if(dtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    ResponseEntity<PersonDTO> findById(@PathVariable final Long id){
        PersonDTO dto = personService.getPersonById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/gender/{gender}")
    ResponseEntity<List<PersonDTO>> findPersonsByGender(@PathVariable final Gender gender){
        List<PersonDTO> persons = personService.getPersonsByGender(gender);
        if(persons.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(persons);
    }

    @PostMapping
    ResponseEntity<Void> createPerson(@RequestBody final CreatePerson request){
        PersonDTO dto = personService.createPerson(request);
        URI uri = UriComponentsBuilder.fromPath(PATH + "/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable final Long id){
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }






}
