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
    public void settings(){

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
    public void test0() {

        loginPage.inputLogin(ConfigureProperties.getProperty("login"));

        loginPage.clickLoginButton();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loginPage.inputPassword(ConfigureProperties.getProperty("password"));

        loginPage.clickLoginButton();

        String user = userPage.getUserName();

        Assert.assertEquals(ConfigureProperties.getProperty("login"), user);

    }

    @Test
    public void test1() {

        userPage.makeMail();
    }
    
    @Test
            public void test2() {
        userPage.addTemplate();

    }
    
    @Test
    public void test3() {

        userPage.inputAdress(ConfigureProperties.getProperty("adress"));

    }
    
    @Test
    public void test4() {

        userPage.inputSubject(ConfigureProperties.getProperty("subject"));

    }
    
    @Test
    public void test5() {        

        userPage.inputText(ConfigureProperties.getProperty("text"));

    }

    @Test
    public void test6() {

        userPage.clickSendMail();

    }

    @Test
    public void test7() {

        userPage.clickFrustratingWindow();

    }

    @Test
    public void test8() {

        userPage.makeMail();

    }

    @Test
    public void test9() {

        userPage.clickRecentTemplate();

    }

    @Test
    public void test10() {

        String adress = userPage.getAdress();

        Assert.assertEquals(ConfigureProperties.getProperty("adress"), adress);

    }
    
    @Test
    public void test11() {

        String subject = userPage.getSubject();

        Assert.assertEquals(ConfigureProperties.getProperty("subject"), subject);

    }
    
    @Test
    public void test12() {        

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
