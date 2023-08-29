package ListPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Notebooks extends Categories {

    @FindBy(xpath = "(//div[@id = 'contentr']//a//div[contains(@class, 'at')])[80]")
    private WebElement pageLoadCheckElement;

    private String endpoint = "116";
    private String newEndpoint = "";

    public Notebooks(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public boolean checkPriceRange() {
        boolean isInRange = true;
        int price;

        for (WebElement element : getItemPrice()) {
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
        for (WebElement element : getItemPrice()) {
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

        for (WebElement element : getItemGeneralInfo()) {
            if (!element.getText().contains(location)) {
                isInLocation = false;
                System.out.println("location");
            }
        }
        return isInLocation;
    }
}
