package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateItemStep {

    Response response;

    @Given("yo tengo acceso a Todo.ly")
    public void yoTengoAccesoATodoLy() {
        System.out.println("acceso a todo.ly");
    }

    @When("yo envio una peticion POST al siguiente url {} con json")
    public void yoEnvioUnaPeticionPOSTAlSiguienteUrlHttpsTodoLyApiItemsJsonConJson(String url, String body) {
        response = given().
                auth().
                preemptive().
                basic("upb2021@upb.com", "12345").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                post(url);
        response.then().log().all();
    }

    @Then("yo espero que el codigo de respuesta sea: {int}")
    public void yoEsperoQueElCodigoDeRespuestaSea(int expectedResult) {
        response.then().
                statusCode(expectedResult);

    }

    @And("yo espero que el nombre del project sea: {string}")
    public void yoEsperoQueElNombreDelProjectSea(String expectedNameProject) {
        response.then().
                body("Content", equalTo(expectedNameProject));

    }


}
