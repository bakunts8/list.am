package ListPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Apartments extends Categories {

    @FindBy(xpath = "//div[@class = 'dlf']")
    private WebElement pageLoadCheckElement;

    @FindBy(xpath = "//div[@id ='contentr']//div[@class = 'clabel']")
    private List<WebElement> apartmentsFromAgency;

    @FindBy(xpath = "//div[@id ='contentr']//a//div[@class = 'l' or @class = 'l l3']")
    private List<WebElement> apartmentsFromAgencyInfo;

    private String endpoint = "56";
    private final String startUrl = "https://www.list.am/category/";

    public Apartments(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getEndpoint() {
        return super.getEndpoint() + endpoint;
    }

    public WebElement getPageLoadCheckElement() {
        return pageLoadCheckElement;
    }

    public Apartments filterThePage(String... options) {
        for (String option : options) {
            String sectionFilterSelector = "//div[@class = 'section']//label[contains(@for, 'id') and contains(text(), '%s')]";
            WebElement optionButton = getWait().waitingClickable(sectionFilterSelector.formatted(option), 3);
            optionButton.click();
        }
        String endUrl = getDriver().getCurrentUrl();
        endpoint = endUrl.substring(startUrl.length());
        return this;
    }

    public boolean checkFilteringApartments() {

        boolean isOfferedFromAgency = true;

        for (WebElement element : apartmentsFromAgency) {
            if (!element.getText().equals("Agency")) {
                isOfferedFromAgency = false;
            }
        }
        return isOfferedFromAgency;
    }
}
