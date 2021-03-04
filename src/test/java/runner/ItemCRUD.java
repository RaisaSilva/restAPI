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

public class ItemCRUD {

    Response response;
    Map<String, String> data = new HashMap<>();

    @Given("tengo acceso a Todo.ly")
    public void tengoAccesoATodoLy(){
        System.out.println("Tengo acceso a todo.ly");
    }

    @When("envio una peticion POST al url {} con json")
    public void envioUnaPeticionPOSTAlUrlConJson(String url, String body) {
        response =  given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                post(url);

        response.then().log().all();

    }

    @Then("quiero que el codigo de respuesta sea {int}")
    public void quieroQueElCodigoDeRespuestaSea(int code) {
        response.then().
                statusCode(code);
    }


    @And("espero que el contenido del item sea {string}")
    public void esperoQueElContenidoDelItemSea(String expectedContent) {
        response.then().
                body("Content", equalTo(expectedContent));
    }


    @And("yo espero el response body sea")
    public void yoEsperoElResponseBodySea(String expectedBody) {
        Assert.assertTrue("Error! Los json no son iguales", JsonHelper.areEqualJson(replaceAllData(expectedBody),response.getBody().asString()));
    }

    @And("yo obtengo el {} y lo guardo en {}")
    public void yoObtengoElIdYLoGuardoEnId_Item(String property, String nameVar) {
        data.put(nameVar, response.then().extract().path(property) + "");
    }

    @When("yo envio una peticion PUT al url {} con json")
    public void yoEnvioUnaPeticionPUTAlUrlHttpTodoLyApiItemsId_ItemJsonConJson(String url, String body) {
        response = given()
                .auth()
                .preemptive()
                .basic("upb2021@upb.com","12345")
                .contentType(ContentType.JSON)
                .body(body)
                .log()
                .all()
                .when()
                .put(replaceAllData(url));
        response.then().log().all();
    }

    @When("yo envio una peticion GET al url {} con json")
    public void yoEnvioUnaPeticionGETAlUrlHttpTodoLyApiItemsId_ItemJsonConJson(String url, String body) {
        response =  given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                log().
                all().
                when().
                get(replaceAllData(url));
        response.then().log().all();
    }

    @When("yo envio una peticion DELETE al url {} con json")
    public void yoEnvioUnaPeticionDELETEAlUrlHttpTodoLyApiItemsId_ItemJsonConJson(String url, String body) {
        response =  given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                log().
                all().
                when().
                get(replaceAllData(url));
        response.then().log().all();
    }

    private String replaceAllData(String value){

        for (String key: data.keySet()) {
            value=value.replace(key,data.get(key));
        }

        return value;
    }


}
