package ListPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Apartments extends Categories {

    @FindBy(xpath = "(//div[@id = 'contentr']//a//div[contains(@class, 'at')])[105]")
    private WebElement pageLoadCheckElement;

    private String endpoint = "56";
    private String newEndpoint = "";

    public Apartments(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getEndpoint() {
        return super.getEndpoint() + endpoint + newEndpoint;
    }

    public void setNewEndpoint(String newEndpoint) {
        this.newEndpoint = newEndpoint;
    }

    public WebElement getPageLoadCheckElement() {
        return pageLoadCheckElement;
    }

    public boolean checkFilteringApartments() {

        boolean isOfferedFromAgency = true;

        for (WebElement element : getItemAgencyLabel()) {
            if (!element.getText().equals("Agency")) {
                isOfferedFromAgency = false;
            }
        }
        return isOfferedFromAgency;
    }
}
