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
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3, 1)).payAmount(10_000).build()
                , LocalDate.of(2019, 4, 1));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 5)).payAmount(10_000).build()
                , LocalDate.of(2019, 6, 5));
    }


    // LocalDate 의 plusMonths 메소드가 알아서 +1달을 처리해준다. (예외 대상에 해당 안됨)
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 1, 31)).payAmount(10_000).build()
                , LocalDate.of(2019, 2, 28));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 31)).payAmount(10_000).build()
                , LocalDate.of(2019, 6, 30));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2020, 1, 31)).payAmount(10_000).build()
                , LocalDate.of(2020, 2, 29));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = expiryDateCalculator.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

}
