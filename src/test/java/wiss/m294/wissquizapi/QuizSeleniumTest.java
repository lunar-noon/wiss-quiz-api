package wiss.m294.wissquizapi;

import java.time.Duration;

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
        // Automatischer Setup vom ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920x1080");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testHomeLoading() {
        // Testseite öffnen
        driver.get("http://localhost:5173/");

    }

    @Test
    public void testClickOnButton() {
        // Testseite öffnen
        driver.get("http://localhost:5173/");

        // Warten bis der Button sichtbar ist
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id='root']/div/div[2]/button")
        ));

        // Button klicken
        driver.findElement(By.xpath("//*[@id='root']/div/div[2]/button")).click();

        // Bestätigung
        System.out.println("Button wurde geklickt.");
    }

    @Test
    public void testClickOnRules() {
        // Testseite öffnen
        driver.get("http://localhost:5173/rules");

        // Warten bis der Button sichtbar ist
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id='root']/div/div[2]/button")
        ));

        // Button klicken
        driver.findElement(By.xpath("//*[@id='root']/div/div[2]/button")).click();

        // Bestätigung
        System.out.println("Button wurde geklickt.");
    }

    @Test
    public void testStartingWhenSelectingCategory() {
        // Testseite öffnen
        driver.get("http://localhost:5173/quiz");

        // Warten bis der Button sichtbar ist
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id='root']/div/div[2]/button")
        ));

        // Button klicken
        driver.findElement(By.xpath("//*[@id='root']/div/div[2]/button")).click();

        // Bestätigung
        System.out.println("Button wurde geklickt.");
    }

    @Test
    public void testQuizAnswerSelect() {
        // Testseite öffnen
        driver.get("http://localhost:5173/quiz");

        // Warten bis der Button sichtbar ist
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id='root']/div/div[2]/button")
        ));

        // Button klicken
        driver.findElement(By.xpath("//*[@id='root']/div/div[2]/button")).click();

        // Bestätigung
        System.out.println("Button wurde geklickt.");
    }

    @Test
    public void testError404Site() {
        // Testseite öffnen
        driver.get("http://localhost:5173/erro");


    }
}
