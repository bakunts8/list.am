import Helpers.Language;
import ListPages.Apartments;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApartmentsFilterFunctionality_Test extends BaseTest {

    @Test
    public void filterFunctionalityTest() {
        Apartments page = new Apartments(getDriver()).open();
        page.chooseLanguageInTopRight(Language.ENGLISH);
        page.filterThePage("Agency").open();

        Assert.assertTrue(page.checkFilteringApartments(), ": Apartment(s) didn't offered by agency");
    }
}
