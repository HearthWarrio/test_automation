package ru.yandex;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {
    public final Logger logger = LogManager.getLogger(UserPage.class);
    public WebDriver driver;
    public UserPage(WebDriver driver){

        PageFactory.initElements(driver, this);

        this.driver = driver;
    }
    @FindBy(xpath = "//*[contains(@class 'user-pic_image')]")
    private WebElement userMenu;

    // папка черновики
    @FindBy(xpath = "//a[text()='Черновики']")
    private WebElement addMail;

        @Step("Переход в черновики")
        public void makeMail () {

        logger.info("Переход в черновики");
        addMail.click();
            Assert.assertEquals(driver.getTitle(), "Создать шаблон");

    }


    // сделать шаблон (октрыть окно)
    @FindBy(xpath = "//*[contains(@text 'Создать шаблон')]")
    private WebElement createTemplate;

    @Step("Открыть создание шаблона")
    public void addTemplate() {

        logger.info("Откно формирования шаблона открыто");
        createTemplate.click();
        WebElement a = driver.findElement(By.xpath("//[@class 'composeHeader-Title']"));
        Assert.assertEquals(a.getClass(), "Создать шаблон");

    }

    @FindBy(xpath = "//*[contains(@class 'MultipleAddressesDesktop-Field ComposeYabblesField')]")
    private WebElement adressField;

    @Step("Ввод адресата")
    public void inputAdress(String adress){


        logger.info("Ввод адресата");
        adressField.sendKeys(adress);

    }

    @FindBy(xpath = "//*[contains(@class 'composeTextField ComposeSubject-TextField')]")
    private WebElement subjectName;

    @Step("Ввод темы черновика")
    public void inputSubject(String subject){

        subjectName.sendKeys(subject);
        logger.debug("Ввод темы");

    }

    @FindBy(xpath = "//div[contains(@class, 'cke_contents cke_reset')]/child::div[text()='Напишите что-нибудь')]")
    private WebElement mailText;

    @Step("Ввод текста черновика")
    public void inputText(String text){

        logger.info("Ввод текста");
        mailText.sendKeys(text);

    }

    @FindBy(xpath = "//span[text()='Отправить']/parent::button")
    private WebElement sendMail;

    @Step("Нажать создания шаблона")
    public void clickSendMail(){

        logger.info("Посылка письма");
        sendMail.click();

    }

    @FindBy(xpath = "//div[contains(@class, 'ComposeDoneScreen-Actions')]/descendant::a[text()='Вернуться во Входящие')]")
    private WebElement frustratingWindow;

    @Step("Закрыть всплывающее окно")
    public void clickFrustratingWindow(){

        logger.info("Закрыть всплывающее окно");
        frustratingWindow.click();

    }

    @FindBy(xpath = "//div[contains(@class, 'mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline)]")
    private WebElement recentTemplate;

    @Step("Открыть созданный черновик")
    public void clickRecentTemplate() {

        logger.info("Открыть созданный черновик");
        recentTemplate.click();

    }

    @Step("Проверка корректности адресата")
    public String isAdressCorrect() {

        String adressName = null;

        if (adressName.equals(adressField.getText())) {

            logger.info("Адресат" + adressName + "верен");
            return adressField.getText();

        } else {

            logger.error("Адресат" + adressName + "неверен" );
            return adressName;
        }

    }

    @Step("Проверка корректности темы черновика")
    public String isSubjectCorrect() {

        String subjectText = null;

        if (subjectText.equals(subjectName.getText())) {

            logger.info("Тема  письма " + subjectName + "верна");
            return subjectName.getText();

        } else {

            logger.error("Тема письма" + subjectName + "не верна");
            return subjectText;
        }

    }

    @Step("Проверка корректности текста письма")
    public  String isMailTextCorrect() {

        String mailWords = null;

        if (mailWords.equals(mailText.getText())) {

            logger.info("Текст письма верен");
            return mailText.getText();

        } else {

            logger.error("Текст письма не верен");
            return mailWords;

        }

    }

    @FindBy(xpath = "//a[text()=’Выйти из сервисов Яндекса’]")
    private WebElement logoutButtton;

    @Step("Проверка имени пользователя")
    public String istUserNameCorrect() {

        logger.info("Чекаем юзернейм");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class 'user-pic_image')]")));

        String userName = userMenu.getText();

        return userName;

    }

    @Step("Нажатие на меню входа-выхода")
    public void entryMenu() {

        logger.info("Нажимаем на меню входа-выхода");

        userMenu.click();

    }

    @Step("Выход")
    public void userLogout() {

        logger.info("Выход");

        logoutButtton.click();

    }

}
