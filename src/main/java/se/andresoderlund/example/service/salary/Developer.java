package se.andresoderlund.example.service.salary;

import java.math.BigDecimal;

public class Developer implements SalaryControl {

    @Override
    public BigDecimal applyRaise(final BigDecimal salary) {
        return salary.multiply(BigDecimal.valueOf(1.13));
    }
}
