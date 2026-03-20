package starter.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import starter.config.DataDrivenHooks;
import starter.navigation.NavigateTo;
import starter.search.LookForInformation;
import starter.utils.JsonDataUtils;


/**
 * @author mamt
 * @version 1.0
 * @date 2026/3/17 10:02
 */
public class SearchJsonSingleCaseSteps {

    @Given("{actor} is attempts to open search page")
    public void openSearchPage(Actor actor) {
        actor.attemptsTo(NavigateTo.theSearchHomePage());

    }

    @When("{actor} looks up by {string} in json")
    public void searchUsingJsonCustName(Actor actor, String term) {
        JsonNode data = DataDrivenHooks.getCurrentCase();

        String name = JsonDataUtils.getStrWithDefault(data, term, "未定义名称");

        actor.attemptsTo(LookForInformation.about(name));

    }

    @Then("{actor} should see information {string} in json")
    public void verifySearchResult(Actor actor, String term) {
        JsonNode data = DataDrivenHooks.getCurrentCase();

        String expect = JsonDataUtils.getStrWithDefault(data, term, "未定义预期");

        actor.attemptsTo(
                Ensure.that(TheWebPage.title()).containsIgnoringCase(expect)
        );
    }
}
