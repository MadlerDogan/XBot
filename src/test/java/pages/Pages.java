package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Pages {

    public Pages(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[text()='Giriş yap']")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@class='css-1dbjc4n r-18u37iz r-16y2uox r-1wbh5a2 r-1wzrnnt r-1udh08x r-xd6kpl r-1pn2ns4 r-ttdzmv']")
    public WebElement usernameTextBox01;


    @FindBy(xpath = "//input[@class='r-30o5oe r-1niwhzg r-17gur6a r-1yadl64 r-deolkf r-homxoj r-poiln3 r-7cikom r-1ny4l3l r-t60dpp r-1dz5y72 r-fdjqy7 r-13qz1uu']")
    public WebElement usernameTextBox;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordTextBox;

    @FindBy(xpath = "(//span[@class='css-901oao css-16my406 r-poiln3 r-bcqeeo r-qvutc0'])[5]")
    public WebElement cookiesButton;

    @FindBy(xpath = "//div[@style= 'outline: none; user-select: text; white-space: pre-wrap; overflow-wrap: break-word;']")
    public WebElement messageTextBox;

    @FindBy(xpath = "//div[@class='css-18t94o4 css-1dbjc4n r-1niwhzg r-42olwf r-sdzlij r-1phboty r-rs99b7 r-5vhgbc r-mvpalk r-htfu76 r-2yi16 r-1qi8awa r-1ny4l3l r-o7ynqc r-6416eg r-lrvibr']")
    public WebElement mediaButton;



    @FindBy(xpath = "//div[@data-testid='tweetButtonInline']")
    public WebElement sendButton;

}
