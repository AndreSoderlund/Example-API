package se.andresoderlund.example.api.request;

import se.andresoderlund.example.db.entity.utility.Gender;
import se.andresoderlund.example.db.entity.utility.Role;

import java.math.BigDecimal;

public record CreatePerson(
        String name,
        String email,
        Gender gender,
        int age,
        BigDecimal salary,
        Role role) {
}
