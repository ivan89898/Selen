import com.codeborne.selenide.Condition;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
    @Test
    void ShouldTestV1() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span/span/span/span[1]/input")).sendKeys(Keys.CONTROL + "a");
        $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span/span/span/span[1]/input")).sendKeys(Keys.DELETE);
        $(By.xpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span/span/span/span[1]/input")).setValue("25082022");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Забронировать']")).click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));

    }
}
