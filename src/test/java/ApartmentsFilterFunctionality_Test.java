import Helpers.Language;
import ListPages.Apartments;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ApartmentsFilterFunctionality_Test extends BaseTest {

    @Test(groups = {"filtering"})
    public void filterFunctionalityTest() {
        Apartments page = (Apartments) new Apartments(getDriver()).get();
        page.chooseLanguageInTopRight(Language.ENGLISH);
        page.filterThePage("Agency");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.checkAgencyFilter(), ": Apartment didn't offered by agency");
        softAssert.assertAll();
    }
}
