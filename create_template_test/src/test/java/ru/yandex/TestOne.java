package ru.yandex;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestOne {

    public static LoginPage loginPage;
    public static UserPage userPage;
    public static WebDriver driver;

    @BeforeClass
    public static void settings(){

        System.setProperty("webdriver.chrome.driver", "/autotest/driver/chromedriver.exe");

        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);

        userPage = new UserPage(driver);

        driver.manage().window().maximize();

        driver.get("https://passport.yandex.ru/auth/list?origin=home_desktop_ru&retpath=https%3A%2F%2Fmail.yandex.ru%2F&backpath=https%3A%2F%2Fyandex.ru");

        String title = driver.getTitle();

        Assert.assertTrue(title.equals("Авторизация"));
    }

    @Test
    public void test() {

        loginPage.inputLogin(ConfigureProperties.getProperty("login"));

        loginPage.clickLoginButton();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loginPage.inputPassword(ConfigureProperties.getProperty("password"));

        loginPage.clickLoginButton();

        String user = userPage.getUserName();

        Assert.assertEquals(ConfigureProperties.getProperty("login"), user);

        userPage.makeMail();

        userPage.addTemplate();

        userPage.inputAdress(ConfigureProperties.getProperty("adress"));

        userPage.inputSubject(ConfigureProperties.getProperty("subject"));

        userPage.inputText(ConfigureProperties.getProperty("text"));

        userPage.clickSendMail();

        userPage.clickFrustratingWindow();

        userPage.makeMail();

        userPage.clickRecentTemplate();

        String adress = userPage.getAdress();

        Assert.assertEquals(ConfigureProperties.getProperty("adress"), adress);

        String subject = userPage.getSubject();

        Assert.assertEquals(ConfigureProperties.getProperty("subject"), subject);

        String text = userPage.getMailText();

        Assert.assertEquals(ConfigureProperties.getProperty("text"), text);

    }

    @AfterClass
    public static void shutDown() {

        userPage.entryMenu();

        userPage.userLogout();

        driver.quit();
    }
}
