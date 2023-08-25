import Helpers.Language;
import ListPages.Notebooks;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NotebooksFilterFunctionality_Test extends BaseTest {

    @Test
    public void checkFilterFunctionality() {
        Notebooks notebooks = new Notebooks(getDriver()).open();
        notebooks.chooseLanguageInTopRight(Language.ENGLISH);

        notebooks.selectCurrency("AMD");
        notebooks.selectPrice("200000", "500000");
        notebooks.selectLocation("Kentron").open();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(notebooks.checkCurrency());
        softAssert.assertTrue(notebooks.checkPriceRange());
        softAssert.assertTrue(notebooks.checkLocation());

        softAssert.assertAll();
    }
}
