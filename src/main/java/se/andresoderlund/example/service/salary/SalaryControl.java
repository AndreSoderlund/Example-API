package se.andresoderlund.example.service.salary;

import java.math.BigDecimal;

public interface SalaryControl {

    BigDecimal applyRaise(final BigDecimal salary);

}
