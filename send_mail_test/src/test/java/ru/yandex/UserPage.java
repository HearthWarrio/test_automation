package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {
    public WebDriver driver;
    public UserPage(WebDriver driver){

        PageFactory.initElements(driver, this);

        this.driver = driver;
    }
    //1. избавиться от таких путей и навсегда забыть о них. по такому пути невозможно найти человеку элемент, если вдруг что-то сломается. это очень ненадежный вариант и с точки
    //зрения поддержки плохой. любой xpath можно описать по-другому, словами. 
    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[2]/div/div/header/div/div[5]/div[2]/a[1]/div/img")
    private WebElement userMenu;

    //переход в папку отправленные
    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[3]/div[2]/div[3]/div/div[1]/div[1]/a[2]/span")
    private WebElement sendedMail;

        public void openSendedMail() {

            sendedMail.click();

        }

    // написать письмо
    //я не стал переименовывать методы, функции и ссылки другие
     @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[3]/div[2]/div[2]/div/div/a/span")
     private WebElement createTemplate;

        public void addTemplate(){

            createTemplate.click();

        }

    //Для этого и подобного решения, мой комментарий: я прочитал о том, что не рекомендуется использовать прямые ссылки
    //по xpath. но, к сожалению, я не везде смог найти id/class/title. Поэтому, там, где их не нашел и использую
    //полные ссылки.
    // скину статью, ты посмотришь - можно искать не только по ним, а практически по любому элемента дерева, плюс можно искать родителя/потомка относительно какого- элемента, что удобно.
    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[9]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/div[1]/div/div[1]/div/div/div/div")
    private WebElement adressField;

    public void inputAdress(String adress){

            adressField.sendKeys(adress);

        }

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[9]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/div[1]/div/div[3]/form/div/div/input")
    private WebElement subjectName;

        public void inputSubject(String subject){

            subjectName.sendKeys(subject);
        }

    @FindBy(xpath = "//*[@id=\"cke_101_contents\"]/div")
    private WebElement mailText;

        public void inputText(String text){

            mailText.sendKeys(text);

        }

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[9]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div[1]/button")
    private WebElement sendMail;

        public void clickSendMail(){

            sendMail.click();

        }

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[9]/div/div[1]/div[2]/a")
    private WebElement frustratingWindow;

        public void clickFrustratingWindow(){

            frustratingWindow.click();

        }

    //я не стал переименовывать методы, функции и ссылки другие. это отвечает за котрытие сообщения (отосланного)
    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[3]/div[3]/div[2]/div[5]/div[1]/div/div/div[2]/div/div[1]/div/div/div/a/div/span[2]/div/span/span[2]/span")
    private WebElement recentTemplate;

        public void clickRecentTemplate() {

            recentTemplate.click();

        }

       public String getAdress() {

            String adressName = adressField.getText();

            return adressName;

       }

       public String getSubject() {

            String subjectText = subjectName.getText();

            return subjectText;

       }

       public  String getMailText() {

            String mailWords = mailText.getText();

            return mailWords;

       }

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[2]/div/div/header/div/div[5]/div[2]/div/ul/ul/li[5]/a")
    private WebElement logoutButtton;

        public String getUserName() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        // про xpath уже писал. второе: в метод лучше всегда стараться передать переменную с хорошим, понятным названием. потому что из этого вызова совершенно неясно, видимости какого элемента мы все-таки ожидаем?
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[2]/div/div/header/div/div[5]/div[2]/a[1]/span[1]")));

        String userName = userMenu.getText();

        return userName;

        }

        public void entryMenu() {

        userMenu.click();

        }

        public void userLogout() {

        logoutButtton.click();

        }

    }

