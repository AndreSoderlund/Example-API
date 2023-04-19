package se.andresoderlund.example.api.request;

import se.andresoderlund.example.db.entity.utility.Gender;

public record CreatePerson(
        String name,
        String email,
        Gender gender,
        int age) {
}
