package Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waits {

    private final WebDriver driver;

    public Waits(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitingPresence(By by, int second) {
        return webDriverWait(second).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitingVisibility(By by, int second) {
        return webDriverWait(second).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitingVisibility(String selector, int second) {
        return webDriverWait(second).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
    }

    public WebElement waitingClickable(By by, int second) {
        return webDriverWait(second).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitingClickable(String selector, int second) {
        return webDriverWait(second).until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
    }

    public WebElement waitingClickable(WebElement element, int second) {
        return webDriverWait(second).until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> waitingElements(By by, int second) {
        return webDriverWait(second).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> waitingElements(String selector, int second) {
        return webDriverWait(second).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selector)));
    }

    public WebDriverWait webDriverWait(int second) {
        return new WebDriverWait(driver, Duration.ofSeconds(second));
    }
}
