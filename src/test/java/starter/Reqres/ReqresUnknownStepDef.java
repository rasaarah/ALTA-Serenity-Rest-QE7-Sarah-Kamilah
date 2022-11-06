package starter.Reqres;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class ReqresUnknownStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //Scenario 7, Get Unknown List Page
    @Given("Get unknown list page {int}")
    public void getUnknowListWithValidJson(int page) {
        reqresAPI.setGetUnkow(page);
    }
    @When("Send get unkown list request")
    public void sendGetUnkowListRequest() {
        SerenityRest.when().get(ReqresAPI.UNKNOW_LIST);
    }
    @And("Response body page should be page {int}")
    public void responseBodyPageShouldBeIdIdAndNameName(int page) {
        SerenityRest.then().body(ReqresResponses.PAGE, equalTo(page));
    }

    //Scenario 8, Get single unknown user
    @Given("Get unknown page {int}")
    public void getUnknownPage(int page) {
        reqresAPI.getUnkowPage(page);
    }
    @When("Send get unknown single list")
    public void sendGetUnkownRequest() {
        SerenityRest.when().get(ReqresAPI.UNKNOW_PAGE);
    }
    @And("Response body page should be id {int} and name {string}")
    public void ResponId(int id, String name) {
        SerenityRest.then().body(ReqresResponses.DATA_ID, equalTo(id))
                .body(ReqresResponses.DATA_NAME, equalTo(name));
    }
    @And("Validate get unknown list user json schema")
    public void validateGetUnknownListUserJsonSchema() {
        File json = new File(ReqresAPI.JSON_SCHEMA+"/GetListUserJsonSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }

}
