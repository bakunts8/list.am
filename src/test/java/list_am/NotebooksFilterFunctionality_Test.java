package list_am;

import Helpers.Language;
import ListPages.Notebooks;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NotebooksFilterFunctionality_Test extends BaseTest {

    @Test(dataProvider = "filterData", groups = {"page_filter"})
    public void checkFilterFunctionality(int priceFrom, int priceTo, String currency, String location) {
        Notebooks notebook = (Notebooks) new Notebooks(getDriver()).get();
        notebook.chooseLanguageInTopRight(Language.ENGLISH);
        notebook.selectCurrency(currency);
        notebook.selectPrice(priceFrom, priceTo);
        notebook.selectLocation(location);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(notebook.checkCurrency(currency), "currency does not match");
        softAssert.assertTrue(notebook.checkPriceRange(priceFrom, priceTo), "the price isn't within the specified range");
        softAssert.assertTrue(notebook.checkLocation(location), "the location is wrong");

        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] filterData() {
        return new Object[][] {
                {200000, 500000, "AMD", "Kentron"},
                {500000, 700000, "AMD", "Gyumri"}
        };
    }
}
