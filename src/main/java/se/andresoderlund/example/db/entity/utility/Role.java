package se.andresoderlund.example.db.entity.utility;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public enum Role {

    PRODUCT_OWNER(salary -> salary.multiply(BigDecimal.valueOf(1.15))),
    SCRUM_MASTER(salary -> salary.multiply(BigDecimal.valueOf(1.13))),
    DEVELOPER(salary -> salary.multiply(BigDecimal.valueOf(1.12))),
    TESTER(salary -> salary.multiply(BigDecimal.valueOf(1.07)));

    public final UnaryOperator<BigDecimal> raiser;

    Role(final UnaryOperator<BigDecimal> raiser) {
        this.raiser = raiser;
    }


}
