package wiss.m294.wissquizapi;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QuizSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Kein GUI

        driver = new ChromeDriver(options);
    }

    @Test
    public void testHomeLoading() {
        driver.get("http://localhost:5173/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/h2")));

        String headerText = driver.findElement(By.xpath("/html/body/div/div/div[2]/h2")).getText();
    
        if (!headerText.equals("Homepage")) {
            throw new AssertionError("Der Text 'Homepage' wurde nicht angezeigt. Gefundener Text: " + headerText);
        }
    }

    @Test
    public void testClickOnButton() {
        driver.get("http://localhost:5173/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/button")));

        driver.findElement(By.xpath("/html/body/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[2]/button")).click();

        String buttonText = driver.findElement(By.xpath("/html/body/div/div/div[2]/button")).getText();

        if (!buttonText.equals("3")) {
            throw new AssertionError("Der Knopf wurde nicht gedrückt. Gefundener Text: " + buttonText);
        }
    }

    @Test
    public void testClickOnRules() {
        driver.get("http://localhost:5173/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/ul/li[3]/a")));

        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[3]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/h2")));
        String rulesText = driver.findElement(By.xpath("/html/body/div/div/div[2]/h2")).getText();
    
        if (!rulesText.equals("Rules")) {
            throw new AssertionError("Die Regeln wurden nicht geöffnet. Gefundener Text: " + rulesText);
        }
    }

    @Test
    public void testStartingWhenSelectingCategory() {
        driver.get("http://localhost:5173/quiz");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='test']")));

        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/button[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]")));
        String quizText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]")).getText();
    
        if (!quizText.equals("Score: 0 von 3")) {
            throw new AssertionError("Der Quiz konnte nicht geladen werden. Gefundener Text: " + quizText);
        }
    }

    @Test
    public void testQuizAnswerSelect() {
        driver.get("http://localhost:5173/quiz");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='test']")));

        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/button[1]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button")).click();
    
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button")).click();
    
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")));
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[3]/button")));
        String buttonText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button")).getText();
    
        if (!buttonText.equals("View Score")) {
            throw new AssertionError("Der Quiz konnte nicht gelöst werden. Gefundener Text: " + buttonText);
        }
    }

    @Test
    public void testError404Site() {
        driver.get("http://localhost:5173/erro");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div")));

        String erroText1 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/h1[1]")).getText();
        String erroText2 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/h1[2]")).getText();

        String erroText = erroText1 + " " + erroText2;
    
        if (!erroText.equals("404 Error Page Not Found")) {
            throw new AssertionError("Der Text '404 Error Page Not Found' wurde nicht angezeigt. Gefundener Text: " + erroText);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Browser schließen
        }
    }
}
