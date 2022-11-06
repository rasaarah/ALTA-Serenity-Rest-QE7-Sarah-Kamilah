package starter.Reqres;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class ReqresGetListStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //Get list user with param page
    @Given("Get list user with parameter page {int}")
    public void getListUserWithParameterPage(int page) {
        reqresAPI.getListUsers(page);
    }

    @When("Send get list user request")
    public void sendGetListUserRequest() {
        SerenityRest.when().get(ReqresAPI.SET_LIST_USER);
    }

    @Then("Status code should be {int} OK")
    public void statusCodeShouldBeOK(int OK) {
        SerenityRest.then().statusCode(OK);
    }

    @And("Response body page should be {int}")
    public void responseBodyPageShouldBePage(int page) {
        SerenityRest.then().body(ReqresResponses.PAGE,equalTo(page));
    }

    @And("Validate get list user json schema")
    public void validateGetListUserJsonSchema() {
        File json = new File(ReqresAPI.JSON_SCHEMA+"/GetListUserJsonSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
