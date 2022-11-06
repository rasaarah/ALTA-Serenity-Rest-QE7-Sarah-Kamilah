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

public class ReqresLoginStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //Login with valid id
    @Given("Post login user with valid json")
    public void LoginUserSucces() {
        File json = new File(ReqresAPI.JSON_REQ_BODY + "/PostLogin.json");
        reqresAPI.loginUser(json);
    }
    @When("Send post login user request")
    public void sendPostLoginUserRequest() {
        SerenityRest.when().post(ReqresAPI.LOGIN_USER);
    }
    @And("Response body should contain {string} as token")
    public void responBodyToken(String token){
        SerenityRest.then().body(ReqresResponses.TOKEN, equalTo(token));
    }
    @And("Validate login user with json schema")
    public void ValidateLoginUser(){
        File json = new File(ReqresAPI.JSON_SCHEMA + "/LoginSuccessSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //Login with invalid user
    @Given("Post login invalid user with valid json")
    public void postLoginInvalidUserWithValidJson() {
        File json = new File(ReqresAPI.JSON_REQ_BODY + "/PostInvalidLogin.json");
        reqresAPI.loginInvalidUser(json);
    }
    @When("Send post login invalid user request")
    public void sendPostLoginInvalidUserRequest() {
        SerenityRest.when().post(ReqresAPI.LOGIN_USER);
    }
    @Then("Status code should be {int} ERROR")
    public void statusCodeShouldBeERROR(int error) {
        SerenityRest.then().statusCode(error);
    }
    @And("Response body page should be contain {string}")
    public void responseBodyPageShouldBeContain(String error) {
        SerenityRest.then().body(ReqresResponses.ERROR, equalTo(error));
    }
    @And("Validate invalid login user with json schema")
    public void ValidateInvalidLoginUser(){
        File json = new File(ReqresAPI.JSON_SCHEMA + "/LoginUnsuccessSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
