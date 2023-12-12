package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.Pages;
import utilities.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilities.LinkAndText.*;
import static utilities.ReuseableMethods.*;

public class StepDefs {

    Pages pages;

    @Given("Launch browser")
    public void launch_browser() {
        Driver.getDriver();
    }

    @When("Navigate to {string}")
    public void navigate_to(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        ReuseableMethods.wait(2);
        js.executeScript("window.scrollBy(0, 500)");
        ReuseableMethods.wait(2);
    }

    @When("Click on login button")
    public void click_on_login_button() {
        pages = new Pages();
        pages.loginButton.click();
        ReuseableMethods.wait(5);
    }

    @And("Enter username as {string}")
    public void enterUsernameAs(String username) {
        pages = new Pages();
        pages.usernameTextBox01.click();
        ReuseableMethods.wait(1);

        pages.usernameTextBox.sendKeys(ConfigReader.getProperty(username), Keys.ENTER);
        ReuseableMethods.wait(2);
    }

    @And("Enter password as {string}")
    public void enterPasswordAs(String password) {
        pages = new Pages();
        pages.usernameTextBox.sendKeys(ConfigReader.getProperty(password), Keys.ENTER);
        ReuseableMethods.wait(2);
    }

    @When("Click cookies")
    public void click_cookies() {
        pages = new Pages();
        pages.cookiesButton.click();
        ReuseableMethods.wait(2);
    }

    //@xbot1 It sends tag and message
    @And("Enter {string}, {string} and send a message {string}")
    public void enterAndSendAMessage(String tag, String message, String numberofTweets) {
        int count = 0;
        int limit = Integer.parseInt(numberofTweets);

        do {
            pages = new Pages();

            String text = message + " \n\n" + tag + " \n";

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);
            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(ReuseableMethods.generateRandomNumber(5, 25));

            count++;
        } while (count < limit);

    }

        //@xbot2 it sends tag, link, and text from excel
    @And("Enter {string}, {string} and send the message {string} times")
    public void enterAndSendTheMessageTimes(String tag, String link, String numberofTweets) {
        int count = 0;
        int limit = Integer.parseInt(numberofTweets);
        String excelFileLink = "C:\\Users...";
        String xBot1SayilarLink = "C:\\Users...";


        do {
            pages = new Pages();
            ExcelUtilities excelUtilities = new ExcelUtilities(excelFileLink, "TurkishQuotes");

            int rnd = getRandomNumberAndRemove(xBot1SayilarLink);
            String message = excelUtilities.getCellData(rnd, 1);
            String text = message + " \n\n" + tag + " \n" + link;

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);
            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(ReuseableMethods.generateRandomNumber(5, 25));

            count++;
        } while (count < limit);

    }


    //@xbot3 it sends tag, link, and text from excel and add a mention from list
    @And("Enter {string}, {string} and add account send the message {string} times")
    public void enterAndAddAccountSendTheMessageTimes(String tag, String link, String numberofTweets) {
        int count = 0;
        int limit = Integer.parseInt(numberofTweets);
        String xHesaplarText="C:\\Users...";
        String excelFileLink= "C:\\Users...";
        String xBot1SayilarLink= "C:\\Users...";
        List<String> xHesaplarList= dosyaSatirlariniOku(xHesaplarText);

        do {
            pages = new Pages();
            ExcelUtilities excelUtilities = new ExcelUtilities(excelFileLink, "TurkishQuotes");

            int rnd = getRandomNumberAndRemove(xBot1SayilarLink);
            String message = excelUtilities.getCellData(rnd, 1);
            String xHesap= xHesaplarList.get(count);


            String text = message + " \n\n"+tag + " \n"+xHesap+ " \n"+link;

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);
            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(ReuseableMethods.generateRandomNumber(5, 25));
            removeSelectedWordFromFile(xHesaplarText,xHesaplarList.get(count));
            count++;
        } while (count < limit);


    }



    // @xbot4 It uses array for, tag, text and link and add an account from list

        @And("Use arrays for tag, text and link and send the message {string} times")
        public void useArraysForTagTextAndLinkAndSendTheMessageTimes(String numberofTweets) {


        int count = 0;
        int limit = Integer.parseInt(numberofTweets);
        String xHesaplarText="C:\\Users...";
        List<String> xHesaplarList= dosyaSatirlariniOku(xHesaplarText);

        do {
            pages = new Pages();
            
            //Identify accounts to mention
            String hesap1 = xHesaplarList.get(count);
            String hesap2 = xHesaplarList.get(count+1);
            String xHesap= hesap1+", "+hesap2;

            //If only one account is mentioned
            //String xHesap= xHesaplarList.get(count);

            String message = ReuseableMethods.selectRandomSentence(musterTextArr);
            String link = ReuseableMethods.selectRandomSentence(musterLinkArr);
            String tag = ReuseableMethods.selectRandomSentence(tagArr);

            String text = message + " \n\n"+tag + " \n"+xHesap+ " \n"+link;

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);
            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(30);
            removeSelectedWordFromFile(xHesaplarText,hesap1);
            ReuseableMethods.wait(1);
            removeSelectedWordFromFile(xHesaplarText,hesap2);
            count++;
        } while (count < limit);
    }



    @Then("close the page")
    public void closeThePage() {

        Driver.closeDriver();
    }

