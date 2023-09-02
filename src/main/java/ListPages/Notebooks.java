package ListPages;

import org.openqa.selenium.WebDriver;

public class Notebooks extends CategoryPage {

    private String endpoint = "116";
    private String newEndpoint = "";

    public Notebooks(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getEndpoint() {
        return super.getEndpoint() + endpoint + newEndpoint;
    }
}
