package starter.Reqres;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.io.File;

public class ReqresUpdateUserStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //Update user with valid ID
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
}
