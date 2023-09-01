package ListPages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Category extends BasePage {


    @FindBy(xpath = "//div[@id = 'menu']//span[contains(text(), 'All')]")
    private WebElement allCategoriesButton;

    @FindBy(xpath = "//div[contains(@class, 'ph')]")
    private WebElement currencyButton;

    @FindBy(xpath = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@class, 'me')]")
    private WebElement locationButton;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'p')]")
    private List<WebElement> itemPrice;

    @FindBy(xpath = "//div[@id = 'contentr']//a/div[contains(@class, 'at')]")
    private List<WebElement> items;

    @FindBy(xpath = "//div[@id ='contentr']//div[@class = 'clabel']")
    private List<WebElement> itemAgencyLabel;

    @FindBy(xpath = "//div[@id ='contentr']//a//div[@class = 'l' or @class = 'l l3']")
    private List<WebElement> itemDescription;

    private String endpoint = "/category/";
    private String newEndpoint = "";

    public Category(WebDriver driver) {
        super(driver);
    }

    public void switchToPage(String... option) {
        new Actions(driver).moveToElement(getAllCategoriesButton()).build().perform();

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
        getCurrencyButton().click();
        String currencyOptionSelector = "//div[contains(@class, 'l top')]/div[contains(text(), '%s')]";
        WebElement currencyOptionButton = getWait().waitingClickable(currencyOptionSelector.formatted(currency), 3);
        clickAndWaitToPageLoad(currencyOptionButton);
        setNewEndpoint();
    }

    public void selectPrice(Integer from, Integer to) {
        String priceSelector = "//input[contains(@id, 'idprice%s')]";
        WebElement priceFrom = getWait().waitingClickable(priceSelector.formatted("1"), 2);
        priceFrom.sendKeys(from.toString());
        WebElement priceTo = getWait().waitingClickable(priceSelector.formatted("2"), 2);
        priceTo.sendKeys(to.toString());
        WebElement priseSubmit = getWait().waitingClickable("//img[@id = 'gobtn']", 5);
        clickAndWaitToPageLoad(priseSubmit);
        setNewEndpoint();
    }

    public void selectLocation(String place) {
        getLocationButton().click();
        String locationOptionSelector = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@data-name, '%s')]";
        WebElement locationOption = getWait().waitingClickable(locationOptionSelector.formatted(place), 2);
        clickAndWaitToPageLoad(locationOption);
        setNewEndpoint();
    }

    public void filterThePage(String... options) {
        for (String option : options) {
            String sectionFilterSelector = "//div[@class = 'section']//label[contains(@for, 'id') and contains(text(), '%s')]";
            WebElement optionButton = getWait().waitingClickable(sectionFilterSelector.formatted(option), 3);
            clickAndWaitToPageLoad(optionButton);
        }
        setNewEndpoint();
    }

    private void setNewEndpoint() {
        newEndpoint = getDriver().getCurrentUrl().substring(getBaseURL().length() + getEndpoint().length());
    }

    public boolean checkPriceRange(int from, int to) {
        boolean isInRange = true;
        int price;

        for (WebElement element : getItemPrice()) {
            price = Integer.parseInt(element.getText().replaceAll("\\D", ""));
            if (price < from || price > to) {
                isInRange = false;
            }
        }
        return isInRange;
    }

    public boolean checkCurrency(String currency) {
        String type = switch (currency) {
            case "AMD" -> "֏";
            case "USD" -> "$";
            default -> "";
        };
        boolean isRightCurrency = true;
        for (WebElement element : getItemPrice()) {
            if (!element.getText().contains(type)) {
                isRightCurrency = false;
            }
        }
        return isRightCurrency;
    }

    public boolean checkLocation(String location) {
        boolean isInLocation = true;

        for (WebElement element : getItems()) {
            if (!element.getText().contains(location)) {
                isInLocation = false;
            }
        }
        return isInLocation;
    }

    public boolean checkAgencyFilter() {

        boolean isOfferedFromAgency = true;

        for (WebElement element : getItemAgencyLabel()) {
            if (!element.getText().equals("Agency")) {
                isOfferedFromAgency = false;
            }
        }
        return isOfferedFromAgency;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    protected List<WebElement> getItemPrice() {
        return itemPrice;
    }

    protected List<WebElement> getItems() {
        return items;
    }

    protected List<WebElement> getItemAgencyLabel() {
        return itemAgencyLabel;
    }

    protected List<WebElement> getItemDescription() {
        return itemDescription;
    }

    public boolean isEmptyItems() {
        return items.isEmpty();
    }

    public WebElement getAllCategoriesButton() {
        return allCategoriesButton;
    }

    public WebElement getCurrencyButton() {
        return currencyButton;
    }

    public WebElement getLocationButton() {
        return locationButton;
    }

    private void clickAndWaitToPageLoad(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            element.submit();
        }
        getWait().waitingClickable("//div[@id='star']/preceding::a[1]", 4);
    }
}
