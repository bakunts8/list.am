package ListPages;

import Helpers.Language;
import Helpers.Waits;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static java.time.Duration.ofSeconds;

public class BasePage extends LoadableComponent<BasePage> {

    private final String BaseURL = "https://www.list.am";
    private final String endpoint = "";

    protected final WebDriver driver;
    private Waits wait;

    @FindBy(xpath = "//input[@id = 'idSearchBox']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class = 'b']//a[@id = 'ap' and text() = 'Post an Ad']']")
    private WebElement postAnAdButton;

    @FindBy(xpath = "//div[@id = 'lbar']")
    private WebElement languageButton;

    @FindBy(xpath = "//div[@id = 'ph']//a[@id = 'l']")
    private WebElement toHomePageButton;

    @FindBy(xpath = "//div[@class = 'r']/a[contains(text(), 'Terms of Service')]")
    private WebElement pageLoadCheckElement;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(8)), this);
    }

    @Override
    protected void load() {
        driver.get(BaseURL + getEndpoint());
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            getPageLoadCheckElement().isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            throw new Error("The page is not loaded");
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public WebElement getPageLoadCheckElement() {
        return pageLoadCheckElement;
    }

    public WebDriver getDriver() {
        return driver;
    }



    public Waits getWait() {
        wait = new Waits(driver);
        return wait;
    }

    public String getBaseURL() {
        return BaseURL;
    }

    public void chooseLanguageInTopRight(Language option) {
        languageButton.click();
        String languageOptionButtonSelector = "//div[@id = 'lmenu']/a[contains(@href, '') and contains(text(), '%s')]";
        String value = Language.getValue(option);
        WebElement languageOptionButton = getWait().waitingClickable(languageOptionButtonSelector.formatted(value), 3);
        languageOptionButton.click();
    }

    public void switchToHomePage() {
        toHomePageButton.click();
    }

    public void search(String content) {
        searchField.sendKeys(content);
        searchField.submit();
    }
}
