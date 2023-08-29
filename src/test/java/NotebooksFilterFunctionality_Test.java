import Helpers.Language;
import ListPages.Categories;
import ListPages.HomePage;
import ListPages.Notebooks;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NotebooksFilterFunctionality_Test extends BaseTest {

    @Test
    public void checkFilterFunctionality() {
        Notebooks notebooks = (Notebooks) new Notebooks(getDriver()).get();
        notebooks.chooseLanguageInTopRight(Language.ENGLISH);
        notebooks.selectCurrency("AMD");
        notebooks.selectPrice("200000", "500000");
        notebooks.selectLocation("Kentron");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(notebooks.checkCurrency());
        softAssert.assertTrue(notebooks.checkPriceRange());
        softAssert.assertTrue(notebooks.checkLocation());

        softAssert.assertAll();
    }
}
