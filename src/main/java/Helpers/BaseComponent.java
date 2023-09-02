package Helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BaseComponent {

    protected WebElement superElement;

    public BaseComponent(WebElement element) {
        this.superElement = element;
        PageFactory.initElements(superElement, this);
    }
}
