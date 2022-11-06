package starter.Reqres;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class ReqresRegisterStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //Register with valid data
    @Given("Post register valid user with valid json")
    public void postRegistValidUserWithValidJson() {
        File json = new File(ReqresAPI.JSON_REQ_BODY + "/PostRegisterSuccess.json");
        reqresAPI.RegisterValidUser(json);
    }
    @When("Send post register valid user request")
    public void sendPostRegistValidUserRequest() {
        SerenityRest.when().post(ReqresAPI.REGISTER_USER);
    }
    @And("Response body page should be id {int} and token {string}")
    public void responseBodyPageShouldBeIdAndToken(int id, String token) {
        SerenityRest.then().body(ReqresResponses.ID, equalTo(id))
                .body(ReqresResponses.TOKEN, equalTo(token));
    }
    @And("Validate register user with json schema")
    public void ValidateRegisUser(){
        File json = new File(ReqresAPI.JSON_SCHEMA + "/RegisterSuccess.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //Register with invalid data
    @Given("Post register invalid user with valid json")
    public void postRegisterInvalidUserWithValidJson() {
        File json = new File(ReqresAPI.JSON_REQ_BODY + "/PostRegisterUnsuccess.json");
        reqresAPI.registerInvalidUser(json);
    }

    @When("Send post register invalid user request")
    public void sendPostRegisterInvalidUserRequest() {
        SerenityRest.when().post(ReqresAPI.REGISTER_USER);
    }

    @And("Validate register invalid user with json schema")
    public void validateRegisterInvalidUserWithJsonSchema() {
        File json = new File(ReqresAPI.JSON_SCHEMA + "/RegisterUnsuccess.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
