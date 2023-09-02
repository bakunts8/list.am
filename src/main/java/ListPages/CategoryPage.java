package ListPages;

import Helpers.Item;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage {


    @FindBy(xpath = "//div[@id = 'menu']//span[contains(text(), 'All')]")
    private WebElement allCategoriesButton;

    @FindBy(xpath = "//div[contains(@class, 'ph')]")
    private WebElement currencyButton;

    @FindBy(xpath = "//div[contains(text(), 'Location')]//..//following-sibling::div[contains(@class, 'me')]")
    private WebElement locationButton;

    @FindBy(xpath = "//div[@id = 'contentr']//a")
    private List<WebElement> elements;

    private List<Item> items;
    private String endpoint = "/category/";
    private String newEndpoint = "";


    public CategoryPage(WebDriver driver) {
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

    public boolean checkPriceRange(int from, int to) {
        boolean isInRange = true;
        int price;

        for (Item item : getItems()) {
            price = Integer.parseInt(item.getItemPrice().getText().replaceAll("\\D", ""));
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
        for (Item item : getItems()) {
            if (!item.getItemPrice().getText().contains(type)) {
                isRightCurrency = false;
            }
        }
        return isRightCurrency;
    }

    public boolean checkLocation(String location) {
        boolean isInLocation = true;

        for (Item item : getItems()) {
            if (!item.getItemLocation().getText().contains(location)) {
                isInLocation = false;
            }
        }
        return isInLocation;
    }

    public boolean checkAgencyFilter() {
        boolean isOfferedFromAgency = true;

        for (Item item : getItems()) {
            if (!item.getItemAgencyLabel().getText().equals("Agency")) {
                isOfferedFromAgency = false;
            }
        }
        return isOfferedFromAgency;
    }

    public boolean isClickableLatestItem() {
        boolean isClickable = true;

        try {
            getWait().waitingClickable(getElements().get(getElements().size() - 1), 4);
        } catch (TimeoutException e) {
            isClickable = false;
        }
        return isClickable;
    }

    public List<Item> getItems() {
        items = new ArrayList<>();
        try {
            for (WebElement el : getElements()) {
                items.add(new Item(getDriver()));
            }
        } catch (TimeoutException e) {
            throw new NullPointerException("The page has no items");
        }
        return items;
    }

    private void clickAndWaitToPageLoad(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            element.submit();
        }
        getWait().waitingClickable("//div[@id='star']/preceding::a[1]", 4);
    }

    @Override
    public String getEndpoint() {
        return endpoint;
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

    public List<WebElement> getElements() {
        return elements;
    }

    private void setNewEndpoint() {
        newEndpoint = getDriver().getCurrentUrl().substring(getBaseURL().length() + getEndpoint().length());
    }
}
