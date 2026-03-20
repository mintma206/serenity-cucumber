package starter.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import starter.config.DataDrivenHooks;
import starter.navigation.NavigateTo;
import starter.search.LookForInformation;
import starter.utils.JsonDataUtils;

import java.util.List;

/**
 * @author mamt
 * @version 1.0
 * @date 2026/3/17 10:02
 */
public class SearchJsonListCaseSteps {


    @When("{actor} prepares to search using {string} in json")
    public void prepareSearches(Actor actor, String key) {
        JsonNode data = DataDrivenHooks.getCurrentCase();
        List<JsonNode> list = JsonDataUtils.getArrayAsList(data, key);
        Serenity.recordReportData().withTitle("Total ".concat(key)).andContents(String.valueOf(list.size()));
    }

    @Then("{actor} should verify the search results for each {string} in json")
    public void verifyEachItem(Actor actor, String key) {
        JsonNode data = DataDrivenHooks.getCurrentCase();
        List<JsonNode> items = JsonDataUtils.getArrayAsList(data, key);
        for (JsonNode item : items) {
            String name = item.path("name").asText("测试值");
            String expect = item.path("expect").asText("期待值");
            int id = item.path("id").asInt();
            String testCaseTitle = String.format("id: %s (name: %s, expect: %s)", id, name, expect);
            Serenity.reportThat(testCaseTitle, () -> {
                actor.attemptsTo(NavigateTo.theSearchHomePage());
                // When 部分：执行搜索
                actor.attemptsTo(LookForInformation.about(name));
                //Then 部分：验证搜索结果
                actor.attemptsTo(
                        Ensure.that(TheWebPage.title()).containsIgnoringCase(expect)
                );
            });
        }
    }
}
