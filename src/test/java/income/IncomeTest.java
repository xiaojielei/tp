package income;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {

    @Test
    void testIncomeCreation() {
        Income income = new Income(250.0, "Part-time Job");
        assertEquals(250.0, income.getAmount());
        assertEquals("Part-time Job", income.getSource());
    }

    @Test
    void testToString() {
        Income income = new Income(99.99, "Scholarship");
        assertEquals("$99.99 from Scholarship", income.toString());
    }
}

