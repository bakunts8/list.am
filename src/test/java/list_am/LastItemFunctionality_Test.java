package list_am;

import Helpers.Language;
import ListPages.Notebooks;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LastItemFunctionality_Test extends BaseTest {

    @Test
    public void checkIsClickableLastItem() {
        Notebooks notebooks = (Notebooks) new Notebooks(getDriver()).get();
        notebooks.chooseLanguageInTopRight(Language.ENGLISH);

        Assert.assertTrue(notebooks.isClickableLatestItem(), "Item isn't clickable");
    }
}
