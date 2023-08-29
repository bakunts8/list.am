import Helpers.Language;
import ListPages.Apartments;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApartmentsFilterFunctionality_Test extends BaseTest {

    @Test
    public void filterFunctionalityTest() {
        Apartments page = (Apartments) new Apartments(getDriver()).get();
        page.chooseLanguageInTopRight(Language.ENGLISH);
        page.filterThePage("Agency");

        Assert.assertTrue(page.checkFilteringApartments(), ": Apartment(s) didn't offered by agency");
    }
}
