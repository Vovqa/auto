package auto1.com.ui;

import org.testng.annotations.Test;
import auto1.com.BaseTest;
import pages.SearchPage;
import testData.TestData;

public class SearchTest extends BaseTest {

    @Test
    public void checkSearchFunctionality() {
        new SearchPage()
                .openSearchPage()
                .selectFilterByRegistration(TestData.PRODUCE_YEAR)
                .waitForApply()
                .selectPriceFilter(TestData.FILTER_NAME, TestData.DESCENDING_PRICE)
                .verifyCarsPriceAreFiltered(TestData.PRODUCE_YEAR)
                .verifyCarsSortedByDescending();
    }
}
