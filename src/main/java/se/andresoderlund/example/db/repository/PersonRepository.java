package se.andresoderlund.example.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.andresoderlund.example.db.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    //Default findById implementation
    Optional<Person> findById(final Long id);

    //Non-Optional implementation
    Person findPersonById(final Long id);


}
