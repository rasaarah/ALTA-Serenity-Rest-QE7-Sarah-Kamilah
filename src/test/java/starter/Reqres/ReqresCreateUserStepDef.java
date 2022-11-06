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

public class ReqresCreateUserStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //Scenario 2, Create user with valid json
    @Given("Post create user with valid json")
    public void postCreateUserWithValidJson() {
        File json = new File(ReqresAPI.JSON_REQ_BODY+"/CreateUser.json");
        reqresAPI.setPostCreateNewUser(json);
    }
    @When("Send post user request")
    public void sendPostUserRequest() {
        SerenityRest.when().post(ReqresAPI.POST_CREATE_NEW_USER);
    }
    @Then("Status code should be {int} Created")
    public void statusCodeShouldBeCreated(int created) {
        SerenityRest.then().statusCode(created);
    }
    @And("Response body should contain name {string} and job {string}")
    public void responseBodyShouldContainNameAndJob(String name, String job) {
        SerenityRest.then()
                .body(ReqresResponses.NAME,equalTo(name))
                .body(ReqresResponses.JOB,equalTo(job));
    }
    @And("Validate create user json schema")
    public void validateCreateUserJsonSchema() {
        File json = new File(ReqresAPI.JSON_SCHEMA+"/CreateUserJsonSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