//xbot5 It sends a tag and a text from excel, makes a mention, add a photo or a viedo
    @And("Enter {string}, {string} and send the message {string} times and add a video")
    public void enterAndSendTheMessageTimesAndAddAVideo(String tag, String link, String limit) {

        int count = 0;
        int limit1 = Integer.parseInt(limit);
        String xHesaplarText="C:\\Users.....";
        String excelFileLink= "C:\\Users.....";
        String xBot1SayilarLink= "C:\\Users.....";
        String pictureLink = "C:\\Users.....";
        List<String> xHesaplarList= dosyaSatirlariniOku(xHesaplarText);

        do {
            pages = new Pages();
            ExcelUtilities excelUtilities = new ExcelUtilities(excelFileLink, "TurkishQuotes");

            int rnd = getRandomNumberAndRemove(xBot1SayilarLink);
            String message = excelUtilities.getCellData(rnd, 1);
            String xHesap= xHesaplarList.get(count);

            String text = message + " \n\n"+tag + " \n"+xHesap+" "+ link;

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);

            //   add picture
            pages.mediaButton.click();
            ReuseableMethods.wait(2);

            //use robot class method
            ReuseableMethods.robotMethod(pictureLink);

            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(30);
            removeSelectedWordFromFile(xHesaplarText,xHesaplarList.get(count));
            count++;
        } while (count < limit1);

    }


    //@xbot6 It sends a tag and a text from excel, makes mentions from sublist, add a photo or a viedo
    @And("Create sublist acording to {string},  enter {string}, {string} and send the message {string} times")
    public void createSublistAcordingToEnterAndSendTheMessageTimes(String numberOfElements, String tag, String link, String numberOfTweets) {
        int elementNumber= Integer.parseInt(numberOfElements);
        int limit= Integer.parseInt(numberOfTweets);
        int count = 0;
        String pictureLink = "C:\\Users.....";

        //burası sublisti hazırlıyor
        String xHesaplarText="C:\\Users.....";
        List<String> xHesaplarList= dosyaSatirlariniOku(xHesaplarText);
        List< List<String>> sublist= createSublists(xHesaplarList,elementNumber);

        do {

            pages = new Pages();
            String sublistStr= sublist.get(count).toString().replace("[","").replace("]","");
            String message = ReuseableMethods.selectRandomSentence(musterTextArr);


            String text = message+"\n"+tag + " \n"+sublistStr+ " \n"+link;

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);

            //   add picture
            pages.mediaButton.click();
            ReuseableMethods.wait(2);

            //use robot class method
            ReuseableMethods.robotMethod(pictureLink);

            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(30);
            removeSelectedWordFromFile(xHesaplarText,sublist.get(count).toString());
            count++;

        } while (count <=limit);
    }


   // @xbot7 It uses sublists for accounts and array for links, tag and text

    @And("Create sublist acording to {string}, use arrays for tag, text and link and send the message {string} times")
    public void createSublistAcordingToUseArraysForTagTextAndLinkAndSendTheMessageTimes(String numberOfElements, String numberOfTweets) {
        int elementNumber= Integer.parseInt(numberOfElements);
        int limit= Integer.parseInt(numberOfTweets);
        int count = 0;

        //burası sublisti hazırlıyor
        String xHesaplarText="C:\\Users.....";
        List<String> xHesaplarList= dosyaSatirlariniOku(xHesaplarText);
        List< List<String>> sublist= createSublists(xHesaplarList,elementNumber);

        do {

            pages = new Pages();
            String sublistStr= sublist.get(count).toString().replace("[","").replace("]","");
            String message = ReuseableMethods.selectRandomSentence(musterTextArr);
            String  link = ReuseableMethods.selectRandomSentence(musterLinkArr);
            String tag = ReuseableMethods.selectRandomSentence(tagArr);


            String text = message+"\n"+tag + " \n"+sublistStr+ " \n"+link;

            ReuseableMethods.wait(1);
            //write the tweet text yaz
            pages.messageTextBox.sendKeys(text);
            //send message
            pages.sendButton.click();
            ReuseableMethods.wait(30);
            removeSelectedWordFromFile(xHesaplarText,sublist.get(count).toString());
            count++;

        } while (count <=limit);


    }



}

