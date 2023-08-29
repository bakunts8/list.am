package ListPages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Categories extends BasePage {

    @FindBy(xpath = "(//div[@id = 'contentr']//a//div[contains(@class, 'at')])[10]")
    private WebElement pageLoadCheckElement;

    @FindBy(xpath = "//div[@id = 'menu']//span[contains(text(), 'All')]")
    private WebElement allCategoriesButton;

    @FindBy(xpath = "//div[contains(@class, 'ph')]")
    private WebElement currencyButton;

    @FindBy(xpath = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@class, 'me')]")
    private WebElement locationButton;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'p')]")
    private List<WebElement> ItemPrice;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'at')]")
    private List<WebElement> itemGeneralInfo;

    @FindBy(xpath = "//div[@id ='contentr']//div[@class = 'clabel']")
    private List<WebElement> itemAgencyLabel;

    @FindBy(xpath = "//div[@id ='contentr']//a//div[@class = 'l' or @class = 'l l3']")
    private List<WebElement> itemDescription;

    private String endpoint = "/category/";
    private String newEndpoint = "";

    public Categories(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    public void setNewEndpoint(String newEndpoint) {
        this.newEndpoint = newEndpoint;
    }

    public WebElement getPageLoadCheckElement() {
        return pageLoadCheckElement;
    }

    public void switchToPage(String... option) {
        new Actions(driver).moveToElement(allCategoriesButton).build().perform();

        String allCategoriesOptionButtonSelector = "//div[@id = 'menu']//div[@data-c]//span/a[contains(text(), '%s')]";
        WebElement allCategoriesOptionButton;
        if (option.length < 2) {
            allCategoriesOptionButton = getWait().waitingClickable(allCategoriesOptionButtonSelector.formatted(option[0]), 3);
            allCategoriesOptionButton.click();
        }
        allCategoriesOptionButton = getWait().waitingClickable(allCategoriesOptionButtonSelector.formatted(option[0]), 3);
        new Actions(driver).moveToElement(allCategoriesOptionButton).build().perform();
        String allCategoriesPageButtonSelector = "//div[@id = 'menu']//div[@data-c = '0']//a[contains(text(), '%s')]";
        WebElement allCategoriesPageButton = getWait().waitingClickable(allCategoriesPageButtonSelector.formatted(option[1]), 2);
        allCategoriesPageButton.click();
    }

    public void switchToPageFromHeadMenu(String... option) {
        String headMenuOptionButtonSelector = "//div[@id = 'menu']//div[@data-c]/a[contains(text(), '%s')]";
        WebElement headMenuOptionButton;
        if (option.length < 2) {
            headMenuOptionButton = getWait().waitingClickable(headMenuOptionButtonSelector.formatted(option[0]), 3);
            headMenuOptionButton.click();
        }
        headMenuOptionButton = getWait().waitingClickable(headMenuOptionButtonSelector.formatted(option[0]), 3);
        new Actions(driver).moveToElement(headMenuOptionButton).build().perform();
        String headMenuPageButtonSelector = "//div[@id = 'menu']//a[contains(text(), '%s')]";
        WebElement headMenuPageButton = getWait().waitingClickable(headMenuPageButtonSelector.formatted(option[1]), 2);
        headMenuPageButton.click();
    }

    public void selectCurrency(String currency) {
        currencyButton.click();
        String currencyOptionSelector = "//div[contains(@class, 'l top')]/div[contains(text(), '%s')]";
        WebElement currencyOptionButton = getWait().waitingClickable(currencyOptionSelector.formatted(currency), 3);
        clickAndWait(currencyOptionButton);
        newEndpoint = getDriver().getCurrentUrl().substring(getBaseURL().length() + getEndpoint().length());
        setNewEndpoint(newEndpoint);
        this.get();
    }

    public void selectPrice(String from, String to) {
        String priceSelector = "//input[contains(@id, 'idprice%s')]";
        WebElement priceFrom = getWait().waitingClickable(priceSelector.formatted("1"), 2);
        priceFrom.sendKeys(from);
        WebElement priceTo = getWait().waitingClickable(priceSelector.formatted("2"), 2);
        priceTo.sendKeys(to);
        WebElement priseSubmit = getWait().waitingClickable("//img[@id = 'gobtn']", 5);
        clickAndWait(priseSubmit);
        newEndpoint = getDriver().getCurrentUrl().substring(getBaseURL().length() + getEndpoint().length());
        setNewEndpoint(newEndpoint);
        this.get();
    }

    public void selectLocation(String place) {
        locationButton.click();
        String locationOptionSelector = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@data-name, '%s')]";
        WebElement locationOption = getWait().waitingClickable(locationOptionSelector.formatted(place), 2);
        clickAndWait(locationOption);
        newEndpoint = getDriver().getCurrentUrl().substring(getBaseURL().length() + getEndpoint().length());
        setNewEndpoint(newEndpoint);
        this.get();
    }

    public void filterThePage(String... options) {
        for (String option : options) {
            String sectionFilterSelector = "//div[@class = 'section']//label[contains(@for, 'id') and contains(text(), '%s')]";
            WebElement optionButton = getWait().waitingClickable(sectionFilterSelector.formatted(option), 3);
            clickAndWait(optionButton);
        }
        newEndpoint = getDriver().getCurrentUrl().substring(getBaseURL().length() + getEndpoint().length());
        setNewEndpoint(newEndpoint);
        this.get();
    }

    public List<WebElement> getItemPrice() {
        return ItemPrice;
    }

    public List<WebElement> getItemGeneralInfo() {
        return itemGeneralInfo;
    }

    public List<WebElement> getItemAgencyLabel() {
        return itemAgencyLabel;
    }

    public List<WebElement> getItemDescription() {
        return itemDescription;
    }

    private void clickAndWait(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            element.submit();
        }
        getWait().waitingVisibility("(//div[@id = 'contentr']//a//div[contains(@class, 'at')])[10]", 4);
    }
}
