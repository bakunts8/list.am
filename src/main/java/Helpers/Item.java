package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Item {

    @FindBy(xpath = "//div[@id = 'contentr']//a//div[@class = 'l' or @class = 'l l3']")
    private WebElement description;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'at')]")
    private WebElement location;

    @FindBy(xpath = "//div[@id = 'contentr']//a/img")
    private WebElement itemFigure;

    @FindBy(xpath = "//div[@id = 'contentr']//a//div[@class = 'clabel']")
    private WebElement agencyLabel;

    @FindBy(xpath = "//div[@id='star']/preceding::a[1]")
    private WebElement lastItem;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'p')]")
    private WebElement price;

    public Item(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getDescription() {
        return description;
    }

    public WebElement getLocation() {
        return location;
    }

    public WebElement getItemFigure() {
        return itemFigure;
    }

    public WebElement getAgencyLabel() {
        return agencyLabel;
    }

    public WebElement getLastItem() {
        return lastItem;
    }

    public WebElement getPrice() {
        return price;
    }
}
