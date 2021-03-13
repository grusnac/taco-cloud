package com.github.grusnac.taco.cloud.acceptance;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageSteps {

    private static HtmlUnitDriver browser;
    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setUp() {
        browser = new HtmlUnitDriver();
        browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void teardown() {
        browser.quit();
    }

    @Test
    public void testHomePage() {
        String homePage = "http://localhost:" + port;
        browser.get(homePage);

        String titleText = browser.getTitle();
        assertEquals("Taco Cloud", titleText);

        String h1Text = browser.findElementByTagName("h1").getText();
        assertEquals("Welcome to Taco Cloud", h1Text);

        String imgSrc = browser.findElementByTagName("img").getAttribute("src");
        assertEquals(homePage + "/images/TacoCloud.png", imgSrc);
    }

}
