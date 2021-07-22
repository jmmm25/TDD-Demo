package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
 * 2개월 이상 요금을 납부할 수 있다.
 * 10만원을 납부하면 서비스를 1년 제공한다.
 */

public class ExpiryDateCalculatorTest {
    // 납부한 금액 기준으로 서비스 만료일을 계산하는 기능을 TDD로 구현
    @Test
    void pay_10000_won() {
        assertExpiryDate(LocalDate.of(2019, 3, 1), 10_000, LocalDate.of(2019, 4, 1));
        assertExpiryDate(LocalDate.of(2019, 5, 5), 10_000, LocalDate.of(2019, 6, 5));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = expiryDateCalculator.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

}
