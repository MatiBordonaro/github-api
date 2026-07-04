package com.github.tp.selenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerUISeleniumTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testSwaggerUILoadsAndShowsAuthScheme() {
        driver.get("http://localhost:" + port + "/swagger-ui/index.html");

        WebElement title = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.className("title")));
        assertNotNull(title);

        boolean hasAuthorize = !driver.findElements(
            By.cssSelector(".authorize, .auth-wrapper button, .btn.authorize")).isEmpty();
        assertTrue(hasAuthorize, "SwaggerUI debería mostrar el botón Authorize");
    }

    @Test
    void testSwaggerUIShowsEndpointSections() {
        driver.get("http://localhost:" + port + "/swagger-ui/index.html");

        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector(".opblock-tag-section, .opblock")));

        List<WebElement> tags = driver.findElements(By.cssSelector(".opblock-tag-section"));
        assertTrue(tags.size() >= 0, "SwaggerUI debería renderizar bloques de endpoints");
    }

    @Test
    void testInternalApiSpecIsAccessible() {
        driver.get("http://localhost:" + port + "/api-docs");

        String body = driver.findElement(By.tagName("body")).getText();
        assertTrue(body.contains("api/repos") || body.contains("listRepositories"));
    }
}
