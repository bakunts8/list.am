package ListPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Categories extends BasePage {

    @FindBy(xpath = "//div[@class = 'dlf']")
    private WebElement pageLoadCheckElement;

    @FindBy(xpath = "//div[@id = 'menu']//span[contains(text(), 'All')]")
    private WebElement allCategoriesButton;

    private final String endpoint = "/category/";

    public Categories(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    public WebElement getPageLoadCheckElement() {
        return pageLoadCheckElement;
    }

    public <T extends Categories> Categories switchToPage(String... option) {
        new Actions(driver).moveToElement(allCategoriesButton).build().perform();

        String allCategoriesOptionButtonSelector = "//div[@id = 'menu']//div[@data-c]//span/a[contains(text(), '%s')]";
        WebElement allCategoriesOptionButton;
        if (option.length < 2) {
            allCategoriesOptionButton = getWait().waitingClickable(allCategoriesOptionButtonSelector.formatted(option[0]), 3);
            allCategoriesOptionButton.click();
            return this;
        }
        allCategoriesOptionButton = getWait().waitingClickable(allCategoriesOptionButtonSelector.formatted(option[0]), 3);
        new Actions(driver).moveToElement(allCategoriesOptionButton).build().perform();
        String allCategoriesPageButtonSelector = "//div[@id = 'menu']//div[@data-c = '0']//a[contains(text(), '%s')]";
        WebElement allCategoriesPageButton = getWait().waitingClickable(allCategoriesPageButtonSelector.formatted(option[1]), 2);
        allCategoriesPageButton.click();
        return this;
    }

    public <T extends Categories> T switchToPageFromHeadMenu(String... option) {
        String headMenuOptionButtonSelector = "//div[@id = 'menu']//div[@data-c]/a[contains(text(), '%s')]";
        WebElement headMenuOptionButton;
        if (option.length < 2) {
            headMenuOptionButton = getWait().waitingClickable(headMenuOptionButtonSelector.formatted(option[0]), 3);
            headMenuOptionButton.click();
            return (T) this;
        }
        headMenuOptionButton = getWait().waitingClickable(headMenuOptionButtonSelector.formatted(option[0]), 3);
        new Actions(driver).moveToElement(headMenuOptionButton).build().perform();
        String headMenuPageButtonSelector = "//div[@id = 'menu']//a[contains(text(), '%s')]";
        WebElement headMenuPageButton = getWait().waitingClickable(headMenuPageButtonSelector.formatted(option[1]), 2);
        headMenuPageButton.click();
        return (T) this;
    }
}
