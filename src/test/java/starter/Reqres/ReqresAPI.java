package starter.Reqres;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;

public class ReqresAPI {
    public static String URL = "https://reqres.in";
    public static final String DIR = System.getProperty("user.dir");
    public static final String JSON_REQ_BODY = DIR + "/src/test/resources/JSON/RequestBody";
    public static final String JSON_SCHEMA = DIR + "/src/test/resources/JSON/JsonSchema";
    public static String SET_LIST_USER = URL + "/api/users?page={page}";
    public static String SET_SINGLE_USER = URL + "/api/users/{id}";
    public static String POST_CREATE_NEW_USER = URL + "/api/users";
    public static String PUT_UPDATE_USER = URL + "/api/users/{id}";
    public static String DELETE_USER = URL + "/api/users/{id}";
    public static String UNKNOW_LIST = URL + "/api/unknow?page={page}";
    public static String UNKNOW_PAGE = URL + "/api/unknow/{id}";
    public static String LOGIN_USER = URL + "/api/login";
    public static String REGISTER_USER = URL + "/api/register";


    //Post new user
   @Step("Post create new user")
    public void setPostCreateNewUser(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    //Json File to make json body request
    @Step("Put update user")
    public void setPutUpdateUser(int id, File json){
        SerenityRest.given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    //Delete User
    @Step("Delete user")
    public void deleteUser(int id){
        SerenityRest.given()
                .pathParam("id", id);
    }

    //Get User/s
    @Step("Get single users")
    public void getSingleUsers(int id) {
        SerenityRest.given().pathParam("id", id);
    }
    @Step("Get unknown list")
    public void setGetUnkow(int page) {
        SerenityRest.given().pathParam("page", page);
    }
    @Step("Get single unkown")
    public void getUnkowPage(int id){
        SerenityRest.given().pathParam("id", id);
    }
    @Step("Get list users")
    public void getListUsers(int page){
        SerenityRest.given().pathParam("page", page);
    }

    //Login
    @Step("Login user with valid id")
    public void loginUser(File json){
        SerenityRest.given().contentType(ContentType.JSON).body(json);
    }
    @Step("Login invalid user")
    public void loginInvalidUser(File json){
        SerenityRest.given().contentType(ContentType.JSON).body(json);
    }

    //Register
    @Step("Register valid user")
    public void RegisterValidUser(File json){
        SerenityRest.given().contentType(ContentType.JSON).body(json);
    }
    @Step("Register invalid user")
    public void registerInvalidUser(File json) {
        SerenityRest.given().contentType(ContentType.JSON).body(json);
    }
}


















