package ru.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
// 1. если я правильно помню и понимаю, то здесь ищется элемент с id "passp-field-login"? Если это так, то нужно искать не по xpath, а по id и искать, с помощью этой аннотации
    // FindBy это тоже доступно. Дело в том, что xpath - надежный, но медленный способ. самый быстрый - id и class. поэтому если есть возможность привязаться к ним, то обязательно надо это сделать
  // единственное надо быть осторожным с автосгенерированными классами и айдишниками(привет ReactJS). на них полагаться, очевидно, нельзя. их можно распознать по их названиям: обычно 
    // там абракадабра. в данном случае тут вполне вменяемый айдишник, скорее всего не сгенерированный, можно привязаться к нему
     // 2. идентификатор "*" - ненадежный. чем более точный путь укажешь, тем надежнее будет тест
    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(text(), 'Войти')]/..")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwordField;

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPassword(String passwd) {
        passwordField.sendKeys(passwd);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
