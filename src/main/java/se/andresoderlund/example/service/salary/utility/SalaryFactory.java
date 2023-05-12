package se.andresoderlund.example.service.salary.utility;

import se.andresoderlund.example.db.entity.Person;
import se.andresoderlund.example.service.salary.Developer;
import se.andresoderlund.example.service.salary.ProductOwner;
import se.andresoderlund.example.service.salary.SalaryControl;
import se.andresoderlund.example.service.salary.ScrumMaster;
import se.andresoderlund.example.service.salary.Tester;

public class SalaryFactory {

    public static SalaryControl createSalaryStrategy(final Person person) {

        switch (person.getRole()) {
            case DEVELOPER -> {
                return new Developer();
            }
            case SCRUM_MASTER -> {
                return new ScrumMaster();
            }
            case PRODUCT_OWNER -> {
                return new ProductOwner();
            }
            default -> {
                return new Tester();
            }
        }
    }
}
