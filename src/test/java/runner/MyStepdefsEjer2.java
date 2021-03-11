package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilsJson.JsonHelper;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class MyStepdefsEjer2 {
    Response response;
    Map<String, String> data = new HashMap<>();
    String email;
    int password;

    @Given("Access to Todo.ly with email {string} and password {int}")
    public void accessToTodoLyWithEmailAndPassword(String email, int password){
        this.email = email;
        this.password = password;
    }

    @Then("My status code must be {int} \\(Ok)")
    public void myStatusCodeMustBeOk(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("My {} content must be")
    public void myItemNameMustBe(String type, String content) {
        Assert.assertTrue("No son iguales los objetos", JsonHelper.areEqualJson(replaceData(content),response.getBody().asString()));
    }


    @When("I sent a POST request for creation of a new item at {}")
    public void iSentAPOSTRequestForCreationOfANewItemAtHttpTodoLyApiItemsJson(String url, String item) {
        response = given().
                auth().
                preemptive().
                basic(email,""+password).
                contentType(ContentType.JSON).
                body(item).
                log().
                all().
                when().
                post(url);
    }

    @And("I get the {} and I save it in {}")
    public void iGetTheIdAndISaveIt(String property, String nameVar) {
        data.put(nameVar,response.then().extract().path(property)+"");
    }

    @When("I sent a PUT request for update of a new item at {}")
    public void iSentAPUTRequestForUpdateOfANewItemAtHttpTodoLyApiItemsID_ITEMJson(String url, String item) {
        response =  given().
                auth().
                preemptive().
                basic(email,""+password).
                contentType(ContentType.JSON).
                body(item).
                log().
                all().
                when().
                put(replaceData(url));
    }

    @When("I sent a GET request for retrieve an item at {}")
    public void iSentAGETRequestForRetrieveANewItemAtHttpTodoLyApiItemsID_ITEMJson(String url) {
        response =  given().
                auth().
                preemptive().
                basic(email,""+password).
                contentType(ContentType.JSON).
                log().
                all().
                when().
                get(replaceData(url));
    }

    @When("I sent a DELETE request to remove an item at {}")
    public void iSentADELETERequestToRemoveAnItemAtHttpTodoLyApiItemsID_ITEMJson(String url) {
        response =  given().
                auth().
                preemptive().
                basic(email,""+password).
                contentType(ContentType.JSON).
                log().
                all().
                when().
                delete(replaceData(url));
    }

    private String replaceData(String value){
        for (String key: data.keySet()) {
            value=value.replace(key,data.get(key));
        }
        return value;
    }


    @When("I sent a GET request to get token at {}")
    public void iSentAGETRequestToGetTokenAtHttpsTodoLyApiAuthenticationTokenJson(String url) {
        response =  given().
                auth().
                preemptive().
                basic(email,""+password).
                contentType(ContentType.JSON).
                log().
                all().
                when().
                get(replaceData(url));
    }

    @When("I sent a POST request for creation of a new project at {} with {}")
    public void iSentAPOSTRequestForCreationOfANewProjectAtHttpTodoLyApiItemsJson(String url, String token, String project) {
        response =  given().
                header("Token", data.get(token)).
                contentType(ContentType.JSON).
                body(project).
                log().
                all().
                when().
                post(url);
        response.then().log().all();
    }
}
