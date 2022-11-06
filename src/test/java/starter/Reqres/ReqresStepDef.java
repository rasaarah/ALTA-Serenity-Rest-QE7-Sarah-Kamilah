package starter.Reqres;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.json.Json;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class ReqresStepDef {
    @Steps
    ReqresAPI reqresAPI; //manggil dulu jan lupa

    //Scenario 1, Get list user with param page
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
    //khusus yang punya response body
    public void validateGetListUserJsonSchema() {
        File json = new File(ReqresAPI.JSON_SCHEMA+"/GetListUserJsonSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }

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

    //Scenario 3, Update user with valid ID
    @Given("Put update user with valid json with id {int}")
    public void putUpdateUserWithValidJsonWithIdId(int id) {
        File json = new File(ReqresAPI.JSON_REQ_BODY+"/UpdateUser.json");
        reqresAPI.setPutUpdateUser(id,json);
    }

    @When("Send put update user request")
    public void sendPutUpdateUserRequest() {
        SerenityRest.when().put(ReqresAPI.PUT_UPDATE_USER);
    }

    @And("Validate put update json schema")
    public void validatePutUpdateJsonSchema() {
        File json = new File(ReqresAPI.JSON_SCHEMA+"/PutUpdateJsonSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //Scenario 4, Delete user with valid ID
    @Given("Delete user with id {int}")
    public void deleteUserWithIdId(int id) {
        reqresAPI.deleteUser(id);
    }

    @When("Send delete user request")
    public void sendDeleteUserRequest() {
        SerenityRest.when().delete(ReqresAPI.DELETE_USER);
    }

    @Then("Status code should be {int} No Content")
    public void statusCodeShouldBeNoContent(int noContent) {
        SerenityRest.then().statusCode(noContent);
    }
}
