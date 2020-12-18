package com.example.demo.selenium;

import com.example.demo.selenium.pages.Site;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;

public class SiteTest {

    private static RemoteWebDriver driver;
    private static final Logger LOGGER = Logger.getGlobal();

    @BeforeClass
    public static void initialise() {
        LOGGER.setLevel(Level.ALL);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/selenium/webdrivers/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));

        // timeouts
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        LOGGER.warning("Closing webdriver instance!");

        driver.quit();

        LOGGER.info("!!! Webdriver closed successfully !!!");
    }

    @Before
    public void setup() {
        LOGGER.warning("Connecting to The Demo Site....");
        driver.get(Site.URL);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void createTaskList() throws InterruptedException {
        Site webpage = PageFactory.initElements(driver, Site.class);

        webpage.createTL();
        driver.switchTo().alert().sendKeys("Pandamonium");
        driver.switchTo().alert().accept();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Panda')]")));
    }

    @Test
    public void createTask() throws InterruptedException {
        Site webpage = PageFactory.initElements(driver, Site.class);

        webpage.createT();
        driver.switchTo().alert().sendKeys("Panda");
        driver.switchTo().alert().accept();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Panda')]")));
    }

    @Test
    public void updateTaskList() throws InterruptedException {
        Site webpage = PageFactory.initElements(driver, Site.class);

        webpage.updateTL();
        driver.switchTo().alert().sendKeys("Dog");
        driver.switchTo().alert().accept();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Dog')]")));
    }

    @Test
    public void updateTask() throws InterruptedException {
        Site webpage = PageFactory.initElements(driver, Site.class);

        webpage.updateT();
        driver.switchTo().alert().sendKeys("Cat");
        driver.switchTo().alert().accept();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Cat')]")));
    }

    @Test
    public void deleteTaskList() throws InterruptedException {
        Site webpage = PageFactory.initElements(driver, Site.class);

        webpage.deleteTL();
        driver.navigate().refresh();
        assertFalse("This will succeed.", driver.getPageSource().contains("Stationary"));
    }

    @Test
    public void deleteTask() throws InterruptedException {
        Site webpage = PageFactory.initElements(driver, Site.class);

        webpage.deleteT();
        driver.navigate().refresh();
        assertFalse("This will succeed.", driver.getPageSource().contains("Eggs"));
    }
}