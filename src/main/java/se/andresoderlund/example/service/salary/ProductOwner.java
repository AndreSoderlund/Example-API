package se.andresoderlund.example.service.salary;

import java.math.BigDecimal;

public class ProductOwner implements SalaryControl {

    @Override
    public BigDecimal applyRaise(final BigDecimal salary) {
        return salary.multiply(BigDecimal.valueOf(1.15));
    }
}
