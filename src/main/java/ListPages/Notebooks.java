package ListPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Notebooks extends Categories {

    @FindBy(xpath = "(//div[@id = 'contentr']//a/div[contains(@class, 'p')])[50]")
    private WebElement pageLoadCheckElement;

    @FindBy(xpath = "//div[contains(@class, 'ph')]")
    private WebElement currencyButton;

    @FindBy(xpath = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@class, 'me')]")
    private WebElement locationButton;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'p')]")
    private List<WebElement> notebooksPrice;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'at')]")
    private List<WebElement> notebooksGeneralInfo;

    private String endpoint = "116";
    private final String startUrl = "https://www.list.am/category/";

    public Notebooks(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getEndpoint() {
        return super.getEndpoint() + endpoint;
    }

    public WebElement getPageLoadCheckElement() {
        return pageLoadCheckElement;
    }

    public Notebooks selectCurrency(String currency) {
        currencyButton.click();
        String currencyOptionSelector = "//div[contains(@class, 'l top')]/div[contains(text(), '%s')]";
        WebElement currencyOptionButton = getWait().waitingClickable(currencyOptionSelector.formatted(currency), 3);
        currencyOptionButton.click();
        String endUrl = getDriver().getCurrentUrl();
        endpoint = endUrl.substring(startUrl.length());
        return this;
    }

    public Notebooks selectPrice(String from, String to) {
        String priceSelector = "//input[contains(@id, 'idprice%s')]";
        WebElement priceFrom = getWait().waitingClickable(priceSelector.formatted("1"), 2);
        priceFrom.sendKeys(from);
        WebElement priceTo = getWait().waitingClickable(priceSelector.formatted("2"), 2);
        priceTo.sendKeys(to);
        WebElement priseSubmit = getWait().waitingClickable("//img[@id = 'gobtn']", 5);
        priseSubmit.submit();
        return this;
    }

    public Notebooks selectLocation(String place) {
        locationButton.click();
        String locationOptionSelector = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@data-name, '%s')]";
        WebElement locationOption = getWait().waitingClickable(locationOptionSelector.formatted(place), 2);
        locationOption.click();
        String endUrl = getDriver().getCurrentUrl();
        endpoint = endUrl.substring(startUrl.length());
        return this;
    }

    public boolean checkPriceRange() {
        boolean isInRange = true;
        int price;

        for (WebElement element : notebooksPrice) {
            price = Integer.parseInt(element.getText().replaceAll("\\D", ""));
            int priceFrom = 200000;
            int priceTo = 500000;
            if (price < priceFrom || price > priceTo) {
                isInRange = false;
                System.out.println("price");
            }
        }
        return isInRange;
    }

    public boolean checkCurrency() {
        String currency = "֏";
        boolean isRightCurrency = true;

        for (WebElement element : notebooksPrice) {
            if (!element.getText().contains(currency)) {
                isRightCurrency = false;
                System.out.println("currency");
            }
        }
        return isRightCurrency;
    }

    public boolean checkLocation() {
        String location = "Kentron";
        boolean isInLocation = true;

        for (WebElement element : notebooksGeneralInfo) {
            if (!element.getText().contains(location)) {
                isInLocation = false;
                System.out.println("location");
            }
        }
        return isInLocation;
    }
}
