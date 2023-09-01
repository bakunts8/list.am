package ListPages;

import org.openqa.selenium.WebDriver;

public class Apartments extends Category {

    private String endpoint = "56";
    private String newEndpoint = "";

    public Apartments(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getEndpoint() {
        return super.getEndpoint() + endpoint + newEndpoint;
    }
}
