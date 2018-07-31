package pages;

import com.codeborne.selenide.*;
import config.TestConfig;
import helpers.TextHelper;
import io.qameta.allure.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;


public class SearchPage {

    private SelenideElement yearFilter = $x("//span[contains(text(), 'Erstzulassung ab')]");
    private SelenideElement yearList = $x("//span[contains(text(), 'Erstzulassung ab')]//ancestor::div[2]//select");
    private SelenideElement filterButton = $("[data-qa-selector='active-filter'] button");
    private ElementsCollection sortFilter = $$("[data-qa-selector='select']");
    private ElementsCollection yearProduced = $$x("//ul[contains(@class, 'specList')]/li[1]");
    private ElementsCollection carPrices = $$("[data-qa-selector='price']");

    @Step
    public SearchPage openSearchPage() {
        open(TestConfig.getBaseUrl());
        return this;
    }

    @Step
    public SearchPage selectFilterByRegistration(String year) {
        yearFilter.click();
        yearList.selectOptionContainingText(year);
        return this;
    }

    @Step
    public SearchPage waitForApply() {
        filterButton.shouldBe(Condition.exist);
        return this;
    }

    @Step
    public SearchPage selectPriceFilter(String filterName, String sortType) {
        sortFilter.shouldHave(CollectionCondition.sizeGreaterThan(0)).stream()
                .filter(filterPrice -> filterPrice.getAttribute("name").equalsIgnoreCase(filterName)).findFirst().get().selectOptionContainingText(sortType);
        return this;
    }


    @Step
    public SearchPage verifyCarsPriceAreFiltered(String produceYear) {
        for (SelenideElement yearProduction : yearProduced) {
            String year = yearProduction.getText().substring(1);
            double newYear = Double.parseDouble(year);
            int newYear1 = (int) newYear;
            assertThat(newYear1).isGreaterThanOrEqualTo(TextHelper.convertToInt(produceYear));
        }
        return this;
    }

    @Step
    public SearchPage verifyCarsSortedByDescending() {
        ArrayList<Integer> priceList = new ArrayList<>();
        for (SelenideElement priceDescending : carPrices) {
            String price = TextHelper.removeLastCharOptional(priceDescending.getText(), 2);
            String formatedPrice = TextHelper.removeDotsOptional(price);
            priceList.add(TextHelper.convertToInt(formatedPrice));
        }
        for (int i = 0; i < priceList.size() - 1; i++) {
            assertThat(priceList.get(i)).isGreaterThanOrEqualTo(priceList.get(i + 1));
        }
        return this;
    }
}




