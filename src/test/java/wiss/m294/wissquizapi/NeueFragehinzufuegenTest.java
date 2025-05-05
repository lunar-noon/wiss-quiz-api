package wiss.m294.wissquizapi;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NeueFragehinzufuegenTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Kein GUI
        driver = new ChromeDriver(options);
    }

    @Test
    public void testNeueFrageHinzufuegen() {
        driver.get("http://localhost:5173/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigiere zum Fragenformular (z. B. über Link "Questions")
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Questions")));
        driver.findElement(By.linkText("Questions")).click();

        // Fülle Formular aus
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("question")));
        driver.findElement(By.name("question")).sendKeys("Was ist 5 * 3?");
        driver.findElement(By.name("answer1")).sendKeys("15");
        driver.findElement(By.name("answer2")).sendKeys("10");
        driver.findElement(By.name("answer3")).sendKeys("20");
        driver.findElement(By.name("correctAnswer")).sendKeys("15");

        // Formular absenden (erster Button auf Seite)
        driver.findElement(By.cssSelector("button")).click();

        // OPTIONAL: Überprüfung – z. B. auf Erfolgsmeldung oder neue Frage in Liste
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Was ist 5 * 3?')]")));
        List<WebElement> results = driver.findElements(By.xpath("//div[contains(text(), 'Was ist 5 * 3?')]"));

        if (results.isEmpty()) {
            throw new AssertionError("Die neue Frage wurde nicht hinzugefügt oder nicht angezeigt.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
