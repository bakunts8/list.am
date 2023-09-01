package ListPages;

import Helpers.Language;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void chooseLanguage(Language option) {
        String value = Language.getValue(option);
        String languagePanelSelector = "//div[@class = 'bar']/a[contains(@href, '%s')]";
        WebElement languageButton = getWait().waitingClickable(languagePanelSelector.formatted(value), 2);
        languageButton.click();
    }
}
