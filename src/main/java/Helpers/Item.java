package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Item {

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'p')]")
    private WebElement itemPrice;

    @FindBy(xpath = "//div[@id = 'contentr']//a//div[@class = 'l' or @class = 'l l3']")
    private WebElement itemDescription;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'at')]")
    private WebElement itemLocation;

    @FindBy(xpath = "//div[@id = 'contentr']//a/img")
    private WebElement itemFigure;

    @FindBy(xpath = "//div[@id = 'contentr']//a//div[@class = 'clabel']")
    private WebElement itemAgencyLabel;

    @FindBy(xpath = "//div[@id='star']/preceding::a[1]")
    private WebElement lastItem;

    public Item(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getItemPrice() {
        return itemPrice;
    }

    public WebElement getItemDescription() {
        return itemDescription;
    }

    public WebElement getItemLocation() {
        return itemLocation;
    }

    public WebElement getItemFigure() {
        return itemFigure;
    }

    public WebElement getItemAgencyLabel() {
        return itemAgencyLabel;
    }

    public WebElement getLastItem() {
        return lastItem;
    }
}
