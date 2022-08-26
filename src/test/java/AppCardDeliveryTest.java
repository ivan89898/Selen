import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(4);

    {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestV1() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Забронировать']")).click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestV2() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue("ежин Иванов");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Забронировать']")).click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }
}
